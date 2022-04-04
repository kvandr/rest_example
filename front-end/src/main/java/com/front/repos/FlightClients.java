package com.front.repos;

import com.front.model.Flight;

import java.util.List;

public interface FlightClients {

    public List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param);

    public Flight findByFlightId(Long id);

    public Flight save(Flight flight);

    public List<Flight> findByAirbus(String airbus);

    public List<Flight> findAll();

    public void delete(Long id);
}
