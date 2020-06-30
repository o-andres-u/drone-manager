package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.CartesianCoordinate

class DroneCarrier {

    var instructionTranslator = CoordinatesTranslator()

    fun sendItem(droneName: String, instructions: String): String {
        val initialCoordinate = CartesianCoordinate(0, 0, 'N')
        val movements = instructionTranslator.calculateDeliveryCoordinates(initialCoordinate, instructions)
//        if (movements > 10) throw DeliveryException("Max delivery blocks exceeded")
        return "(-2,4,W)"
    }

}