package com.rest.controller;

import com.rest.model.Route;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Route route) {
        return new ResponseEntity<>(routeService.create(route), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Route>> readAll() {
        final List<Route> routes = routeService.readAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/route/{id}")
    public ResponseEntity<Route> read(@PathVariable(name = "id") Long id) {
        final Route routes = routeService.read(id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/route/search")
    public ResponseEntity<Route> readByPoint(@RequestParam String departPoint, @RequestParam String arrivalPoint) {
        final Route route = routeService.readByPoint(departPoint, arrivalPoint);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping(value = "/route/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  //consumes принимает фиксируемый формат данных
            produces = MediaType.APPLICATION_JSON_VALUE)  //produces возвращает фиксируемый формат данных
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Route route) {
        final boolean updated = routeService.update(route, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/route/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = routeService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
