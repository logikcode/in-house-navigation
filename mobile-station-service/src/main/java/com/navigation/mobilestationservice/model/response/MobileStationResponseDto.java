package com.navigation.mobilestationservice.model.response;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MobileStationResponseDto {
    private String mobileId;
    private float x;
    private float y;
    private float error_radius;
    private int error_code;
    private String error_description;
}
