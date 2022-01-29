package com.rest.controller;

import com.rest.model.Route;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "/route")
    public ResponseEntity<?> createRoute(@RequestBody Route routeList) {
        return new ResponseEntity<>(routeService.addRoute(routeList), HttpStatus.CREATED);
    }

    @GetMapping(value = "/route")
    public ResponseEntity<?> readAll() {
        final String routes = routeService.viewRoute();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/route/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        final String routes = routeService.searchRoute(id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    /*@GetMapping(value = "/route")
    public ResponseEntity<?> readByPoint(@RequestBody Route routeList) {
        final String route = routeService.searchRoute(routeList);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }*/

    @PutMapping(value = "/route/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  //consumes принимает фиксируемый формат данных
            produces = MediaType.APPLICATION_JSON_VALUE)  //produces возвращает фиксируемый формат данных
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Route routeList) {
        final boolean updated = routeService.updateRoute(id, routeList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/route/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = routeService.deleteRoute(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
