package com.rest.controller;

import com.rest.model.Flight;
import com.rest.service.RouteService;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private final FlightService flightService;


    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createFlight (@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                     @RequestBody Flight flight){
        flight.setFlightId(id);
        final Flight updated = flightService.update(flight);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    /*public ResponseEntity<?> readAll() {
        final List<Flight> flights = flightService.readAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }*/
    public String readAll(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "flight";
    }


    @GetMapping(value = "/{id}")
    public String read(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("flights", flightService.read(id));
        return "flight";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("flights", flightService.delete(id));
        return "flight";
    }

    /*@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteId(@PathVariable(name = "id") Long id) {
        final boolean deleted = flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
