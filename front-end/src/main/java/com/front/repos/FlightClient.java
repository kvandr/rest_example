package com.front.repos;

import com.front.model.Flight;

import java.util.List;

public interface FlightClient {

    List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param);

    Flight findByFlightId(Long id);

    Flight save(Flight flight);

    void update(Flight flight);

    List<Flight> findByAirbus(String airbus);

    List<Flight> findAll();

    void delete(Long id);
}
