package com.rest.service.impl;

import com.rest.model.Flight;
import com.rest.repos.FlightRepo;
import com.rest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Override
    public void create(Flight flight, Long route) {
        flight.setRoute(route);
        flightRepo.save(flight);
    }

    @Override
    public Flight createTrack(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public List<Flight> readAll() {
        return flightRepo.findAll();
    }

    @Override
    public Flight read(Long id) {
        return flightRepo.findById(id);
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public boolean delete(Long id) {

        flightRepo.delete(flightRepo.findById(id));
        return true;
    }

    @Override
    public List<Flight> readSearch(String search) {
        return flightRepo.findByAnyParamFlight(search);
    }

    @Override
    public Iterable<Flight> saveAll(Iterable<Flight> flightList) {

        return flightRepo.saveAll(flightList);
    }

    @Override
    public void deleteAll(Iterable<Flight> flightList) {
        flightRepo.deleteAll(flightList);
    }
}
