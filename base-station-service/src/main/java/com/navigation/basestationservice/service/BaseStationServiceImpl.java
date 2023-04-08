package com.navigation.basestationservice.service;

import com.navigation.basestationservice.exceptionhandler.exceptions.EntityNotFoundException;
import com.navigation.basestationservice.model.BaseStation;
import com.navigation.basestationservice.model.MobileStation;
import com.navigation.basestationservice.model.request.BaseStationRequestDto;
import com.navigation.basestationservice.model.response.BaseStationResponseDto;
import com.navigation.basestationservice.repository.BaseStationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BaseStationServiceImpl implements BaseStationService{

    private final BaseStationRepository baseStationRepository;
    private final WebClient.Builder webClietBuilder;

    @Override
    public BaseStation createBaseStation(BaseStationRequestDto baseStationRequestDto) {
        BaseStation baseStation = new BaseStation();
        baseStation.setDetectionRadiusInMeters(baseStationRequestDto.getDetectionRadiusInMeters());
        baseStation.setX(baseStationRequestDto.getX());
        baseStation.setY(baseStationRequestDto.getY());
        baseStation.setName(baseStationRequestDto.getName());
        baseStation.setUuid(UUID.randomUUID().toString());

        return baseStationRepository.save(baseStation);
    }

    @Override
    public BaseStationResponseDto getBaseStation(Long id) {
       Optional<BaseStation> baseStation = baseStationRepository.findById(id);
       if (baseStation.isEmpty()){
           throw new EntityNotFoundException("mobile station with id "+id+" not found");
       }

       List<Map<String,Object>> mobileStations = getMobileStations(baseStation.get().getX(), baseStation.get().getX(),baseStation.get().getX());
       if(mobileStations.isEmpty()){
           throw new EntityNotFoundException("no mobile station within the specified radius "+id+"");
       }
       BaseStationResponseDto responseDto = new BaseStationResponseDto();
       responseDto.setReports(mobileStations);
       responseDto.setBase_station(baseStation.get().getName());

       return responseDto;
    }



    private List<Map<String,Object>> getMobileStations(float x, float y, float detectionRadiusInMeters) {

        List<MobileStation> mobileStations = retrieveMobileStations();
        return computeMobileStationDistance(mobileStations, x, y, detectionRadiusInMeters);
    }

    public List<MobileStation> retrieveMobileStations(){
        List<MobileStation> mobileStationList = new ArrayList<>();
    MobileStation[] response;
       response = webClietBuilder.build().get()
                .uri("http://mobile-station-service/api/v1/mobile_station/all")
                .retrieve()
                .bodyToMono(MobileStation[].class)
                .block();

       assert response != null;
       mobileStationList = Arrays.asList(response);
       return mobileStationList;
    }

    private List<Map<String, Object>> computeMobileStationDistance(List<MobileStation> mobileStations, float x, float y, float detectionRadiusInMeters){

        List<Map<String, Object>> response = new ArrayList<>();

        for(MobileStation mobileStation: mobileStations) {
            Map<String,Object> res = new HashMap<>();

            double distance = Math.sqrt(Math.pow(x - mobileStation.getLastKnownX(), 2)
                    - Math.pow(y - mobileStation.getGetLastKnownY(), 2));
            if(distance <= detectionRadiusInMeters) {
                res.put("mobile_station_id", mobileStation.getId());
                res.put("distance", distance);
                res.put("timeStamp", mobileStation.getCreatedDate());

                response.add(res);
            }

        }
        return response;
    }
}
