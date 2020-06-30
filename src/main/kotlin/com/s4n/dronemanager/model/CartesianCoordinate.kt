package com.s4n.dronemanager.model

data class CartesianCoordinate(val x: Int = 0, val y: Int = 0, val orientation: Char = 'N') {

    override fun toString(): String {
        return "($x,$y,$orientation)"
    }

}