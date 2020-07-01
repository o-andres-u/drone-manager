package com.s4n.dronemanager.dispatcher

import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.Drone
import io.mockk.every
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object DroneDispatcherTest : Spek({

	Feature("Drone Dispatcher") {
		val droneDispatcher = spyk(DroneDispatcher())
		val drone = Drone("01")

		Scenario("Prepare a drone to deliver items") {
			var instructions = listOf<String>()

			Given("the read instructions operation should return mocked data") {
				every { droneDispatcher.readInstructions("01") } returns listOf("AAAAIAA", "DDDAIAD", "AAIADAD")
			}

			When("preparing the drone") {
				instructions = droneDispatcher.prepareDrone(drone)
			}

			Then("should return the list of instructions") {
				assertThat(instructions).hasSize(3)
				assertThat(instructions).contains("AAAAIAA", "DDDAIAD", "AAIADAD")
			}
		}


		Scenario("Try to prepare an overloaded drone") {
			var throwable: Throwable? = null

			Given("reading the instructions should return 5 lines") {
				every { droneDispatcher.readInstructions("01") } returns listOf("AAAAIAA", "DDDAIAD", "AAIADAD", "AAAIIAD", "DADADAA")
			}

			When("trying to prepare the drone") {
				throwable = catchThrowable { droneDispatcher.prepareDrone(drone) }
			}

			Then("it should throw the expected exception") {
				assertThat(throwable).isInstanceOf(DeliveryException::class.java)
				assertThat(throwable).hasMessage("Drone 01 exceeds the number of routes allowed")
			}
		}

		Scenario("Try to prepare a drone with overloaded capacity") {
			var throwable: Throwable? = null

			Given("updating the drone's capacity") {
				drone.capacity = 5
			}

			When("trying to prepare the drone") {
				throwable = catchThrowable { droneDispatcher.prepareDrone(drone) }
			}

			Then("it should throw the expected exception") {
				assertThat(throwable).isInstanceOf(DeliveryException::class.java)
				assertThat(throwable).hasMessage("Drone 01 exceeds the maximum capacity")
			}
		}
	}

})