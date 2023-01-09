package com.ocpdemo.demo.Exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
          HttpStatus BadRequest = HttpStatus.BAD_REQUEST;
          ApiException apiException = new ApiException(
                  e.getMessage(),
                  e,
                  HttpStatus.BAD_REQUEST,
                  ZonedDateTime.now(ZoneId.of("UTC-11"))
          );

          return new ResponseEntity<>(apiException, BadRequest);
    }
}
