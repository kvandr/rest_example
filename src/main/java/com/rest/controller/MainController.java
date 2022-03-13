package com.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rest.model.Flight;
import com.rest.service.FlightService;
import com.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .setDateFormat("yyyy.MM.dd")
            .create();
    @Autowired
    private final FlightService flightService;
    @Autowired
    private final RouteService routeService;


    @Autowired
    public MainController(FlightService flightService, RouteService routeService) {
        this.flightService = flightService;
        this.routeService = routeService;
    }

    @GetMapping
    public String readAll(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "show";
    }
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, Map<String, Object> model) {
        BufferedReader reader;
        List<Flight> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Flight>>() {}.getType();
        try {
            List <Flight> line;
            InputStream is = file.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            list = GSON.fromJson(reader, itemsListType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null) {
            //flightService.deleteAll(flightService.readAll());
            flightService.saveAll(list);
        }
        return "redirect:/";
    }
}





















/*ObjectMapper objectMapper = new ObjectMapper();
        try{
            List<Flight> flight = objectMapper.readValue(file, new TypeReference<List<Flight>>() {
            });
            //flightService.deleteAll(flightService.readAll());
            flightService.saveAll(flight);
        } catch(IOException e)
        {
            e.printStackTrace();
        }*/
