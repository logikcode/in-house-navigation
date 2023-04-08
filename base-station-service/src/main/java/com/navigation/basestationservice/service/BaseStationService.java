package com.navigation.basestationservice.service;


import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.model.request.BaseStationRequestDto;
import com.navigation.basestationservice.model.response.BaseStationResponseDto;

public interface BaseStationService {
     BaseStation createBaseStation(BaseStationRequestDto baseStationRequestDto);
     BaseStationResponseDto getBaseStation(String uuid);
}
