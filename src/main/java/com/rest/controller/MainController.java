package com.rest.controller;

import com.rest.model.Flight;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private final FlightService flightService;


    @Autowired
    public MainController(FlightService flightService) {this.flightService = flightService;}

    @GetMapping
    public String readAll(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "show";
    }

    /*@PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model)
    {
        Iterable<Flight> flightList;
        if (filter != null && !filter.isEmpty())
            flightList = flightService.readAirbus(filter);
        else
            flightList = flightService.readAll();
        model.put("filter", flightList);
        return "show";
    }*/

}
