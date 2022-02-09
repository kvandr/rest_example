package com.rest.repos;

import com.rest.model.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepo extends CrudRepository<Route, Integer> {
    Route findById(Long id);
    Route findByDepartTimeOrArrivalTime(String departTime, String arrivalTime);
    List<Route> findAll();
}
