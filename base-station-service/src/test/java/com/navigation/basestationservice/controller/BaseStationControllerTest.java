package com.navigation.basestationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navigation.basestationservice.exceptionhandler.exceptions.InvalidParameterException;
import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.repository.BaseStationRepository;
import com.navigation.basestationservice.service.BaseStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseStationController.class)

class BaseStationControllerTest {
    private static final String END_POINT_PATH = "/api/v1/base_station/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BaseStationService baseStationService;
    @MockBean
    private BaseStationRepository baseStationRepository;

    private Random random = new Random(100);
    @BeforeEach
    void setUp() {
    }


    @Test
    void testCreateBaseStation() throws Exception {

        BaseStation baseStation = new BaseStation();
        baseStation.setUuid(UUID.randomUUID().toString());
        baseStation.setName("Base Station 1");
        baseStation.setY(random.nextFloat(1, 100));
        baseStation.setX(random.nextFloat(1, 100));
        baseStation.setDetectionRadiusInMeters(random.nextFloat(1, 100));

        String requestBody = objectMapper.writeValueAsString(baseStation);

        mockMvc.perform(MockMvcRequestBuilders
                .post(END_POINT_PATH+"create").contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk());


    }

    @Test
    void testInvalidBaseStationCreation() throws Exception {

        BaseStation baseStation = new BaseStation();
        baseStation.setUuid(UUID.randomUUID().toString());
        baseStation.setName("");
        baseStation.setY(random.nextFloat(1, 100));
        baseStation.setX(random.nextFloat(1, 100));
        baseStation.setDetectionRadiusInMeters(random.nextFloat(1, 100));

        String requestBody = objectMapper.writeValueAsString(baseStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(END_POINT_PATH+"create").contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());


    }

    @Test
    void testGetBaseStationById() throws Exception {
    String uri = END_POINT_PATH + "get";
    Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri).contentType("application/json")
                        .queryParam("id", String.valueOf(id)))
                .andExpect(status().isOk());

    }

    @Test
    void testGetListShouldReturnNoContent() throws Exception {
        String uri = END_POINT_PATH + "get";
        Long id = 200L;
        Mockito.when(baseStationService.getBaseStation(id)).thenThrow(InvalidParameterException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri).contentType("application/json")
                        .queryParam("id", String.valueOf(id)))
                .andExpect(status().isNotFound());

        //Mockito.verify(baseStationService, Mockito.times(1));


    }


}