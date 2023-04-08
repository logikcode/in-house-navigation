package com.navigation.basestationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

    @Getter
    @Setter
    @Entity(name = "base_station")
    @NoArgsConstructor
    @AllArgsConstructor
    public class BaseStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private float x;
    private float y;
    private float detectionRadiusInMeters;

}
