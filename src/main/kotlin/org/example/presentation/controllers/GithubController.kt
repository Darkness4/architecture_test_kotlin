package org.example.presentation.controllers

import io.reactivex.rxjava3.disposables.Disposable
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.example.domain.entities.Repo
import org.example.domain.usecases.FetchRepoByUser
import tornadofx.Controller

class GithubController : Controller() {
    private val fetchRepoByUser: FetchRepoByUser by di()
    val repos: ObservableList<Repo> = FXCollections.observableArrayList<Repo>()
    private var d: Disposable? = null

    fun findRepoByUser(user: String) {
        d?.dispose()
        d = fetchRepoByUser(user).subscribe(
            { newRepos ->
                repos.clear()
                repos.addAll(newRepos)
            },
            { err -> println(err) }
        )
    }

    fun dispose() {
        d?.dispose()
    }
}