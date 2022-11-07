/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.entity.model;

/**
 *
 * @author Bruno
 */
public enum FleetCategory {
    CAR("Car"),
    TRUCK("Truck"),
    BUS("Bus");

    private final String category;

    FleetCategory(String category) {
        this.category = category;
    }

    public String category() {
        return category;
    }
}
