package org.example.domain.usecases

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Single
import org.example.domain.entities.Repo
import org.example.domain.repositories.RepoRepository

@Singleton
class FetchRepoByUser @Inject constructor(private val repoRepository: RepoRepository) {
    operator fun invoke(user: String): Single<List<Repo>> = repoRepository.getReposByUser(user)
}