package com.navigation.basestationservice.controller;

import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.model.request.BaseStationRequestDto;
import com.navigation.basestationservice.model.response.BaseStationResponseDto;
import com.navigation.basestationservice.service.BaseStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/base_station")
@RequiredArgsConstructor
public class BaseStationController {
    private final BaseStationService baseStationService;
    @PostMapping("/create")
    public ResponseEntity<BaseStation> createBaseStation(@RequestBody BaseStationRequestDto baseStationRequestDto){
        return ResponseEntity.ok(baseStationService.createBaseStation(baseStationRequestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<BaseStationResponseDto> getBaseStation(@RequestParam("id") Long id){
        return ResponseEntity.ok(baseStationService.getBaseStation(id));
    }

}
