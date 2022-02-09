package com.rest.controller;

import com.rest.model.Flight;
import com.rest.service.RouteService;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private final FlightService flightService;
    @Autowired
    private final RouteService routeService;


    @Autowired
    public FlightController(FlightService flightService, RouteService routeService) {

        this.flightService = flightService;
        this.routeService = routeService;
    }

    @PostMapping(value = "/flight")
    public ResponseEntity<?> createFlight (@RequestBody Flight flightList) {
        return new ResponseEntity<>(flightService.createFlight(flightList), HttpStatus.OK);
    }

    @PutMapping("/flight")
    public  ResponseEntity<?> update(@RequestBody Flight flightList){
        final Flight updated = flightService.update(flightList);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping(value = "/flight")
    public ResponseEntity<?> readAll() {
        final List<Flight> flights = flightService.readAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        final Flight flight = flightService.read(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/flight/{id}")
    public ResponseEntity<?> deleteId(@PathVariable(name = "id") Long id) {
        final boolean deleted = flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
