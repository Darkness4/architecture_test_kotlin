package org.example.data.datasources

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.example.data.models.RepoModel

@Singleton
class GithubLocalDataSourceImpl @Inject constructor(private val repoDB: HashMap<Int, RepoModel>) :
    GithubLocalDataSource {
    override fun saveRepos(repos: List<RepoModel>): Completable =
        Completable.merge(repos.map { saveRepo(it) })


    private fun saveRepo(repo: RepoModel): Completable =
        Completable.fromAction { repoDB[repo.id] = repo }


    override fun getRepos(): Single<List<RepoModel>> = Single.just(repoDB.values.toList())
}
