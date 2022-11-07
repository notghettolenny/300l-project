/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fleettracker.controller;

import com.fleettracker.ApiResponse;
import com.fleettracker.rest.model.FleetRest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Bruno
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FleetControllerTest {

    public FleetControllerTest() {
    }

    MockMvc mockMvc;

    @Mock
    private FleetController fleetController;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(fleetController).build();
    }

    /**
     * Test of register method, of class FleetController.
     */
    @Test
    public void testRegister() {
        FleetRest fleetRest = new FleetRest();
        fleetRest.setAssignedDriver("Okafor");
        fleetRest.setColour("Black");
        fleetRest.setFleetCategory("Car");
        fleetRest.setFleetNumber("XO895ENU");

        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> fleet = getHttpEntity(fleetRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/fleettracker/api/fleet/register", fleet, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());

    }

    @Test
    public void testRegisterFleetExist() {
        FleetRest fleetRest = new FleetRest();
        fleetRest.setAssignedDriver("Okafor");
        fleetRest.setColour("Black");
        fleetRest.setFleetCategory("Car");
        fleetRest.setFleetNumber("XO895ENU");

        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.BAD_REQUEST);

        HttpEntity<Object> fleet = getHttpEntity(fleetRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/fleettracker/api/fleet/register", fleet, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());

    }

    /**
     * Test of update method, of class FleetController.
     */
    @Test
    public void testUpdate() {
        FleetRest fleetRest = new FleetRest();
        fleetRest.setAssignedDriver("Okafor");
        fleetRest.setColour("Black");
        fleetRest.setFleetCategory("Car");
        fleetRest.setId(2L);

        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> fleet = getHttpEntity(fleetRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/fleettracker/api/fleet/update", fleet, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    /**
     * Test of deactivate method, of class FleetController.
     */
    @Test
    public void testDeactivate() {
        FleetRest fleetRest = new FleetRest();
        fleetRest.setId(2L);

        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> fleet = getHttpEntity(fleetRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/fleettracker/api/fleet/deactivate", fleet, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    /**
     * Test of delete method, of class FleetController.
     */
    @Test
    public void testDelete() {
        FleetRest fleetRest = new FleetRest();
        fleetRest.setId(2L);

        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> fleet = getHttpEntity(fleetRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/fleettracker/api/fleet/delete", fleet, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    /**
     * Test of getFleetByCategory method, of class FleetController.
     */
    @Test
    public void testGetFleetByCategory() {
        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        ResponseEntity<?> response = template.getForEntity(
                "/fleettracker/api/fleet/getFleetByCategory?category=Car", Object.class);
        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    /**
     * Test of getFleetByFleetNumber method, of class FleetController.
     */
    @Test
    public void testGetFleetByFleetNumber() {
        ResponseEntity<ApiResponse<FleetRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        ResponseEntity<?> response = template.getForEntity(
                "/fleettracker/api/fleet/getFleetByFleetNumber?fleetNumber=XP8950LOS", Object.class);
        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
