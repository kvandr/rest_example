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
    public ResponseEntity<?> createTrack (
            @RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.createTrack(flight), HttpStatus.OK);
    }

    @PutMapping("/flight")
    public  ResponseEntity<?> saveAll(@RequestBody List<Flight> flightList){
        return new ResponseEntity<>(flightService.saveAll(flightList), HttpStatus.OK);
    }

    @GetMapping(value = "/flight")
    public ResponseEntity<List<Flight>> readAll() {
        final List<Flight> flights = flightService.readAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{id}")
    public ResponseEntity<Flight> read(@PathVariable(name = "id") Long id) {
        final Flight flight = flightService.read(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{search}")
    public ResponseEntity<List<Flight>> readSearch(@PathVariable(name = "search") String search) {
        final List<Flight> flight = flightService.readSearch(search);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping(value = "/flight")
    public ResponseEntity<?> update(
            @RequestBody Flight flight) {
        final Flight updated = flightService.update(flight);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/flight/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/flight")
    public ResponseEntity<?> deleteAll(@RequestBody List<Flight> flightList) {
        flightService.deleteAll(flightList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
