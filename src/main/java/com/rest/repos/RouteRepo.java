package com.rest.repos;

import com.rest.Exception.NotFoundException;
import com.rest.model.Route;

import java.util.List;

public interface RouteRepo{
    Boolean save(Route route);
    Boolean delete(Route route);
    Boolean fileUnload(List<Route> list);
    List<Route> fileLoad();
    Route getRouteDepart(String departPoint, String arrivalPoint) throws NotFoundException;
    Route getRouteDepart(Long id) throws NotFoundException;
}
