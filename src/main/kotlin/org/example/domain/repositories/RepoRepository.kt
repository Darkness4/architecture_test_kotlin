package org.example.domain.repositories

import io.reactivex.rxjava3.core.Single
import org.example.domain.entities.Repo

interface RepoRepository {
    fun getReposByUser(user: String): Single<List<Repo>>
}
