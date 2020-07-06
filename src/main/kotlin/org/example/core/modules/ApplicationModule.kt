package org.example.core.modules

import dagger.Binds
import dagger.Module
import org.example.data.datasources.GithubLocalDataSource
import org.example.data.datasources.GithubLocalDataSourceImpl
import org.example.data.repositories.RepoRepositoryImpl
import org.example.domain.repositories.RepoRepository

@Module
abstract class ApplicationModule {
    @Binds
    abstract fun provideGithubLocalDataSource(githubLocalDataSource: GithubLocalDataSourceImpl): GithubLocalDataSource

    @Binds
    abstract fun provideRepoRepository(repositoryImpl: RepoRepositoryImpl): RepoRepository
}
