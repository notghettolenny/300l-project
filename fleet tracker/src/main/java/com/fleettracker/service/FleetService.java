/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.service;

import com.fleettracker.entity.model.Fleet;
import com.fleettracker.entity.model.FleetCategory;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bruno
 */
public interface FleetService {

    Optional<Fleet> getFleetByFleetNumber(String fleetNumber);

    List<Fleet> getFleetByFleetCategory(FleetCategory fleetCategory);

    void registerFleet(Fleet fleet);

    void deleteFleet(Fleet fleet);

    void deleteFleetById(Long id);

    Optional<Fleet> getFleetById(Long id);
}
