package com.navigation.mobilestationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("last_known_x")
    private float lastKnownX;
    @JsonProperty("last_known_y")
    private float lastKnownY;
    @JsonProperty("created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
