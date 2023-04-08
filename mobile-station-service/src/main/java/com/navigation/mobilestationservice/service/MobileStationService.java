package com.navigation.mobilestationservice.service;


import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;

public interface MobileStationService {
    MobileStation createBaseStation(MobileStationRequestDto mobileStationRepository);

    MobileStationResponseDto getMobileStation(String id);
}
