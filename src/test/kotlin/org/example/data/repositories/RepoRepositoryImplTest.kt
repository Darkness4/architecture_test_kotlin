package org.example.data.repositories

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.BehaviorSubject
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.example.core.connectivity.Connectivity
import org.example.core.error.HttpCallFailureException
import org.example.core.error.NoNetworkException
import org.example.core.error.ServerUnreachableException
import org.example.data.datasources.GithubLocalDataSource
import org.example.data.datasources.GithubRemoteDataSource
import org.example.data.models.RepoModel
import org.example.data.models.UserModel
import org.example.domain.repositories.RepoRepository
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RepoRepositoryImplTest : WordSpec() {
    private val tUser = "login"
    private val tRepos = listOf(
        RepoModel(
            0,
            "name",
            "full_name",
            UserModel(
                0,
                tUser
            )
        )
    )
    private lateinit var repository: RepoRepository
    private lateinit var mockIsOnline: BehaviorSubject<Boolean>
    private val mockRemote: GithubRemoteDataSource = mockk(relaxed = true)
    private val mockLocal: GithubLocalDataSource = mockk(relaxed = true)
    private val mockConnectivity: Connectivity = mockk(relaxed = true)


    override fun beforeTest(testCase: TestCase) {
        clearAllMocks()
        mockIsOnline = BehaviorSubject.createDefault(false)
        every { mockConnectivity.isOnline } returns mockIsOnline
        repository = RepoRepositoryImpl(
            mockRemote,
            mockLocal,
            mockConnectivity
        )
        super.beforeTest(testCase)
    }

    init {

        "RepoRepositoryImpl" should {
            "subscribe to connectivity on init" {
                // Arrange
                clearAllMocks()

                // Act
                repository = RepoRepositoryImpl(
                    mockRemote,
                    mockLocal,
                    mockConnectivity
                )

                // Assert
                verify { mockConnectivity.isOnline.subscribe(any<Consumer<in Boolean>>()) }
            }
        }

        "getReposByUser" should {
            "save to local" {
                // Arrange
                mockIsOnline.onNext(true)
                every { mockRemote.getReposByUser(tUser) } returns Single.just(tRepos)

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertComplete()
                verify { mockLocal.saveRepos(tRepos) }
            }

            "return the entities by user if online" {
                // Arrange
                mockIsOnline.onNext(true)
                every { mockRemote.getReposByUser(tUser) } returns Single.just(tRepos)
                val expected = tRepos.map { it.asEntity() }

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertComplete()
                observer.assertValue(expected)
            }

            "return NoNetworkException if offline" {
                // Arrange
                mockIsOnline.onNext(true)
                every { mockRemote.getReposByUser(tUser) } returns Single.error(SocketTimeoutException())

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertError(NoNetworkException::class.java)
            }

            "return ServerUnreachableException if no DNS" {
                // Arrange
                mockIsOnline.onNext(true)
                every { mockRemote.getReposByUser(tUser) } returns Single.error(UnknownHostException())

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertError(ServerUnreachableException::class.java)
            }

            "return HttpCallFailureException if not OK" {
                // Arrange
                mockIsOnline.onNext(true)
                every { mockRemote.getReposByUser(tUser) } returns Single.error(
                    HttpException(
                        Response.error<String>(
                            400,
                            ResponseBody.create(
                                MediaType.parse("text/plain"),
                                "error"
                            )
                        )
                    )
                )

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertError(HttpCallFailureException::class.java)
            }

            "return Error if not expected" {
                // Arrange
                mockIsOnline.onNext(true)
                val error = Exception("Fake")
                every { mockRemote.getReposByUser(tUser) } returns Single.error(error)

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertError(error)
            }

            "return the entities by user from cache if offine" {
                // Arrange
                mockIsOnline.onNext(false)
                every { mockLocal.getRepos() } returns Single.just(tRepos)
                val expected = tRepos.map { it.asEntity() }

                // Act
                val observer = repository.getReposByUser(tUser).test()

                // Assert
                observer.assertComplete()
                observer.assertValue(expected)
            }
        }
    }
}