package org.example.core.connectivity

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.reactivex.rxjava3.observers.TestObserver

class ConnectivityTest: WordSpec() {
    private lateinit var connectivity: Connectivity

    override fun beforeTest(testCase: TestCase) {
        connectivity = ConnectivityImpl()
        super.beforeTest(testCase)
    }

    init {
        "connectivity" should {
            "be connected by default" {
                // Arrange
                val subscriber = TestObserver<Boolean>()
                connectivity.isOnline.subscribe(subscriber)

                // Assert
                subscriber.assertValue(true)
            }
        }

        "setOnline" should {
            "emit true" {
                // Arrange
                val subscriber = TestObserver<Boolean>()
                connectivity.isOnline.subscribe(subscriber)

                // Act
                connectivity.setOnline()

                // Assert
                subscriber.assertValues(true, true)
            }
        }

        "setOffline" should {
            "emit false" {
                // Arrange
                val subscriber = TestObserver<Boolean>()
                connectivity.isOnline.subscribe(subscriber)

                // Act
                connectivity.setOffline()

                // Assert
                subscriber.assertValues(true, false)
            }
        }
    }
}