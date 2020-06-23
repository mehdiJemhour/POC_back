package com.jemhourmehdi.importbdx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionPayload {

    private String errorCode;
    private String errorMessage;
    private LocalDateTime errorTimeStamp;
}
