package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.constants.MAX_BLOCKS
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate
import com.s4n.dronemanager.model.Drone
import kotlin.math.abs

class DroneCarrier(private val drone: Drone) {
    var instructionTranslator = CoordinatesTranslator()

    fun sendItem(instructions: String): CartesianCoordinate {
        val deliveryCoordinate = instructionTranslator.calculateDeliveryCoordinates(drone.currentLocation, instructions)
        validateCoordinates(deliveryCoordinate)

        return deliveryCoordinate
    }

    private fun validateCoordinates(cartesianCoordinates: CartesianCoordinate) {
        if (abs(cartesianCoordinates.x) > MAX_BLOCKS || abs(cartesianCoordinates.y) > MAX_BLOCKS) {
            throw DeliveryException("Delivery distance out of range")
        }
    }
}
