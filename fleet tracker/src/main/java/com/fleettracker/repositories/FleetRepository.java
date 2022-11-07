/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.repositories;

import com.fleettracker.entity.model.Fleet;
import com.fleettracker.entity.model.FleetCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Bruno
 */
public interface FleetRepository extends CrudRepository<Fleet, Long> {

    Optional<Fleet> findFleetByFleetNumber(String fleetNumber);

    List<Fleet> findByFleetCategory(FleetCategory fleetCategory);

}
