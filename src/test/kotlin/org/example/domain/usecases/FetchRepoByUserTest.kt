package org.example.domain.usecases

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.example.domain.entities.Repo
import org.example.domain.repositories.RepoRepository

class FetchRepoByUserTest : WordSpec() {
    private val repoRepository = mockk<RepoRepository>()
    private val fetchRepoByUser = FetchReposByUser(repoRepository)

    override fun beforeTest(testCase: TestCase) {
        clearAllMocks()
        super.beforeTest(testCase)
    }

    init {
        "invoke" should {
            "retrieves repos by user" {
                // Arrange
                val mock = mockk<List<Repo>>()
                every { repoRepository.getReposByUser(any()) } returns Single.just(mock)

                // Act
                val observer = fetchRepoByUser("test").test()

                // Assert
                observer.assertComplete()
                observer.assertValue(mock)
            }
        }
    }
}