package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.exception.DeliveryException
import org.assertj.core.api.Assertions.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.lang.Exception

object DroneCarrierTest : Spek({

    Feature("Drone Carrier") {
        val droneCarrier by memoized { DroneCarrier() }

        Scenario("Sending a new delivery instruction") {
            val deliveryInstruction = "AAAAIAA"
            var coordinates = "(0,0,N)"
            When("sending new delivery instruction") {
                coordinates = droneCarrier.sendItem("mavic_01", deliveryInstruction)
            }

            Then("it should return the current coordinates after delivering") {
                assertThat(coordinates).isEqualTo("(-2,4,W)")
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
                assertThat(throwable).hasMessage("Max delivery blocks exceeded")
            }
        }
    }
})