package com.rest.controller;

import com.rest.model.Route;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "/create")
    public String createRoute(@RequestParam(name="departPoint") String departPoint,
                              @RequestParam(name="arrivalPoint") String arrivalPoint,
                              Map<String, Object> model) {
        Route route = new Route(departPoint, arrivalPoint);
        model.put("routes", routeService.create(route));
        return "redirect:/route";
    }

    @GetMapping
    /*public ResponseEntity<?> readAll() {
        final List<Route> route = routeService.readAll();
        return new ResponseEntity<>(route, HttpStatus.OK);
    }*/
    public String readAll(Map<String, Object> model) {
        model.put("routes", routeService.readAll());
        return "route";
    }

    @GetMapping(value = "oneRoute/{id}")
    public String read(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.read(id));
        return "oneRoute";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable(name = "id") Long id,
                         @RequestParam(name="departPoint") String departPoint,
                         @RequestParam(name="arrivalPoint") String arrivalPoint,
                         Map<String, Object> model) {

        Route route = new Route(id, departPoint, arrivalPoint);
        model.put("routes", routeService.update(route));
        return "redirect:/route";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.delete(id));
        return "redirect:/route";
    }

    /*@DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.delete(id));
        return "route";
    }*/
}
