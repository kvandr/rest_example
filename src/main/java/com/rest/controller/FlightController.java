package com.rest.controller;

import com.rest.model.Flight;
import com.rest.model.Route;
import com.rest.service.RouteService;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/flight")
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

    @PostMapping(value = "/create")
    public String createFlight (@RequestParam(name="airbus") String airbus,
                                @RequestParam(name="route") Long route,
                                @RequestParam(name="departTime") Date departTime,
                                @RequestParam(name="arrivalTime") Date arrivalTime,
                                Map<String, Object> model) {
        Route routeList = routeService.read(route);
        Flight flight = new Flight(airbus,routeList,departTime,arrivalTime);
        model.put("flights", flightService.createFlight(flight));
        return "redirect:/flight";
    }

    @PostMapping(value = "/update")
    public String update(@RequestParam(name="flightId") Long flightId,
                         @RequestParam(name="airbus") String airbus,
                         @RequestParam(name="route") Long route,
                         @RequestParam(name="departTime") Date departTime,
                         @RequestParam(name="arrivalTime") Date arrivalTime,
                         Map<String, Object> model){
        Route routeList = routeService.read(route);
        Flight flight = new Flight(airbus,routeList,departTime,arrivalTime);
        flight.setFlightId(flightId);
        model.put("flights", flightService.update(flight));
        return "redirect:/flight";
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


    @GetMapping(value = "oneFlight/{id}")
    public String read(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("flights", flightService.read(id));
        return "oneFlight";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("flights", flightService.delete(id));
        return "redirect:/flight";
    }

    /*@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteId(@PathVariable(name = "id") Long id) {
        final boolean deleted = flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
