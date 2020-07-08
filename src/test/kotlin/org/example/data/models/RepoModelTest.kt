package org.example.data.models

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.example.domain.entities.Repo
import org.example.domain.entities.User

class RepoModelTest : WordSpec() {
    private val model: RepoModel = RepoModel(
        0,
        "name",
        "full_name",
        UserModel(
            0,
            "login"
        )
    )

    init {
        "asEntity" should {
            "returns the correct entity" {
                // Arrange
                val expected = Repo(
                    model.id,
                    model.name,
                    model.full_name,
                    User(
                        model.owner.id,
                        model.owner.login
                    )
                )

                // Act
                val result = model.asEntity()

                // Assert
                result shouldBe expected
            }
        }
    }
}