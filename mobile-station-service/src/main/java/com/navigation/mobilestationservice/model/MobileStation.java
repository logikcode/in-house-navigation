package com.navigation.mobilestationservice.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    private float lastKnownX;
    private float lastKnownY;
//    private float error_radius;
//    private int error_code;
//    private String error_description;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
