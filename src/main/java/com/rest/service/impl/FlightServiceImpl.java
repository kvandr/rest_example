package com.rest.service.impl;

import com.rest.model.Flight;
import com.rest.repos.FlightRepo;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepo flight;


    @Override
    public Flight createFlight(Flight flights) {
        return flight.save(flights);
    }

    @Override
    public List<Flight> readAll() {
        return flight.findAll();
    }

    @Override
    public Flight read(Long id) {
        return flight.findByFlightId(id);
    }

    @Override
    public Flight update(Flight flights) {
        return flight.save(flights);
    }

    @Override
    public boolean delete(Long id) {

        flight.delete(flight.findByFlightId(id));
        return true;
    }

    @Override
    public List<Flight> readSearch(String search) {
        return flight.findByAirbusOrRouteOrDepartTimeOrTravelTime(search);
    }

    @Override
    public Iterable<Flight> saveAll(Iterable<Flight> flightList) {

        return flight.saveAll(flightList);
    }

    @Override
    public void deleteAll(Iterable<Flight> flightList) {
        flight.deleteAll(flightList);
    }
}
