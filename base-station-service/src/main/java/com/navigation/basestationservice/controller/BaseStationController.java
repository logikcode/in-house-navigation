package com.navigation.basestationservice.controller;

import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.model.request.BaseStationRequestDto;
import com.navigation.basestationservice.model.response.BaseStationResponseDto;
import com.navigation.basestationservice.service.BaseStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/base_station")
@RequiredArgsConstructor
public class BaseStationController {
    private final BaseStationService baseStationService;


   @Operation(description = "Create a Base Station",
            responses = {

            @ApiResponse(responseCode = "400", ref = "Bad Request"),
            @ApiResponse(responseCode = "500", ref = "Internal Server"),
            @ApiResponse(responseCode = "201", ref = "Success")
    })
    @PostMapping("/create")
    public ResponseEntity<BaseStation> createBaseStation(@RequestBody BaseStationRequestDto baseStationRequestDto){
        return ResponseEntity.ok(baseStationService.createBaseStation(baseStationRequestDto));
    }

    @GetMapping("/get")
    @Operation(description = "Retrieving a Base Station",
            responses = {

                    @ApiResponse(responseCode = "400", ref = "Bad Request"),
                    @ApiResponse(responseCode = "500", ref = "Internal Server"),
                    @ApiResponse(responseCode = "200", ref = "Success")
            })
    public ResponseEntity<BaseStationResponseDto> getBaseStation(@RequestParam("id") Long id){
        return ResponseEntity.ok(baseStationService.getBaseStation(id));
    }

}
