package org.example.domain.usecases

import com.google.inject.Inject
import com.google.inject.Singleton
import io.reactivex.rxjava3.core.Single
import org.example.core.usecases.SingleUsecase
import org.example.domain.entities.Repo
import org.example.domain.repositories.RepoRepository

@Singleton
class FetchReposByUser @Inject constructor(private val repoRepository: RepoRepository): SingleUsecase<String, List<Repo>> {
    override operator fun invoke(params: String): Single<List<Repo>> = repoRepository.getReposByUser(params)
}