package com.front.repos;

import com.front.model.Route;
import java.util.List;

public interface RouteClient {

    List <Route> findByDepartPointOrArrivalPoint(String param);

    Route findByRouteId(Long id);

    Route save(Route route);

    void update(Route route);

    List<Route> findAll();

    void delete(Long id);
}
