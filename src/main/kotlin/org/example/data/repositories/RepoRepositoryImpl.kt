package org.example.data.repositories

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import org.example.core.connectivity.Connectivity
import org.example.core.error.HttpCallFailureException
import org.example.core.error.NoNetworkException
import org.example.core.error.ServerUnreachableException
import org.example.data.datasources.GithubLocalDataSource
import org.example.data.datasources.GithubRemoteDataSource
import org.example.domain.entities.Repo
import org.example.domain.repositories.RepoRepository
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val remote: GithubRemoteDataSource,
    private val local: GithubLocalDataSource,
    connectivity: Connectivity
) : RepoRepository {
    private var isOnline: Boolean
    private val disposable: Disposable

    init {
        isOnline = false
        disposable = connectivity.isOnline.subscribe { isOnline = it }
    }

    override fun getReposByUser(user: String): Single<List<Repo>> {
        return if (isOnline) {
            remote.getReposByUser(user)
                .onErrorResumeNext {
                    when (it) {
                        is SocketTimeoutException -> Single.error(NoNetworkException(it))
                        is UnknownHostException -> Single.error(ServerUnreachableException(it))
                        is HttpException -> Single.error(HttpCallFailureException(it))
                        else -> Single.error(it)
                    }
                }
                .map { repos ->
                    local.saveRepos(repos)
                    repos.map { it.asEntity() }
                }
        } else {
            local.getRepos()
                .map { repos ->
                    repos
                        .filter { it.owner.login == user }
                        .map { it.asEntity() }
                }
        }
    }
}
