/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.entity.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author lenny
 */
@Entity
public class Fleet extends AuditModel implements Serializable {

    @Column(name = "fleet_number", nullable = false)
    private String fleetNumber;

    @Column(name = "fleet_category", nullable = false)
    private FleetCategory fleetCategory;

    @Column(name = "colour")
    private String colour;

    @Column(name = "assigned_driver")
    private String assignedDriver;

    public String getFleetNumber() {
        return fleetNumber;
    }

    public void setFleetNumber(String fleetNumber) {
        this.fleetNumber = fleetNumber;
    }

    public FleetCategory getFleetCategory() {
        return fleetCategory;
    }

    public void setFleetCategory(FleetCategory fleetCategory) {
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

}
