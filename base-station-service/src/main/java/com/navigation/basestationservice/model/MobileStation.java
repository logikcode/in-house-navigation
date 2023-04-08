package com.navigation.basestationservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MobileStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float lastKnownX;
    private float getLastKnownY;
    private float error_radius;
    private int error_code;
    private String error_description;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
