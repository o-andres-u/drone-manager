package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object DroneCarrierTest : Spek({

    Feature("Drone Carrier") {
        val droneCarrier by memoized { DroneCarrier() }

        Scenario("Sending a new delivery instruction") {
            val deliveryInstruction = "AAAAIAA"
            var coordinate: CartesianCoordinate? = null
            When("sending new delivery instruction") {
                coordinate = droneCarrier.sendItem("mavic_01", deliveryInstruction)
            }

            Then("it should return the current coordinate after delivering") {
                assertThat(coordinate.toString()).isEqualTo("(-2,4,W)")
            }
        }

        Scenario("Sending a delivery instruction out of range") {
            val deliveryInstruction = "AAAAAAAAAAA"
            var throwable: Throwable? = null
            When("sending the delivery instruction") {
                throwable = catchThrowable { droneCarrier.sendItem("mavic_01", deliveryInstruction) }
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
                throwable = catchThrowable { droneCarrier.sendItem("mavic_01", deliveryInstruction) }
            }

            Then("it should throw the expected exception") {
                assertThat(throwable).isInstanceOf(DeliveryException::class.java)
                assertThat(throwable).hasMessage("Delivery distance out of range")
            }
        }
    }
})