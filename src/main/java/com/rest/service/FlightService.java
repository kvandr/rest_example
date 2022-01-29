package com.rest.service;
import com.rest.model.Flight;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlightService {
    String addFlight(Flight flightList);
    Boolean updateFlight(Long IdFlight, String airbus, Long route, String departTime,String travelTime);
    Boolean deleteFlight(Long idFlight);
    Boolean deleteFlight(List<Flight> flightList);
    Boolean saveAll(Flight flight, Long idFlight);
    String viewFlight();
    String searchFlight(Long idFlight);
    String searchFlight(String airbuses);
}
