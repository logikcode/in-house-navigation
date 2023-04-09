package com.navigation.mobilestationservice.service;

import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.repository.MobileStationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@SpringBootTest
class MobileStationServiceImplTest {
    @Mock
    private MobileStationService mobileStationService;
    @MockBean

    private MobileStationRepository mobileStationRepository;

    private AutoCloseable autoCloseable;
    private MobileStation mobileStation;
    private Random random;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        random = new Random(100);

        mobileStation = new MobileStation();
        mobileStation.setId(1L);
        mobileStation.setUuid(UUID.randomUUID().toString());
        mobileStation.setLastKnownX(random.nextFloat(1,100));
        mobileStation.setLastKnownY(random.nextFloat(1, 100));
        mobileStation.setCreatedDate(LocalDateTime.now());

    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void testCreateBaseStation() {

        mock(MobileStationRepository.class);
        mock(MobileStationService.class);
        Mockito.when(mobileStationRepository.save(mobileStation)).thenReturn(mobileStation);
        Mockito.when(mobileStationService.createBaseStation(new MobileStationRequestDto())).thenReturn(mobileStation);
        Assertions.assertNotNull(mobileStation);
        Assertions.assertEquals(mobileStation.getUuid(), mobileStation.getUuid());

    }

    @Test
    void testGetMobileStation() {
        Long id = 1L;
        Optional<MobileStation> mobileStationOptional = Optional.of(mobileStation);
        mock(MobileStationRepository.class);
        mock(MobileStationService.class);
        Mockito.when(mobileStationRepository.findById(id)).thenReturn(mobileStationOptional);
        Assertions.assertNotNull(mobileStationOptional.get());
    }

    @Test
    void retrieveAllStationsInDB() {
        mock(MobileStationRepository.class);
        mock(MobileStationService.class);
        List<MobileStation> mobileStationList = List.of(mobileStation, mobileStation, mobileStation);
        Mockito.when(mobileStationService.retrieveAllStationsInDB())
                .thenReturn(mobileStationList);
        assertThat(mobileStationList).isNotNull();
    }
}