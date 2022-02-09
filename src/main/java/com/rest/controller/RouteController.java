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
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "/route")
    public ResponseEntity<?> createRoute(@RequestBody Route routeList) {
        return new ResponseEntity<>(routeService.create(routeList), HttpStatus.CREATED);
    }

    @GetMapping(value = "/route")
    public ResponseEntity<?> readAll() {
        final List<Route> route = routeService.readAll();
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping(value = "/route/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        final Route route = routeService.read(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }


    @PutMapping(value = "/route/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  //consumes принимает фиксируемый формат данных
            produces = MediaType.APPLICATION_JSON_VALUE)  //produces возвращает фиксируемый формат данных
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Route routeList) {
        final boolean updated = routeService.update(routeList, id);
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
