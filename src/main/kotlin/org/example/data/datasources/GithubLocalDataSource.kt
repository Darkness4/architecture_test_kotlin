package org.example.data.datasources

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.example.data.models.RepoModel

interface GithubLocalDataSource {
    fun saveRepos(repos: List<RepoModel>): Completable

    fun getRepos(): Single<List<RepoModel>>
}

@Singleton
class GithubLocalDataSourceImpl @Inject constructor() : GithubLocalDataSource {
    private val repoDB = HashMap<Int, RepoModel>()

    override fun saveRepos(repos: List<RepoModel>): Completable {
        repos.forEach { saveRepo(it) }
        return Completable.complete()
    }

    private fun saveRepo(repo: RepoModel): Completable {
        repoDB[repo.id] = repo
        return Completable.complete()
    }

    override fun getRepos(): Single<List<RepoModel>> = Single.just(repoDB.values.toList())
}
