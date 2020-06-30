package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.model.CartesianCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class CoordinatesTranslatorTest : Spek({

    Feature("Coordinates translator") {
        val instructionTranslator by memoized { CoordinatesTranslator() }

        Scenario("Calculate delivery coordinates") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction to calculate the delivery coordinates") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(0, 0, 'N'),
                    instruction = "AAAAAAAAAAA"
                )
            }

            Then("it should return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(0, 11, 'N'))
            }
        }

        Scenario("Calculate delivery coordinates with turns") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction with turns") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(0, 0, 'N'),
                    instruction = "AAAAIAA"
                )
            }

            Then("it should return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(-2, 4, 'W'))
            }
        }

        Scenario("Calculate delivery coordinates with no default initial position") {
            var coordinates: CartesianCoordinate? = null
            When("sending an instruction") {
                coordinates = instructionTranslator.calculateDeliveryCoordinates(
                    initialCoordinate = CartesianCoordinate(-2, 4, 'W'),
                    instruction = "DDDAIAD"
                )
            }

            Then("it shoult return the corresponding coordinates") {
                assertThat(coordinates).isEqualTo(CartesianCoordinate(-1, 3, 'S'))
            }
        }

        Scenario("Coordinates String representation") {
            var stringRepresentation: String? = null
            When("Calling toString") {
                stringRepresentation = CartesianCoordinate(1,20,'W').toString()
            }

            Then("it should be written by following the correct format") {
                assertThat(stringRepresentation).isEqualTo("(1,20,W)")
            }
        }
    }

})