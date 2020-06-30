package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.constants.MAX_BLOCKS
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate
import kotlin.math.abs

class DroneCarrier {

    var instructionTranslator = CoordinatesTranslator()

    fun sendItem(droneName: String, instructions: String): CartesianCoordinate {
        val initialCoordinate = CartesianCoordinate(0, 0, 'N')
        val deliveryCoordinate = instructionTranslator.calculateDeliveryCoordinates(initialCoordinate, instructions)
        validateCoordinates(deliveryCoordinate)

        return deliveryCoordinate
    }

    private fun validateCoordinates(cartesianCoordinates: CartesianCoordinate) {
        if (abs(cartesianCoordinates.x) > MAX_BLOCKS || abs(cartesianCoordinates.y) > MAX_BLOCKS) {
            throw DeliveryException("Delivery distance out of range")
        }
    }
}
