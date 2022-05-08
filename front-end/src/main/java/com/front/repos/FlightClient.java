package com.front.repos;


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
public class FlightClient{
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

    public List<Flight> findByUserId(Long id) {
        ResponseEntity<List<Flight>> rateResponse = restTemplate.exchange(url + "/user/{id}/flight",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                },id);

        return rateResponse.getBody();
    }

    public List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTimeAndUserId(String param, Long id) {
        ResponseEntity<List<Flight>> rateResponse = restTemplate.exchange(url + "/user/{id}/flight/{param}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                },id, param);
        return rateResponse.getBody();
    }

    public Flight findByFlightId(Long id){
        ResponseEntity<Flight> rateResponse =
                restTemplate.exchange(url + "/flight/{id}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<Flight>() {
                        }, id);

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
        restTemplate.delete(url + "/flight/{id}",id);
    }

    public void deleteAll(List<Flight> flightList) {
        for(Flight flight:flightList)
            delete(flight.getFlightId());
    }
}
