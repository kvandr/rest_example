package com.front.controller;

import com.front.model.Route;
import com.front.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                         @RequestParam(name="arrivalPoint") String arrivalPoint) {

        Route route = new Route(id, departPoint, arrivalPoint);
        routeService.update(route);
        return "redirect:/route";
    }

    @PostMapping(value="/filter")
    public String filter(@RequestParam(name="filter") String filter, Map<String, Object> model)
    {
        Iterable<Route> routeList;
        if (filter != null && !filter.isEmpty())
            routeList = routeService.readSearch(filter);
        else
            routeList = routeService.readAll();
        model.put("routes", routeList);
        return "route";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("routes", routeService.delete(id));
        return "redirect:/route";
    }
}
