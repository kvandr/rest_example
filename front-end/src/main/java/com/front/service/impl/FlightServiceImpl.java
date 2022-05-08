package com.front.service.impl;

import com.front.model.Flight;
import com.front.repos.FlightClient;
import com.front.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightClient flight;

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
    public void update(Flight flights) {
        flight.update(flights);
    }

    @Override
    public boolean delete(Long id) {
        flight.delete(id);
        return true;
    }

    @Override
    public List<Flight> readSearch(String search) {
        return flight.findByAirbusOrRouteOrDepartTimeOrTravelTime(search);
    }

    @Override
    public List<Flight> readFilter(String search) {
        return flight.findByAirbus(search);
    }
}
