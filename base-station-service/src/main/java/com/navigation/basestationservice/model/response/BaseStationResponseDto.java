package com.navigation.basestationservice.model.response;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseStationResponseDto {
    private String base_station;
    private List<Map<String,Object>> reports;
}
