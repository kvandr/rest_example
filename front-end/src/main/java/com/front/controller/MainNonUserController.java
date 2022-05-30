package com.front.controller;

import com.front.model.User;
import com.front.service.FlightService;
import com.front.service.RouteService;
import com.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainNonUserController {

    @Autowired
    private final FlightService flightService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final RouteService routeService;

    @Autowired
    public MainNonUserController(FlightService flightService, RouteService routeService,UserService userService){
        this.flightService = flightService;
        this.routeService = routeService;
        this.userService = userService;
    }

    @GetMapping(value = "/mainLocal")
    public String readAll(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "mainLocal";
    }

    @GetMapping(value = "/flightLocal")
    public String readFlight(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "flightLocal";
    }

    @GetMapping(value = "/routeLocal")
    public String readRoute(Map<String, Object> model) {
        model.put("routes", routeService.readAll());
        return "routeLocal";
    }
}
