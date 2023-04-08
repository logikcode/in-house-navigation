package com.navigation.mobilestationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mobile_station")
@Entity()

public class MobileStation {
    @Id
    private String id;
    private float lastKnownX;
    private float getLastKnownY;
    private float error_radius;
    private int error_code;
    private String error_description;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
