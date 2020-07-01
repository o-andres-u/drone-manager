package com.s4n.dronemanager.dispatcher

import com.s4n.dronemanager.carrier.DroneCarrier
import com.s4n.dronemanager.constants.MAX_ITEMS_PER_DRONE
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate
import com.s4n.dronemanager.model.Drone
import java.io.File

class DroneDispatcher {

    fun prepareDrone(drone: Drone): List<String> {
        if (drone.capacity > MAX_ITEMS_PER_DRONE) {
            throw DeliveryException("Drone ${drone.id} exceeds the maximum capacity")
        }

        val instructions = readInstructions(drone.id)
        if (instructions.size > MAX_ITEMS_PER_DRONE) {
            throw DeliveryException("Drone ${drone.id} exceeds the number of routes allowed")
        }

        return instructions
    }

    fun readInstructions(droneId: String): List<String> {
        return File("src/main/resources/in$droneId.txt").readLines()
    }

    fun dispatch(drone: Drone, instructions: List<String>) {
        println("Drone ${drone.id} starting...")
        val droneCarrier = DroneCarrier(drone)
        instructions.forEach {
            val coordinate = droneCarrier.sendItem(it)
            printReport(drone.id, coordinate)
        }
        println("Drone ${drone.id} finished...")
    }

    private fun printReport(droneId: String, coordinate: CartesianCoordinate) {
        val file = File("src/main/resources/out$droneId.txt")
        file.appendText(coordinate.toString())
        file.appendText("\n")
    }

}