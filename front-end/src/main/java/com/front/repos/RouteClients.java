package com.front.repos;

import com.front.model.Route;
import java.util.List;

public interface RouteClients {

    public List <Route> findByDepartPointOrArrivalPoint(String param);

    public Route findByRouteId(Long id);

    public Route save(Route route);

    public List<Route> findAll();

    public void delete(Long id);
}
