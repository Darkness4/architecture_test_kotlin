package org.example.presentation.controllers

import io.reactivex.rxjava3.disposables.Disposable
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.example.domain.entities.Repo
import org.example.domain.usecases.FetchReposByUser
import tornadofx.Controller

class GithubController : Controller() {
    private val fetchReposByUser: FetchReposByUser by di()
    val repos: ObservableList<Repo> = FXCollections.observableArrayList<Repo>()
    private var d: Disposable? = null

    fun findRepoByUser(user: String) {
        d?.dispose()
        d = fetchReposByUser(user).subscribe(
            {
                repos.clear()
                repos.addAll(it)
            },
            { println(it) }
        )
    }

    fun dispose() {
        d?.dispose()
    }
}