package org.example.core.modules

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.example.data.datasources.GithubLocalDataSource
import org.example.data.datasources.GithubLocalDataSourceImpl
import org.example.data.repositories.RepoRepositoryImpl
import org.example.domain.repositories.RepoRepository

class ApplicationModule: AbstractModule() {
    @Provides
    fun provideGithubLocalDataSource(impl: GithubLocalDataSourceImpl): GithubLocalDataSource = impl

    @Provides
    fun provideRepoRepository(impl: RepoRepositoryImpl): RepoRepository = impl
}
