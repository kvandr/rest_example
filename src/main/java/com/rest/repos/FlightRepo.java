package com.rest.repos;


import com.rest.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepo extends CrudRepository<Flight, Integer>{

    @Query("Find by any parametr Flight")
    List<Flight> findByAnyParamFlight(String param);

    List<Flight> findByUserId(Long id);

    Flight findById(long id);

    @Query("SELECT COUNT(t) FROM Flight t")
    Long countAll();

    List<Flight> findAll();
}
