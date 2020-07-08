package org.example.data.datasources

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import org.example.data.models.RepoModel
import org.example.data.models.UserModel

class GithubLocalDataSourceTest: WordSpec() {
    private lateinit var dataSource: GithubLocalDataSource
    private lateinit var mockHashMap: HashMap<Int, RepoModel>

    override fun beforeTest(testCase: TestCase) {
        mockHashMap = HashMap()
        dataSource = GithubLocalDataSourceImpl(mockHashMap)
        super.beforeTest(testCase)
    }

    init {
        "saveRepos" should {
            "save successfully" {
                // Arrange
                val repos = listOf(
                    RepoModel(
                        0,
                        "name",
                        "full_name",
                        UserModel(0, "login")
                    )
                )

                // Act
                val observer = dataSource.saveRepos(repos).test()

                // Assert
                observer.assertComplete()
                observer.dispose()

                val expected = repos.map { it.id to it }.toMap()
                mockHashMap shouldBe expected
            }
        }

        "getRepos" should {
            "retrieves all repos" {
                // Arrange
                mockHashMap[1] = RepoModel(
                    1,
                    "name",
                    "full_name",
                    UserModel(0, "login")
                )

                // Act
                val observer = dataSource.getRepos().test()

                // Assert
                observer.assertValue(mockHashMap.values.toList())
                observer.dispose()
            }
        }
    }
}