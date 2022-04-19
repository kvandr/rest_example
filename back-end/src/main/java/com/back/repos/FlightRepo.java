package com.back.repos;


import com.back.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightRepo extends CrudRepository<Flight, Integer> {

    @Query("SELECT t FROM Flight t WHERE t.airbus LIKE %?1% " +
            "or t.route like %?1% " +
            "or t.departTime like %?1% " + "or t.travelTime like %?1%")
    List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param);

    Flight findByFlightId(Long id);

    @Query("SELECT t FROM Flight t WHERE t.airbus LIKE %?1% ")
    List<Flight> findByAirbus(String airbus);

    List<Flight> findAll();

}
