package org.example.data.models

import org.example.core.models.DomainMappable
import org.example.domain.entities.Repo

data class RepoModel(
    val id: Int,
    val name: String,
    val full_name: String,
    val owner: UserModel
): DomainMappable<Repo> {
    override fun asEntity(): Repo =
        Repo(
            id = this.id,
            name = this.name,
            fullName = this.full_name,
            owner = this.owner.asEntity()
        )
}
