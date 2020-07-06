package org.example.data.models

import org.example.domain.entities.Repo

data class RepoModel(
    val id: Int,
    val name: String,
    val full_name: String,
    val owner: UserModel
)

fun RepoModel.toRepo() =
    Repo(
        id = this.id,
        name = this.name,
        full_name = this.full_name,
        owner = this.owner.toUser()
    )
