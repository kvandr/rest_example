package com.rest.service;


import com.rest.model.Route;

public interface RouteService {
    String addRoute(String DepartPoint, String ArrivalPoint);
    Boolean updateRoute(Long id, String departPoint, String arrivalPoint);
    Boolean deleteRoute(Long idRoute);
    Boolean saveAll(Route route);
    String viewAll();
    String searchRoute(Long idRoute);
    String searchRoute(String departPoint, String arrivalPoint);
}
