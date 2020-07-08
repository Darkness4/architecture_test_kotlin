package org.example.presentation.views

import javafx.beans.Observable
import javafx.scene.control.TableView
import org.example.domain.entities.Repo
import org.example.presentation.controllers.GithubController
import tornadofx.View
import tornadofx.column
import tornadofx.resizeColumnsToFitContent
import tornadofx.tableview

class GithubView : View() {
    private val controller: GithubController by inject()
    private var tableView: TableView<Repo>

    init {
        tableView = tableview(controller.repos) {
            column("ID", Repo::idProperty)
            column("Name", Repo::nameProperty)
            column("Full Name", Repo::fullNameProperty)
            column("Owner", Repo::ownerProperty)
        }

        controller.findRepoByUser("Darkness4")
    }

    override val root = tableView

    override fun onUndock() {
        controller.dispose()
    }
}