package com.navigation.mobilestationservice.model.request;

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
    private float error_radius;
    private int error_code;
    private String error_description;
}
