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
    public ResponseEntity<?> createFlight (
            @RequestParam String airbus,
            @RequestParam Long route,
            @RequestParam String departTime,
            @RequestParam String travelTime) {
        return new ResponseEntity<>(flightService.addFlight(airbus, route, departTime, travelTime), HttpStatus.OK);
    }

    @PutMapping("/flight")
    public  ResponseEntity<?> saveAll(@RequestBody Flight flightList){
        return new ResponseEntity<>(flightService.saveAll(flightList), HttpStatus.OK);
    }

    @GetMapping(value = "/flight")
    public ResponseEntity<?> readAll() {
        final String flights = flightService.viewAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        final String flight = flightService.searchFlight(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{search}")
    public ResponseEntity<?> readSearch(@PathVariable(name = "search") String search) {
        final String flight = flightService.searchFlight(search);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping(value = "/flight")
    public ResponseEntity<?> update(
            @RequestParam Long idFlight,
            @RequestParam String airbus,
            @RequestParam Long route,
            @RequestParam String departTime,
            @RequestParam String travelTime) {
        final Boolean updated = flightService.updateFlight(idFlight, airbus, route, departTime, travelTime);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/flight/{id}")
    public ResponseEntity<?> deleteId(@PathVariable(name = "id") Long id) {
        final boolean deleted = flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/flight")
    public ResponseEntity<?> delete(@RequestBody List<Flight> flightList) {
        flightService.deleteFlight(flightList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
