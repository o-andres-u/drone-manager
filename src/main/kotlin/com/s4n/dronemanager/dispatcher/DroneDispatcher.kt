package com.s4n.dronemanager.dispatcher

import com.s4n.dronemanager.constants.MAX_ITEMS_PER_DRONE
import com.s4n.dronemanager.exception.DeliveryException
import com.s4n.dronemanager.model.Drone
import com.s4n.dronemanager.storage.drones

class DroneDispatcher {

    fun prepareDrone(drone: Drone) {
        // TODO read file in01.txt
        // TODO determine if fail when file contains more than 3 lines
        // TODO count the number of deliveries
        if (drone.capacity > MAX_ITEMS_PER_DRONE) {
            throw DeliveryException("Drone ${drone.id} exceeds the maximum capacity")
        }
    }

}