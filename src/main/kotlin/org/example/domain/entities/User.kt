package org.example.domain.entities

import org.example.data.models.UserModel

data class User(
    val id: Int,
    val login: String
)

fun User.toUserModel() =
    UserModel(
        id = this.id,
        login = this.login
    )
