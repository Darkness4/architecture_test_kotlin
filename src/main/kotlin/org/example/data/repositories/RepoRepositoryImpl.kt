package org.example.data.repositories

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Single
import org.example.core.connectivity.Connectivity
import org.example.data.datasources.GithubLocalDataSource
import org.example.data.datasources.GithubRemoteDataSource
import org.example.data.models.toRepo
import org.example.domain.entities.Repo
import org.example.domain.repositories.RepoRepository

@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val remote: GithubRemoteDataSource,
    private val local: GithubLocalDataSource,
    connectivity: Connectivity
) : RepoRepository {
    private var isOnline: Boolean = false

    init {
        connectivity.isOnline.subscribe { isOnline = it }
    }

    override fun getReposByUser(user: String): Single<List<Repo>> {
        return if (isOnline) {
            remote.getReposByUser(user).flatMap { repos ->
                local.saveRepos(repos).toSingle { repos.map { it.toRepo() } }
            }
        } else {
            local.getRepos()
                .map { repos ->
                    repos
                        .filter { it.owner.login == user }
                        .map { it.toRepo() }
                }
        }
    }
}
