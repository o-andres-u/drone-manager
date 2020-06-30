package com.s4n.dronemanager.model

import com.s4n.dronemanager.constants.MAX_ITEMS_PER_DRONE
import com.s4n.dronemanager.constants.NORTH

data class Drone(
    val id: String,
    val capacity: Int = MAX_ITEMS_PER_DRONE,
    var currentLocation: CartesianCoordinate = CartesianCoordinate(0, 0, NORTH)
)