package com.back.controller;


import com.back.model.Flight;
import com.back.service.FlightService;
import com.back.service.RouteService;
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

    @PutMapping("/flight/{id}")
    public  ResponseEntity<?> saveAll(@RequestBody Flight flightList){
        return new ResponseEntity<>(flightService.update(flightList), HttpStatus.OK);
    }

    @GetMapping(value = "/flight")
    public ResponseEntity<?> readAll() {
        final List<Flight> flights = flightService.readAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/flightSearch/{param}")
    public ResponseEntity<?> search(@PathVariable(name = "param") String param) {
        final List<Flight> flights = flightService.readSearch(param);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/flightAirbusSearch/{param}")
    public ResponseEntity<?> searchAirbus(@PathVariable(name = "param") String param) {
        final List<Flight> flights = flightService.readFilter(param);
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
