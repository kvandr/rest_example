package com.rest.repos;


import com.rest.Exception.NotFoundException;
import com.rest.model.Flight;

import java.util.List;

public interface FlightRepo{
    Boolean save(Flight flight);
    void delete(Flight flight);
    Boolean fileUnload(List <Flight> flight);
    List<Flight> fileLoad();
    Flight findFlight(Long idFlight, Long route) throws NotFoundException;
    Flight findFlight(Long idFlight) throws NotFoundException;
    boolean findByAll(Flight flight);

}
