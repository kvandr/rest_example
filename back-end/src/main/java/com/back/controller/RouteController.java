package com.back.controller;


import com.back.model.Route;
import com.back.service.RouteService;
import lombok.extern.slf4j.Slf4j;
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
        return new ResponseEntity<>(routeService.readAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/route/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(routeService.read(id), HttpStatus.OK);
    }

    @GetMapping(value = "/routeSearch/{param}")
    public ResponseEntity<?> search(@PathVariable(name = "param") String param) {
        return new ResponseEntity<>(routeService.readSearch(param), HttpStatus.OK);
    }


    @PutMapping(value = "/route/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  //consumes принимает фиксируемый формат данных
            produces = MediaType.APPLICATION_JSON_VALUE)  //produces возвращает фиксируемый формат данных
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Route route) {
        routeService.update(route);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/route/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        routeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
