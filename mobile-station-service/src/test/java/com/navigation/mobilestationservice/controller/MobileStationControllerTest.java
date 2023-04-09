package com.navigation.mobilestationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;
import com.navigation.mobilestationservice.repository.MobileStationRepository;
import com.navigation.mobilestationservice.service.MobileStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MobileStationController.class)
class MobileStationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  MobileStationService mobileStationService;
    @MockBean
    private MobileStationRepository mobileStationRepository;
    private Random random;
     private List<MobileStation> mobileStationList;
    private MobileStation mobileStation;
   private MobileStation mobileStation2;
    private MobileStation mobileStation3;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mobileStationList = new ArrayList<>();
        random = new Random(100);
        mobileStation = new MobileStation();
        mobileStation.setId(1L);
        mobileStation.setUuid(UUID.randomUUID().toString());
        mobileStation.setLastKnownX(random.nextFloat(1,100));
        mobileStation.setLastKnownY(random.nextFloat(1, 100));
        mobileStation.setCreatedDate(LocalDateTime.now());

        mobileStation2 = new MobileStation();
        mobileStation2.setId(1L);
        mobileStation2.setUuid(UUID.randomUUID().toString());
        mobileStation2.setLastKnownX(random.nextFloat(1,100));
        mobileStation2.setLastKnownY(random.nextFloat(1, 100));
        mobileStation2.setCreatedDate(LocalDateTime.now());

         mobileStation3 = new MobileStation();
        mobileStation3.setId(1L);
        mobileStation3.setUuid(UUID.randomUUID().toString());
        mobileStation3.setLastKnownX(random.nextFloat(1,100));
        mobileStation3.setLastKnownY(random.nextFloat(1, 100));
        mobileStation3.setCreatedDate(LocalDateTime.now());

        mobileStationList.add(mobileStation);
        mobileStationList.add(mobileStation2);
        mobileStationList.add(mobileStation3);

    }

    @Test
    void testCreateMobileStation() throws Exception {
        MobileStationRequestDto requestDto = new MobileStationRequestDto( 56f, 76f, 45f,
                0, "");
        String request = objectMapper.writeValueAsString(requestDto);
    Mockito.when(mobileStationService.createBaseStation(requestDto)).thenReturn(new MobileStation());
    mockMvc.perform(post("/api/v1/mobile_station/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request)).andExpect(status().isOk());

    }


    @Test
    void testGetMobileStation() throws Exception {
        MobileStationResponseDto respDto = new MobileStationResponseDto();
        respDto.setMobileId(mobileStation.getId().toString());
        respDto.setX(mobileStation.getLastKnownX());
        respDto.setY(mobileStation.getLastKnownY());
        respDto.setErrorRadius(85f);
        Long id = 1L;
       Mockito.when(mobileStationService.getMobileStation(id))
               .thenReturn(respDto);
       this.mockMvc.perform(get("/api/v1/mobile_station/location/1"))
               .andExpect(status().isOk());
    }

    @Test
    void testRetrieveStations() throws Exception {
        Mockito.when(mobileStationService.retrieveAllStationsInDB())
                .thenReturn(mobileStationList);
        this.mockMvc.perform(get("/api/v1/mobile_station/all"))
                .andDo(print()).andExpect(status().isOk());
    }
}