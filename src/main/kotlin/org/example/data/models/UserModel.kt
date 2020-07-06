package org.example.data.models

import org.example.domain.entities.User

data class UserModel(
    val id: Int,
    val login: String
)

fun UserModel.toUser() =
    User(
        id = this.id,
        login = this.login
    )
