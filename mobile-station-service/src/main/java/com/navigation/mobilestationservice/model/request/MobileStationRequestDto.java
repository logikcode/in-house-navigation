package com.navigation.mobilestationservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MobileStationRequestDto {
    private float x;
    private float y;
    @JsonProperty("error_radius")
    private float errorRadius;
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("error_description")

    private String errorDescription;
}
