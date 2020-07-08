package org.example.data.models

import org.example.core.models.DomainMappable
import org.example.domain.entities.User

data class UserModel(
    val id: Int,
    val login: String
): DomainMappable<User> {
    override fun asEntity(): User =
        User(
            id = this.id,
            login = this.login
        )
}
