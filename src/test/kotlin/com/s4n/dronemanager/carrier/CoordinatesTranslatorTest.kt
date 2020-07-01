package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.constants.NORTH
import com.s4n.dronemanager.constants.SOUTH
import com.s4n.dronemanager.constants.WEST
import com.s4n.dronemanager.model.CartesianCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.lang.IllegalArgumentException

class CoordinatesTranslatorTest : Spek({

    Feature("Coordinates translator") {
        val instructionTranslator by memoized { CoordinatesTranslator() }

        Scenario("Calculate delivery coordinates") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction to calculate the delivery coordinates") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(0, 0, NORTH),
                    instruction = "AAAAAAAAAAA"
                )
            }

            Then("it should return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(0, 11, NORTH))
            }
        }

        Scenario("Calculate delivery coordinates with turns") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction with turns") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(0, 0, NORTH),
                    instruction = "AAAAIAA"
                )
            }

            Then("it should return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(-2, 4, WEST))
            }
        }

        Scenario("Calculate delivery coordinates with no default initial position") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(-2, 4, WEST),
                    instruction = "DDDAIAD"
                )
            }

            Then("it should return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(-1, 3, SOUTH))
            }
        }

        Scenario("Incorrect instruction") {
            var throwable: Throwable? = null
            When("sending instruction with incorrect actions") {
                throwable = catchThrowable {
                    instructionTranslator.calculateDeliveryCoordinates(
                        initialCoordinate = CartesianCoordinate(0, 0, NORTH),
                        instruction = "DAAXID"
                    )
                }
            }

            Then("it should return the expected exception") {
                assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
                assertThat(throwable).hasMessage("Incorrect action: X")
            }
        }
    }

})
