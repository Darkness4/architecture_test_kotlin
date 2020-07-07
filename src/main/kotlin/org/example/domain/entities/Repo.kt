package org.example.domain.entities

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.example.data.models.RepoModel
import tornadofx.getValue
import tornadofx.property
import tornadofx.setValue

class Repo(
    id: Int,
    name: String,
    full_name: String,
    owner: User
) {
    val idProperty = SimpleIntegerProperty(this, "ID", id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(this, "Name", name)
    var name by nameProperty

    val fullNameProperty = SimpleStringProperty(this, "Full Name", full_name)
    var full_name by fullNameProperty

    val ownerProperty = SimpleObjectProperty(this, "Owner", owner)
    var owner by ownerProperty
}

fun Repo.toRepoModel() =
    RepoModel(
        id = this.id,
        name = this.name,
        full_name = this.full_name,
        owner = this.owner.toUserModel()
    )