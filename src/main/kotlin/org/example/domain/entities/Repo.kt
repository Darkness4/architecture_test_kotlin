package org.example.domain.entities

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.example.core.entities.ModelMappable
import org.example.data.models.RepoModel
import tornadofx.getValue
import tornadofx.setValue

class Repo(
    id: Int,
    name: String,
    fullName: String,
    owner: User
): ModelMappable<RepoModel> {
    val idProperty = SimpleIntegerProperty(this, "ID", id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(this, "Name", name)
    var name: String by nameProperty

    val fullNameProperty = SimpleStringProperty(this, "Full Name", fullName)
    var fullName: String by fullNameProperty

    val ownerProperty = SimpleObjectProperty(this, "Owner", owner)
    var owner: User by ownerProperty

    override fun asModel(): RepoModel =
        RepoModel(
            id = this.id,
            name = this.name,
            full_name = this.fullName,
            owner = this.owner.asModel()
        )

    override fun equals(other: Any?): Boolean =
        (other is Repo)
                && id == other.id
                && name == other.name
                && fullName == other.fullName
                && owner == other.owner

    override fun hashCode(): Int {
        var result = idProperty.hashCode()
        result = 31 * result + nameProperty.hashCode()
        result = 31 * result + fullNameProperty.hashCode()
        result = 31 * result + ownerProperty.hashCode()
        return result
    }
}