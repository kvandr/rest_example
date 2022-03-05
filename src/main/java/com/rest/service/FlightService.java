package com.rest.service;
import com.rest.model.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);

    List<Flight> readAll();

    Flight read(Long id);

    Flight update(Flight flight);

    boolean delete(Long id);

    List<Flight> readSearch(String search);

    List<Flight> readFilter(String search);

    Iterable<Flight> saveAll(Iterable<Flight> flightList);

    void deleteAll(Iterable<Flight> flightList);
}
