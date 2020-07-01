package com.s4n.dronemanager.app

import com.s4n.dronemanager.dispatcher.DroneDispatcher
import com.s4n.dronemanager.model.Drone
import com.s4n.dronemanager.repository.drones
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val droneDispatcher = DroneDispatcher()

fun main() = runBlocking {
    drones.forEach {
        launch {
            launch(it)
        }
    }
}

private fun launch(drone: Drone) {
    val instructions = droneDispatcher.prepareDrone(drone)
    droneDispatcher.dispatch(drone, instructions)
}
