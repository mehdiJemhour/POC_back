package com.jemhourmehdi.importbdx.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public HttpEntity<ExceptionPayload> fileExceptionHandler(IOException e){
        ExceptionPayload payload = new ExceptionPayload("ERROR-FILE",e.getMessage(), LocalDateTime.now());
        log.error("Error Occurred : {} with message {}",payload.getErrorCode(),payload.getErrorMessage());
        return ResponseEntity.badRequest().body(payload);
    }

    @ExceptionHandler(RuntimeException.class)
    public HttpEntity<ExceptionPayload> exceptionHandler(RuntimeException e){
        ExceptionPayload payload = new ExceptionPayload("ERROR",e.getMessage(), LocalDateTime.now());
        log.error("Error Occurred : {} with message {}",payload.getErrorCode(),payload.getErrorMessage());
        return ResponseEntity.badRequest().body(payload);
    }
}
