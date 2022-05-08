package com.front.repos;

import com.front.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.storage.url}")
    String url;

    public User findByUsername(String username) {
        ResponseEntity<User> responseEntity =
                restTemplate.exchange(url + "/user/findByUsername/{username}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
                        },username);

        return responseEntity.getBody();
    }

    public User findUserById(Long id) {
        ResponseEntity<User> responseEntity =
                restTemplate.exchange(url + "/user/findUserById/{id}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
                        }, id);

        return responseEntity.getBody();
    }

    public List<User> findAll() {
        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(url + "/user/findAllList",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });

        return rateResponse.getBody();
    }

    public User save(User user) {
        ResponseEntity<User> responseEntity = restTemplate.postForEntity
                (url + "/user", user, User.class);
        return responseEntity.getBody();
    }

    public void deleteById(Long id) {
        restTemplate.delete(url + "/user/deleteById/{id}",id);
    }
}
