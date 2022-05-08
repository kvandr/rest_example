package com.front.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.model.Flight;
import com.front.model.Role;
import com.front.model.User;
import com.front.repos.UserClient;
import com.front.service.FlightService;
import com.front.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserClient userClient;

    @GetMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userClient.findByUsername(user.getUsername());

        if (userFromDB!=null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userClient.save(user);
        return "redirect:/login";
    }
}
