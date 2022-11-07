/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.controller;

import com.fleettracker.ApiResponse;
import com.fleettracker.entity.model.Fleet;
import com.fleettracker.entity.model.FleetCategory;
import com.fleettracker.exceptions.ResourceException;
import com.fleettracker.rest.model.FleetRest;
import com.fleettracker.service.FleetService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bruno
 */
@RestController
@RequestMapping("/fleettracker/api/fleet")
public class FleetController {

    @Autowired
    private FleetService fleetService;

    @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<FleetRest>> register(@RequestBody FleetRest fleetRest) {

        if (StringUtils.isEmpty(fleetRest.getFleetNumber()) || StringUtils.isEmpty(fleetRest.getFleetCategory())) {
            throw new ResourceException("Missing required details.");
        }

        String fleetNumber = fleetRest.getFleetNumber();
        Optional<Fleet> optFleet = fleetService.getFleetByFleetNumber(fleetNumber);

        if (optFleet.isPresent()) {
            throw new ResourceException("Fleet already exist.");
        }

        Fleet fleet = new Fleet();
        fleet.setActive(Boolean.TRUE);
        fleet.setAssignedDriver(fleetRest.getAssignedDriver());
        fleet.setColour(fleetRest.getColour());
        fleet.setFleetNumber(fleetNumber);
        FleetCategory fleetCategory = Stream.of(FleetCategory.values()).filter(f -> f.category().equalsIgnoreCase(fleetRest.getFleetCategory())).findFirst().get();
        fleet.setFleetCategory(fleetCategory);

        try {
            fleetService.registerFleet(fleet);
            ApiResponse<FleetRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleet registered successfully");
            apiResponse.setData(fleetRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<FleetRest>> update(@RequestBody FleetRest fleetRest) {

        if (StringUtils.isEmpty(fleetRest.getId()) || StringUtils.isEmpty(fleetRest.getFleetCategory())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = fleetRest.getId();
        Optional<Fleet> optFleet = fleetService.getFleetById(id);

        if (!optFleet.isPresent()) {
            throw new ResourceException("Fleet does not exist.");
        }

        Fleet fleet = optFleet.get();
        fleet.setAssignedDriver(fleetRest.getAssignedDriver());
        fleet.setColour(fleetRest.getColour());
        FleetCategory fleetCategory = Stream.of(FleetCategory.values()).filter(f -> f.category().equalsIgnoreCase(fleetRest.getFleetCategory())).findFirst().get();
        fleet.setFleetCategory(fleetCategory);

        try {
            fleetService.registerFleet(fleet);
            ApiResponse<FleetRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleet updated successfully");
            apiResponse.setData(fleetRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/deactivate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<FleetRest>> deactivate(@RequestBody FleetRest fleetRest) {

        if (StringUtils.isEmpty(fleetRest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = fleetRest.getId();
        Optional<Fleet> optFleet = fleetService.getFleetById(id);

        if (!optFleet.isPresent()) {
            throw new ResourceException("Fleet does not exist.");
        }

        Fleet fleet = optFleet.get();
        fleet.setActive(Boolean.FALSE);

        try {
            fleetService.registerFleet(fleet);
            ApiResponse<FleetRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleet deactivated successfully");
            apiResponse.setData(fleetRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/delete", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<FleetRest>> delete(@RequestBody FleetRest fleetRest) {

        if (StringUtils.isEmpty(fleetRest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = fleetRest.getId();
        Optional<Fleet> optFleet = fleetService.getFleetById(id);

        if (!optFleet.isPresent()) {
            throw new ResourceException("Fleet does not exist.");
        }

        Fleet fleet = optFleet.get();

        try {
            fleetService.deleteFleet(fleet);
            ApiResponse<FleetRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleet deleted successfully");
            apiResponse.setData(fleetRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @GetMapping(path = "/getFleetByCategory", produces = "application/json")
    public ResponseEntity<ApiResponse<List<Fleet>>> getFleetByCategory(@RequestParam String category) {

        if (StringUtils.isEmpty(category)) {
            throw new ResourceException("Missing required detail.");
        }
        FleetCategory fleetCategory = Stream.of(FleetCategory.values()).filter(f -> f.category().equalsIgnoreCase(category)).findFirst().get();

        try {
            List<Fleet> fleets = fleetService.getFleetByFleetCategory(fleetCategory);
            ApiResponse<List<Fleet>> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleets retrieved successfully");
            apiResponse.setData(fleets);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @GetMapping(path = "/getFleetByFleetNumber", produces = "application/json")
    public ResponseEntity<ApiResponse<Fleet>> getFleetByFleetNumber(@RequestParam String fleetNumber) {

        if (StringUtils.isEmpty(fleetNumber)) {
            throw new ResourceException("Missing required detail.");
        }

        try {
            Optional<Fleet> optFleet = fleetService.getFleetByFleetNumber(fleetNumber);
            ApiResponse<Fleet> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Fleet retrieved successfully");
            apiResponse.setData(optFleet.orElse(null));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

}
