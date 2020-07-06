package org.example

import dagger.Component
import javax.inject.Singleton
import org.example.core.connectivity.Connectivity
import org.example.core.modules.ApplicationModule
import org.example.core.modules.ConnectivityModule
import org.example.core.modules.RetrofitModule
import org.example.domain.repositories.RepoRepository

@Singleton
@Component(modules = [RetrofitModule::class, ConnectivityModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun repo(): RepoRepository
    fun connectivity(): Connectivity
}

fun main() {
    val app: ApplicationComponent = DaggerApplicationComponent.builder().build()
    val repoRepository: RepoRepository = app.repo()
    val connectivity: Connectivity = app.connectivity()

    connectivity.setOnline()
    val repos = repoRepository.getReposByUser("Darkness4").blockingGet()

    connectivity.setOffline()
    println(repoRepository.getReposByUser("Darkness4").blockingGet())
}
