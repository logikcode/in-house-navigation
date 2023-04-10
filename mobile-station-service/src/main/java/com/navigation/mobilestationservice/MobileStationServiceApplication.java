package com.navigation.mobilestationservice;

import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.repository.MobileStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class MobileStationServiceApplication implements CommandLineRunner{
    private final MobileStationRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(MobileStationServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        loadMobileStationsToDB();
    }

    private  void loadMobileStationsToDB(){
        Random random = new Random(100);


        for (int i = 1; i < 100; i++){
            String uuid = UUID.randomUUID().toString();
            float xValue = random.nextFloat(1, 100);
            float yValue = random.nextFloat(1, 100);
            log.info("XVALUE -> {}, YVALUE -> {}", xValue, yValue);

            MobileStation mobileStation = new MobileStation();
            mobileStation.setUuid(uuid);
            mobileStation.setLastKnownX(xValue);
            mobileStation.setLastKnownY(yValue);
            mobileStation.setCreatedDate(LocalDateTime.now());

            repository.save(mobileStation);

        }
    }

}
