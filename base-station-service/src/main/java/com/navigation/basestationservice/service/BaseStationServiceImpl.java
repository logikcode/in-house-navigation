package com.navigation.basestationservice.service;

import com.navigation.basestationservice.exceptionhandler.exceptions.InvalidParameterException;
import com.navigation.basestationservice.exceptionhandler.exceptions.NoBaseStationException;
import com.navigation.basestationservice.exceptionhandler.exceptions.NoMobileStationInRadiusException;
import com.navigation.basestationservice.exceptionhandler.exceptions.ServiceDownException;
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
        if (id == null){
            throw new InvalidParameterException();
        }
       Optional<BaseStation> baseStation = baseStationRepository.findById(id);

       if (baseStation.isEmpty()){
           throw new NoBaseStationException("mobile station with id "+id+" not found");
       }

       List<Map<String,Object>> mobileStations = getMobileStations(baseStation.get().getX(), baseStation.get().getY() ,baseStation.get().getDetectionRadiusInMeters());
       if(mobileStations.isEmpty()){
           throw new NoMobileStationInRadiusException();
       }
       BaseStationResponseDto responseDto = new BaseStationResponseDto();
       responseDto.setReports(mobileStations);
       responseDto.setBaseStationId(baseStation.get().getUuid());

       return responseDto;
    }



    private List<Map<String,Object>> getMobileStations(float x, float y, float detectionRadiusInMeters) {

        List<MobileStation> mobileStations = retrieveMobileStations();
        System.out.println("DONE RETRIEVING ALL MOBILE STATIONS "+ mobileStations.size());
        return computeMobileStationDistance(mobileStations, x, y, detectionRadiusInMeters);
    }

    public List<MobileStation> retrieveMobileStations(){
        List<MobileStation> mobileStationList = new ArrayList<>();
    MobileStation[] response;
    try {
        response = webClietBuilder.build().get()
                .uri("http://mobile-station-service/api/v1/mobile_station/all")
                .retrieve()
                .bodyToMono(MobileStation[].class)
                .block();
    }catch (Exception ex){

        throw new ServiceDownException();
    }

       assert response != null;
       mobileStationList = Arrays.asList(response);
       return mobileStationList;
    }

    private List<Map<String, Object>> computeMobileStationDistance(List<MobileStation> mobileStations,
                                                                   float baseStationXPoint, float baseStationYPoint,
                                                                   float detectionRadiusInMeters){

        List<Map<String, Object>> response = new ArrayList<>();

        for(MobileStation mobileStation: mobileStations) {
            Map<String,Object> report = new HashMap<>();

            double distance = Math.sqrt(Math.pow( mobileStation.getLastKnownX()- baseStationXPoint, 2)
                    - Math.pow(mobileStation.getGetLastKnownY() - baseStationYPoint, 2));

            if(distance <= detectionRadiusInMeters) {
                report.put("mobile_station_id", mobileStation.getId());
                report.put("distance", distance);
                report.put("timeStamp", mobileStation.getCreatedDate());

                response.add(report);
            }

        }
        return response;
    }
}
