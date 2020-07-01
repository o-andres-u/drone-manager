package com.s4n.dronemanager.model

import com.s4n.dronemanager.constants.EAST
import com.s4n.dronemanager.constants.NORTH
import com.s4n.dronemanager.constants.SOUTH
import com.s4n.dronemanager.constants.WEST

data class CartesianCoordinate(val x: Int = 0, val y: Int = 0, val orientation: Char = NORTH) {

    override fun toString(): String {
        var orientationString: String? = null
        when(orientation) {
            NORTH -> orientationString = "Norte"
            SOUTH -> orientationString = "Sur"
            EAST -> orientationString = "Oriente"
            WEST -> orientationString = "Occidente"
        }
        return "($x,$y) dirección $orientationString"
    }

}