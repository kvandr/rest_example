package com.back.controller;

import com.back.RestExampleApplication;
import com.back.model.Flight;
import com.back.model.Route;
import com.back.service.RouteService;
import com.back.service.impl.RouteServiceImpl;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RestExampleApplication.class
)
@AutoConfigureMockMvc
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .setDateFormat("yyyy.MM.dd")
            .create();

    @BeforeEach
    public void setup() throws Exception {
        System.out.println("startup - creating DB connection");
        BufferedReader reader;
        List<Route> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Route>>() {
        }.getType();
        try {
            reader = new BufferedReader(new FileReader("src/test/resources/jsonBD/Route.json"));
            list = GSON.fromJson(reader, itemsListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (list == null) list = new ArrayList<>();
        for (Route route : list) {
            routeService.create(route);
        }
    }

    @Test
    void createRoute() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/route")
                        .content(asJsonString(new Route(1L,"Samara","Moskva")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void readAll() throws Exception {
        mockMvc.perform(get("/route")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void read() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/route/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void search() throws Exception {
        String param = "Sam";
        mockMvc.perform(get("/routeSearch/{param}", param)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        Long id = 1L;
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/route/{id}", id)
                        .content(asJsonString(new Route(2L,"Samara","Moscow")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/route/{id}",id))
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