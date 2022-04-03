package com.front.service.impl;

import com.front.model.Flight;
import com.front.model.Route;
import com.front.repos.FlightRepo;
import com.front.repos.RouteRepo;
import com.front.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepo flight;
    @Autowired
    RouteRepo routes;

    @Override
    public Flight createFlight(Flight flights) {
        if (flights.getRoute() != null) {
            if (routes.findByRouteId(flights.getRoute().getRouteId()) != null)
                flights.setRoute(routes.findByRouteId(flights.getRoute().getRouteId()));
            else {
                if (routes.findByDepartPointOrArrivalPoint(flights.getRoute().getDepartPoint()) != null) {
                    for (Route route : routes.findByDepartPointOrArrivalPoint(flights.getRoute().getDepartPoint())) {
                        if (route.getDepartPoint().equals(flights.getRoute().getDepartPoint()) &&
                                route.getArrivalPoint().equals(flights.getRoute().getArrivalPoint())) {
                            flights.setRoute(route);
                        }
                    }
                } else {
                    if (flights.getRoute().getDepartPoint() != null && flights.getRoute().getArrivalPoint() != null) {
                        flights.setRoute(new Route(flights.getRoute().getDepartPoint(),
                                flights.getRoute().getArrivalPoint()));
                    }
                }
            }
        }
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
        flights.setRoute(routes.findByRouteId(flights.getRoute().getRouteId()));
        return flight.save(flights);
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
