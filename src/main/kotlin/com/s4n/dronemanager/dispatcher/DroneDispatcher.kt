package com.s4n.dronemanager.dispatcher

import com.s4n.dronemanager.constants.MAX_ITEMS_PER_DRONE
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.Drone

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
        return listOf()
    }

}