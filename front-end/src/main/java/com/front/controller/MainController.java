package com.front.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.model.Flight;
import com.front.service.FlightService;
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

import java.io.IOException;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
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
    private ObjectMapper mapper;

    @Autowired
    public MainController(FlightService flightService){ this.flightService = flightService; }

    @GetMapping
    public String read(Map<String, Object> model) {
        return "main";
    }

    @GetMapping(value = "main")
    public String readAll(Map<String, Object> model) {
        model.put("flights", flightService.readAll());
        return "show";
    }

    @RequestMapping(value = "main/load", method = RequestMethod.POST)
    public String uploadJsonFile(@RequestParam("file") MultipartFile file, Map<String, Object> model) {
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
            for (Flight flight : list) {
                flightService.createFlight(flight);
            }
        }
        return "redirect:/main";
    }

    @Transactional
    @RequestMapping(value = "main/download")
    public ResponseEntity<Resource> downloadJsonFile() throws IOException {

        List<Flight> flightList = flightService.readAll();

        File file = new File("tmp/main/resources/jsonBD/FlightAndRoute.json");

        TypeReference <List<Flight>> typeReference = new TypeReference<List<Flight>>() {};
        mapper.writerFor(typeReference).writeValue(new FileOutputStream(file), flightList);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=flightDB.json");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }
}
