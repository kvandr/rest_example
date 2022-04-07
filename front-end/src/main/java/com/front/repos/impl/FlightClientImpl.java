package com.front.repos.impl;


import com.front.model.Flight;
import com.front.repos.FlightClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightClientImpl implements FlightClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.storage.url}")
    String url;

    public List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param){
        ResponseEntity<List<Flight>> rateResponse =
                restTemplate.exchange(url + "/flightSearch/{param}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                        },param);

        return rateResponse.getBody();
    }

    public Flight findByFlightId(Long id){
        ResponseEntity<Flight> rateResponse =
                restTemplate.exchange(url + "/flight/" + id,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Flight>() {
                        });

        return rateResponse.getBody();
    }

    public Flight save(Flight flight) {
        ResponseEntity<Flight> responseEntity = restTemplate.postForEntity
                (url + "/flight", flight, Flight.class);

        return responseEntity.getBody();
    }

    public void update(Flight flight) {
        restTemplate.put(url + "/flight/" + flight.getFlightId(), flight, Flight.class);
    }

    public List<Flight> findByAirbus(String airbus){
        ResponseEntity<List<Flight>> rateResponse =
                restTemplate.exchange(url + "/flightAirbusSearch/"+airbus,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                        });

        return rateResponse.getBody();
    }

    public List<Flight> findAll() {
        ResponseEntity<List<Flight>> rateResponse =
                restTemplate.exchange(url + "/flight",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                        });

        return  rateResponse.getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(url + "/flight/"+id);
    }
}
