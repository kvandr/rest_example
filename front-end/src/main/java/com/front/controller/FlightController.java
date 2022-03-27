package com.front.controller;

import com.back.model.Flight;
import com.back.model.Route;
import com.back.service.RouteService;
import com.back.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable(name = "id") Long id,
                         @RequestParam(name="airbus") String airbus,
                         @RequestParam(name="route") Long route,
                         @RequestParam(name="departTime") Date departTime,
                         @RequestParam(name="arrivalTime") Date arrivalTime,
                         Map<String, Object> model){
        Route routeList = routeService.read(route);
        Flight flight = new Flight(id, airbus,routeList,departTime,arrivalTime);
        model.put("flights", flightService.update(flight));
        return "redirect:/flight";
    }

    @PostMapping(value="/filter")
    public String filter(@RequestParam(name="filter") String filter, Map<String, Object> model)
    {
        Iterable<Flight> flightList;
        if (filter != null && !filter.isEmpty())
            flightList = flightService.readFilter(filter);
        else
            flightList = flightService.readAll();
        model.put("flights", flightList);
        return "flight";
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