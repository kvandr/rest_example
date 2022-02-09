package com.rest.service;


import com.rest.model.Route;

import java.util.List;

public interface RouteService {
    Route create(Route route);

    List<Route> readAll();

    Route read(Long id);

    boolean update(Route route, Long id);

    boolean delete(Long id);

    Route readByDepartTimeOrArrivalTime(String departTime, String arrivalTime);
}
