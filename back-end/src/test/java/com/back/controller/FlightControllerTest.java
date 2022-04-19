package com.back.controller;

import com.back.RestExampleApplication;
import com.back.model.Flight;
import com.back.model.Route;
import com.back.service.FlightService;
import com.back.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RestExampleApplication.class
)
@AutoConfigureMockMvc

class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private RouteService routeService;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .setDateFormat("yyyy.MM.dd")
            .create();

    @BeforeEach
    public void setup() throws Exception {
        System.out.println("startup - creating DB connection");
        BufferedReader reader;
        List<Flight> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Flight>>() {
        }.getType();
        try {
            reader = new BufferedReader(new FileReader("src/test/resources/jsonBD/FlightAndRoute.json"));
            list = GSON.fromJson(reader, itemsListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (list == null) list = new ArrayList<>();
        for (Flight flight : list) {
            flightService.createFlight(flight);
        }
    }

    @Test
    void createFlight() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
        Date departTime = ft.parse("2012.05.29");
        Date travelTime = ft.parse("2012.05.30");
        mockMvc.perform( MockMvcRequestBuilders.post("/flight")
                        .content(asJsonString(new Flight(1L,"Airbus-88",
                                new Route(11L,"Samara","Moskva"), departTime, travelTime)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                //.andExpect(status().isCreated())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        Long id = 1L;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
        Date departTime = ft.parse("2012.05.26");
        Date travelTime = ft.parse("2012.05.28");
        mockMvc.perform( MockMvcRequestBuilders.put("/flight/{id}",id)
                        .content(asJsonString(new Flight(2L,"Airbus-8888",
                                new Route(111L,"Samara","Moscow"), departTime, travelTime)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                //.andExpect(status().isCreated())
                .andExpect(status().isOk());
    }

    @Test
    void readAll() throws Exception{
        mockMvc.perform(get("/flight")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void search() throws Exception {
        String param = "Air";
        mockMvc.perform(get("/flightSearch/{param}", param)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void searchAirbus() throws Exception {
        String param = "Air";
        mockMvc.perform(get("/flightAirbusSearch/{param}", param)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void read() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/flight/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteId() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/flight/{id}",id))
                .andDo(print())
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        System.out.println("closing DB connection");
    }
}