package com.navigation.basestationservice.exceptionhandler;

import com.navigation.basestationservice.exceptionhandler.exceptions.InvalidParameterException;
import com.navigation.basestationservice.exceptionhandler.exceptions.NoBaseStationException;
import com.navigation.basestationservice.exceptionhandler.exceptions.NoMobileStationInRadiusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoMobileStationInRadiusException.class)
    public ResponseEntity<String> handleUnAvailableMobileStationsInRadius(NoMobileStationInRadiusException ex){

        return new ResponseEntity<>("No Mobile Station within radius of the specified Base Station. " +
                "Please try another base station using different 'Request Param' id", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoBaseStationException.class)
    public ResponseEntity<String> handleInvalidBaseStationInException(NoBaseStationException ex){
        return new ResponseEntity<>("No Base Station with the id", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> handleInvalidBParamId(InvalidParameterException ex){
        return new ResponseEntity<>("No Valid Param In supplied", HttpStatus.NOT_FOUND);
    }
}
