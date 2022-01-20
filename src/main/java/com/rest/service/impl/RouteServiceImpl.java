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
    private RouteRepo routeRepo;

    @Override
    public Route create(Route route) {
        return routeRepo.save(route);
    }

    @Override
    public List<Route> readAll() {
        return routeRepo.findAll();
    }

    @Override
    public Route read(Long id) {
        return routeRepo.findById(id);
    }

    @Override
    public boolean update(Route route, Long id) {
        routeRepo.save(route);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        routeRepo.delete(routeRepo.findById(id));
        return true;
    }

    @Override
    public Route readByPoint(String departPoint, String arrivalPoint) {
        return routeRepo.findByNameRoute(departPoint, arrivalPoint);
    }
}
