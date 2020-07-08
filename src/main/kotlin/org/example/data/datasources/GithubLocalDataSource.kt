package org.example.data.datasources

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.example.data.models.RepoModel

interface GithubLocalDataSource {
    fun saveRepos(repos: List<RepoModel>): Completable

    fun getRepos(): Single<List<RepoModel>>
}