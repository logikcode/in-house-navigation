package com.navigation.basestationservice;

import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.repository.BaseStationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class BaseStationServiceApplication implements CommandLineRunner {

    private final BaseStationRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(BaseStationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadBaseStationsToDB();
    }

    private void loadBaseStationsToDB() {
        Random random = new Random(100);
        String NAME = "Base Station ";
        float radius = 45;

        for (int i = 1; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            float xValue = random.nextFloat(120 );
            float yValue = random.nextFloat(120);
            log.info("XVALUE -> {}, YVALUE -> {}", xValue, yValue);

            BaseStation baseStation = new BaseStation();
            baseStation.setName(NAME + " " + i);
            baseStation.setUuid(uuid);
            baseStation.setX(xValue);
            baseStation.setY(yValue);
            baseStation.setDetectionRadiusInMeters(radius);


            repository.save(baseStation);

        }

    }

    private float computeDetectionPoint(float x, float y){
        float r = x - y;
       return  (float)Math.sqrt(r * r);


    }
}