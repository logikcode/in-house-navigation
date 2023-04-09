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
    private float errorRadius;
    private int errorCode;
    private String errorDescription;
}
