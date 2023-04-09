package com.navigation.mobilestationservice.controller;

import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;
import com.navigation.mobilestationservice.service.MobileStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/mobile_station")
@RestController
@RequiredArgsConstructor
public class MobileStationController {

    private final MobileStationService mobileStationService;

    @PostMapping("/create")
    public ResponseEntity<MobileStation> createMobileStation(@RequestBody MobileStationRequestDto mobileStationRequestDto){
        return ResponseEntity.ok(mobileStationService.createBaseStation(mobileStationRequestDto));
    }

    @GetMapping("/location/{uuid}")
    public ResponseEntity<MobileStationResponseDto> getMobileStation(@PathVariable("uuid") Long id){
        return ResponseEntity.ok(mobileStationService.getMobileStation(id));
    }
    @GetMapping("/all")
    public List<MobileStation> retrieveStations(){
        return mobileStationService.retrieveAllStationsInDB();
    }
}
