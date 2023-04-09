package com.navigation.mobilestationservice.service;


import com.navigation.mobilestationservice.exceptionhandler.exceptions.EntityNotFoundException;
import com.navigation.mobilestationservice.model.MobileStation;
import com.navigation.mobilestationservice.model.request.MobileStationRequestDto;
import com.navigation.mobilestationservice.model.response.MobileStationResponseDto;
import com.navigation.mobilestationservice.repository.MobileStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MobileStationServiceImpl implements MobileStationService{
    @Autowired
    private MobileStationRepository mobileStationRepository;

    @Override
    public MobileStation createBaseStation(MobileStationRequestDto mobileStationRequestDto) {
        MobileStation mobileStation = new MobileStation();
        mobileStation.setUuid(UUID.randomUUID().toString());
        mobileStation.setLastKnownY(mobileStationRequestDto.getY());
        mobileStation.setLastKnownX(mobileStationRequestDto.getX());
        mobileStation.setCreatedDate(LocalDateTime.now());
        return mobileStationRepository.save(mobileStation);
    }

    @Override
    public MobileStationResponseDto getMobileStation(Long id) {
        Optional<MobileStation> mobileStation = mobileStationRepository.findById(id);
        if (mobileStation.isEmpty()){
            throw new EntityNotFoundException("mobile station with id "+id+" not found");
        }
        MobileStationResponseDto response = new MobileStationResponseDto();
        response.setX(mobileStation.get().getLastKnownX());
        response.setY(mobileStation.get().getLastKnownY());
        response.setMobileId(mobileStation.get().getUuid());
        return response;
    }

    @Override
    public List<MobileStation> retrieveAllStationsInDB() {
        return mobileStationRepository.findAll();
    }
}
