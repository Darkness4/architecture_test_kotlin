package org.example.domain.entities

import org.example.data.models.RepoModel

data class Repo(
    val id: Int,
    val name: String,
    val full_name: String,
    val owner: User
)

fun Repo.toRepoModel() =
    RepoModel(
        id = this.id,
        name = this.name,
        full_name = this.full_name,
        owner = this.owner.toUserModel()
    )
