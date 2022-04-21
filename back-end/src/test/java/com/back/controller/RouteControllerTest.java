package com.back.controller;

import com.back.RestExampleApplication;
import com.back.model.Route;
import com.back.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
@Slf4j
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RouteService routeService;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .setDateFormat("yyyy.MM.dd")
            .create();

    @BeforeEach
    public void setup() throws Exception {
        log.info("startup - creating DB connection");
        BufferedReader reader;
        Type itemsListType = new TypeToken<List<Route>>() {
        }.getType();
        reader = new BufferedReader(new InputStreamReader(new ClassPathResource("jsonBD/Route.json").getInputStream()));
        List<Route> list = GSON.fromJson(reader, itemsListType);
        log.info(String.valueOf(list));
        for (Route route : list) {
            routeService.create(route);
            log.info(String.valueOf(routeService.create(route)));
            log.info(String.valueOf(route));
        }
        list = routeService.readAll();
        log.info(String.valueOf(list));
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
        mockMvc.perform( MockMvcRequestBuilders.put("/route/{id}", id)
                        .content(asJsonString(new Route(2L,"Samara","Moscow")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
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
        log.info("closing DB connection");
    }
}