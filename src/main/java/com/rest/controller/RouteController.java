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

    @PostMapping
    public ResponseEntity<?> createRoute(@RequestBody Route routeList) {
        return new ResponseEntity<>(routeService.create(routeList), HttpStatus.CREATED);
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

    @GetMapping(value = "/{id}")
    public String read(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.read(id));
        return "route";
    }

    @PostMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  //consumes принимает фиксируемый формат данных
            produces = MediaType.APPLICATION_JSON_VALUE)  //produces возвращает фиксируемый формат данных
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Route route) {
        route.setRouteId(id);
        final Route updated = routeService.update(route);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.delete(id));
        return "route";
    }

    /*@DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.delete(id));
        return "route";
    }*/
}
