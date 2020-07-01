package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.constants.SOUTH
import com.s4n.dronemanager.constants.WEST
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate
import com.s4n.dronemanager.model.Drone
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object DroneCarrierTest : Spek({

    Feature("Drone Carrier") {
        val drone = Drone(id = "mavic-01")
        var droneCarrier = DroneCarrier(drone)

        Scenario("Sending a new delivery instruction") {
            val deliveryInstruction = "AAAAIAA"
            var coordinate: CartesianCoordinate? = null
            When("sending new delivery instruction") {
                coordinate = droneCarrier.sendItem(deliveryInstruction)
            }

            Then("it should return the current coordinate after delivering") {
                assertThat(coordinate).isEqualTo(CartesianCoordinate(-2,4, WEST))
            }
        }

        Scenario("Sending a delivery instruction out of range") {
            val deliveryInstruction = "AAAAAAAAAAA"
            var throwable: Throwable? = null
            When("sending the delivery instruction") {
                throwable = catchThrowable { droneCarrier.sendItem(deliveryInstruction) }
            }

            Then("it should throw the expected exception") {
                assertThat(throwable).isInstanceOf(DeliveryException::class.java)
                assertThat(throwable).hasMessage("Delivery distance out of range")
            }
        }

        Scenario("Sending instruction out of range including turns") {
            val deliveryInstruction = "AAAAAIAAAAADAAAAAIAAAAADA"
            var throwable: Throwable? = null
            When("sending the out of range instruction") {
                throwable = catchThrowable { droneCarrier.sendItem(deliveryInstruction) }
            }

            Then("it should throw the expected exception") {
                assertThat(throwable).isInstanceOf(DeliveryException::class.java)
                assertThat(throwable).hasMessage("Delivery distance out of range")
            }
        }

        Scenario("Sending a delivery instruction with a no default location") {
            val deliveryInstruction = "DDDAIAD"
            var coordinate: CartesianCoordinate? = null
            Given("a drone with a location different to the default one") {
                drone.currentLocation = CartesianCoordinate(-2, 4, WEST)
                droneCarrier = DroneCarrier(drone)
            }

            When("the drone is sent to the new delivery location") {
                coordinate = droneCarrier.sendItem(deliveryInstruction)
            }

            Then("it should return the correct coordinate after delivering") {
                assertThat(coordinate).isEqualTo(CartesianCoordinate(-1, 3, SOUTH))
            }
        }
    }
})
