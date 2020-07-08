package org.example.domain.entities

import org.example.core.entities.ModelMappable
import org.example.data.models.UserModel

data class User(
    val id: Int,
    val login: String
): ModelMappable<UserModel> {
    override fun asModel(): UserModel =
        UserModel(
            id = this.id,
            login = this.login
        )
}
