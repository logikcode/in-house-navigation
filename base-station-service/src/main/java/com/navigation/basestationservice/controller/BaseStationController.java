package com.navigation.basestationservice.controller;

import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.model.request.BaseStationRequestDto;
import com.navigation.basestationservice.model.response.BaseStationResponseDto;
import com.navigation.basestationservice.service.BaseStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/basestation")
public class BaseStationController {
    @Autowired
private BaseStationService baseStationService;
    @PostMapping()
    public ResponseEntity<BaseStation> createBaseStation(@RequestBody BaseStationRequestDto baseStationRequestDto){
        return ResponseEntity.ok(baseStationService.createBaseStation(baseStationRequestDto));
    }

    @GetMapping()
    public ResponseEntity<BaseStationResponseDto> getBaseStation(@RequestParam("id") String id){
        return ResponseEntity.ok(baseStationService.getBaseStation(id));
    }
}
