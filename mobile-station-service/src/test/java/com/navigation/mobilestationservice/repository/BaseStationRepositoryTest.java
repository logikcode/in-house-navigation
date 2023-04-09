package com.navigation.mobilestationservice.repository;

import com.navigation.mobilestationservice.model.MobileStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BaseStationRepositoryTest {
    @Autowired
    private  MobileStationRepository baseStationRepository;
    @BeforeEach
    void setup(){

    }

    @Test
    void testThatBaseStationIsLoadedToDB(){
        List<MobileStation> mobileStations = baseStationRepository.findAll();
        Assertions.assertNotNull(mobileStations);
    }

    @Test
    void testThatBaseStationIsLoadedToDB_isLessThan100(){
        int LESS_THAN_100 = 99;
        List<MobileStation> mobileStations = baseStationRepository.findAll();
        Assertions.assertEquals(mobileStations.size(), LESS_THAN_100);
        
    }
}