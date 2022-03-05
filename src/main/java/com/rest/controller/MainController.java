package com.rest.controller;

import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
