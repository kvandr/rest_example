package com.front.service;

import com.front.model.Route;

import java.util.List;

public interface RouteService {
    Route create(Route route);

    List<Route> readAll();

    Route read(Long id);

    void update(Route route);

    boolean delete(Long id);

    List<Route> readSearch(String search);

}
