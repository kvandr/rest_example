package com.back.service.impl;

import com.back.model.Flight;
import com.back.model.Route;
import com.back.model.User;
import com.back.repos.FlightRepo;
import com.back.repos.RouteRepo;
import com.back.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepo flight;
    @Autowired
    RouteRepo routes;

    @Override
    public void create(Flight flights, Route route, User user){
        flights.setRoute(route);
        flights.setUser(user);
        flight.save(flights);
    }

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
                        flights.setRoute(new Route(flights.getRoute().getRouteId(),
                                flights.getRoute().getDepartPoint(),
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
    public List<Flight> readByUser(User user) {
        return flight.findByUserId(user.getId());
    }

    @Override
    public List<Flight> readByUserAndSearch(User user, String search) {
        return flight.findByAirbusOrRouteOrDepartTimeOrTravelTimeAndUserId(search,user.getId());
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
        flight.findByFlightId(id).setRoute(null);
        flight.delete(flight.findByFlightId(id));
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

    @Override
    public Iterable<Flight> saveAll(Iterable<Flight> flightList) {
        return flight.saveAll(flightList);
    }

    @Override
    public void deleteAll(Iterable<Flight> flightList) {
        flight.deleteAll(flightList);
    }
}
