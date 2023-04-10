package com.navigation.basestationservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class BaseStationRequestDto {
    private String name;
    private float x;
    private float y;
    @JsonProperty("detection_radius_in_meters")
    private float detectionRadiusInMeters;
}
