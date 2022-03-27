package com.back.repos;

import com.back.model.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepo extends CrudRepository<Route, Integer> {

    @Query("SELECT t FROM Route t WHERE t.departPoint LIKE %?1% " +
            "or t.arrivalPoint like %?1% ")
    List <Route> findByDepartPointOrArrivalPoint(String param);

    Route findByRouteId(Long id);
    List<Route> findAll();
}
