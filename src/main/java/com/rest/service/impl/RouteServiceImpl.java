package com.rest.service.impl;

import com.rest.model.Route;
import com.rest.repos.RouteRepo;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteRepo routes;
    @Override
    public Route create(Route route) {
        return routes.save(route);
    }

    @Override
    public List<Route> readAll() {
        return routes.findAll();
    }

    @Override
    public Route read(Long id) {
        return routes.findByRouteId(id);
    }

    @Override
    public Route update(Route route) {
        return routes.save(route);
    }

    @Override
    public boolean delete(Long id) {
        routes.delete(routes.findByRouteId(id));
        return true;
    }

    @Override
    public List<Route> readSearch(String search) {
        return routes.findByDepartPointOrArrivalPoint(search);
    }

}
