package com.s4n.dronemanager.carrier

import com.s4n.dronemanager.constants.*
import com.s4n.dronemanager.model.CartesianCoordinate
import java.lang.IllegalArgumentException

class CoordinatesTranslator {

    fun calculateDeliveryCoordinates(initialCoordinate: CartesianCoordinate, instruction: String): CartesianCoordinate {
        var x = initialCoordinate.x
        var y = initialCoordinate.y
        var orientation = initialCoordinate.orientation
        instruction.forEach { action ->
            when(action) {
                FORWARDS -> {
                    when(orientation) {
                        NORTH -> y++
                        SOUTH -> y--
                        EAST -> x++
                        WEST -> x--
                    }
                }
                TURN_RIGHT -> {
                    when(orientation) {
                        NORTH -> orientation = EAST
                        EAST -> orientation = SOUTH
                        SOUTH -> orientation = WEST
                        WEST -> orientation = NORTH
                    }
                }
                TURN_LEFT -> {
                    when(orientation) {
                        NORTH -> orientation = WEST
                        WEST -> orientation = SOUTH
                        SOUTH -> orientation = EAST
                        EAST -> orientation = NORTH
                    }
                }
                else -> throw IllegalArgumentException("Incorrect action: $action")
            }
        }
        return CartesianCoordinate(x, y, orientation)
    }
}
