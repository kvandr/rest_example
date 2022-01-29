package com.rest.service;


import com.rest.model.Route;

public interface RouteService {
    String addRoute(Route routeList);
    Boolean updateRoute(Long id, Route routeList);
    Boolean deleteRoute(Long idRoute);
    Boolean saveAll(Route route);
    String viewRoute();
    String searchRoute(Long idRoute);
    String searchRoute(Route routeList);
}
