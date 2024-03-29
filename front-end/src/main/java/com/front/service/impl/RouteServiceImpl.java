package com.front.service.impl;

import com.front.model.Route;
import com.front.repos.RouteClient;
import com.front.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteClient routes;
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
    public void update(Route route) {
        routes.update(route);
    }

    @Override
    public boolean delete(Long id) {
        routes.delete(id);
        return true;
    }

    @Override
    public List<Route> readSearch(String search) {
        return routes.findByDepartPointOrArrivalPoint(search);
    }

}
