package com.navigation.basestationservice.repository;

import com.navigation.basestationservice.model.BaseStation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class BaseStationRepositoryTest {
    @Autowired
    private  BaseStationRepository baseStationRepository;
    @BeforeEach
    void setup(){

    }

    @Test
    void testThatBaseStationIsLoadedToDB(){
        List<BaseStation> baseStations = baseStationRepository.findAll();
        Assertions.assertNotNull(baseStations);
    }

    @Test
    void testThatBaseStationIsLoadedToDB_isLessThan100(){
        int LESS_THAN_100 = 99;
        List<BaseStation> baseStations = baseStationRepository.findAll();
        Assertions.assertEquals(baseStations.size(), LESS_THAN_100);

    }
}
