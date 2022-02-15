package com.rest.service;


import com.rest.model.Route;

import java.util.List;

public interface RouteService {
    Route create(Route route);

    List<Route> readAll();

    Route read(Long id);

    Route update(Route route);

    boolean delete(Long id);

    List<Route> readSearch(String search);
}
