package com.navigation.mobilestationservice.controller;

import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;
import com.navigation.mobilestationservice.service.MobileStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "Create a Mobile Station",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "Bad Request"),
                    @ApiResponse(responseCode = "500", ref = "Internal Server"),
                    @ApiResponse(responseCode = "201", ref = "Success")
            })
    public ResponseEntity<MobileStation> createMobileStation(@RequestBody MobileStationRequestDto mobileStationRequestDto){
        return ResponseEntity.ok(mobileStationService.createBaseStation(mobileStationRequestDto));
    }

    @Operation(description = "Retrieving a Mobile Station",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "Bad Request"),
                    @ApiResponse(responseCode = "500", ref = "Internal Server"),
                    @ApiResponse(responseCode = "200", ref = "Success")
            })
    @GetMapping("/location/{uuid}")
    public ResponseEntity<MobileStationResponseDto> getMobileStation(@PathVariable("uuid") Long id){
        return ResponseEntity.ok(mobileStationService.getMobileStation(id));
    }
    @Operation(description = "Retrieving all Mobile Stations",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "Bad Request"),
                    @ApiResponse(responseCode = "500", ref = "Internal Server"),
                    @ApiResponse(responseCode = "200", ref = "Success")
            })
    @GetMapping("/all")
    public List<MobileStation> retrieveStations(){
        return mobileStationService.retrieveAllStationsInDB();
    }
}
