package com.navigation.mobilestationservice.service;


import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;

import java.util.List;

public interface MobileStationService {
    MobileStation createBaseStation(MobileStationRequestDto mobileStationRepository);

    MobileStationResponseDto getMobileStation(Long id);

    List<MobileStation> retrieveAllStationsInDB();
}
