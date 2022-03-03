package com.rest.repos;


import com.rest.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepo extends CrudRepository<Flight, Integer> {

    @Query("SELECT t FROM Flight t WHERE LOWER(t.airbus) LIKE %?1% " +
            "or LOWER(t.route) like %?1% " +
            "or LOWER(t.departTime) like %?1% " + "or LOWER(t.travelTime) like %?1%")
    List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param);

    Flight findByFlightId(Long id);

    List<Flight> findByAirbus(String airbus);

    List<Flight> findAll();

}
