/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.service;

import com.fleettracker.entity.model.Fleet;
import com.fleettracker.entity.model.FleetCategory;
import com.fleettracker.repositories.FleetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno
 */
@Service
public class FleetServiceImpl implements FleetService {

    @Autowired
    FleetRepository fleetRepository;

    @Override
    public Optional<Fleet> getFleetByFleetNumber(String fleetNumber) {
        return fleetRepository.findFleetByFleetNumber(fleetNumber);
    }

    @Override
    public List<Fleet> getFleetByFleetCategory(FleetCategory fleetCategory) {
        return fleetRepository.findByFleetCategory(fleetCategory);
    }

    @Override
    public void registerFleet(Fleet fleet) {
        fleetRepository.save(fleet);
    }

    @Override
    public void deleteFleet(Fleet fleet) {
        fleetRepository.delete(fleet);
    }

    @Override
    public void deleteFleetById(Long id) {
        fleetRepository.deleteById(id);
    }

    @Override
    public Optional<Fleet> getFleetById(Long id) {
        return fleetRepository.findById(id);
    }

}
