package com.front.repos.impl;


import com.front.model.Route;
import com.front.repos.RouteClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RouteClientImpl implements RouteClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.storage.url}")
    String url;

    public List <Route> findByDepartPointOrArrivalPoint(String param){
        ResponseEntity<List<Route>> rateResponse =
                restTemplate.exchange(url + "/routeSearch/",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Route>>() {
                        }, param);

        return rateResponse.getBody();
    }

    public Route findByRouteId(Long id){
        ResponseEntity<Route> rateResponse =
                restTemplate.exchange(url + "/route/"+id,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Route>() {
                        });
        return rateResponse.getBody();
    }

    public Route save(Route route) {
        ResponseEntity<Route> responseEntity = restTemplate.postForEntity
                (url + "/route", route, Route.class);

        return responseEntity.getBody();
    }

    public void update(Route route) {
        restTemplate.put(url + "/route/" + route.getRouteId(), route, Route.class);
    }

    public List<Route> findAll() {
        ResponseEntity<List<Route>> rateResponse =
                restTemplate.exchange(url + "/route",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Route>>() {
                        });

        return  rateResponse.getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(url + "/route/"+id);
    }
}
