package com.front.repos;


import com.front.model.Flight;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FlightRepo {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.storage.url}")
    String url;

    public List<Flight> findByAirbusOrRouteOrDepartTimeOrTravelTime(String param){
        ResponseEntity<List<Flight>> rateResponse =
                restTemplate.exchange(url + "/flightSearch/"+param,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                        });
        List<Flight> flightList = rateResponse.getBody();

        return flightList;
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

    public List<Flight> findByAirbus(String airbus){
        ResponseEntity<List<Flight>> rateResponse =
                restTemplate.exchange(url + "/flightAirbusSearch/"+airbus,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Flight>>() {
                        });
        List<Flight> flightList = rateResponse.getBody();

        return flightList;
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
