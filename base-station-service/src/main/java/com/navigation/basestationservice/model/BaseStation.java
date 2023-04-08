package com.navigation.basestationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

    @Getter
    @Setter
    @Entity(name = "base_station")
    @NoArgsConstructor
    @AllArgsConstructor
    public class BaseStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    @NotBlank(message = "Name must not be empty")
    @Size(min = 3, max = 20)
    private String name;
    @Column(length = 20, nullable = false)
    private float x;
    @Column(length = 20, nullable = false)
    private float y;
    @Column(length = 20, nullable = false)
    private float detectionRadiusInMeters;

}
