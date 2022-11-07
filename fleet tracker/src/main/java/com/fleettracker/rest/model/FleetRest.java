/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.rest.model;

import com.fleettracker.entity.model.FleetCategory;

/**
 *
 * @author Bruno
 */
public class FleetRest {

    private Long id;

    private String fleetNumber;

    private String fleetCategory;

    private String colour;

    private String assignedDriver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFleetNumber() {
        return fleetNumber;
    }

    public void setFleetNumber(String fleetNumber) {
        this.fleetNumber = fleetNumber;
    }

    public String getFleetCategory() {
        return fleetCategory;
    }

    public void setFleetCategory(String fleetCategory) {
        this.fleetCategory = fleetCategory;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(String assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    @Override
    public String toString() {
        return "FleetRest{" + "id=" + id + ", fleetNumber=" + fleetNumber + ", fleetCategory=" + fleetCategory + ", colour=" + colour + ", assignedDriver=" + assignedDriver + '}';
    }

}
