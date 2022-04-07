package com.front.service;

import com.front.model.Flight;

import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);

    List<Flight> readAll();

    Flight read(Long id);

    void update(Flight flight);

    boolean delete(Long id);

    List<Flight> readSearch(String search);

    List<Flight> readFilter(String search);
}
