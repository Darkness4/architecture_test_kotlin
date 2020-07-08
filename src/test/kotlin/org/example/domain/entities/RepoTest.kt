package org.example.domain.entities

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.example.data.models.RepoModel
import org.example.data.models.UserModel

class RepoTest : WordSpec() {
    private val entity = Repo(
        0,
        "name",
        "full_name",
        User(
            0,
            "login"
        )
    )

    init {
        "asModel" should {
            "returns the correct entity" {
                // Arrange
                val expected = RepoModel(
                    entity.id,
                    entity.name,
                    entity.fullName,
                    UserModel(
                        entity.owner.id,
                        entity.owner.login
                    )
                )

                // Act
                val result = entity.asModel()

                // Assert
                result shouldBe expected
            }
        }
    }
}