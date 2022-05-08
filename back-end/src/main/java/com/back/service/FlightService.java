package com.back.service;

import com.back.model.Flight;
import com.back.model.Route;
import com.back.model.User;

import java.util.List;

public interface FlightService {
    void create(Flight flight, Route route, User user);

    Flight createFlight(Flight flight);

    List<Flight> readAll();

    List<Flight> readByUser(User user);

    List<Flight> readByUserAndSearch(User user, String search);

    Flight read(Long id);

    Flight update(Flight flight);

    boolean delete(Long id);

    List<Flight> readSearch(String search);

    List<Flight> readFilter(String search);

    Iterable<Flight> saveAll(Iterable<Flight> flightList);

    void deleteAll(Iterable<Flight> flightList);
}
