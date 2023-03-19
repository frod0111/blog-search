package com.frod.core.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalApiException extends RuntimeException {
    private final String msg;
    private final int rawStatusCode;

    private final HttpStatus httpStatus;

    public ExternalApiException(String msg, int rawStatusCode, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.rawStatusCode = rawStatusCode;
        this.httpStatus = httpStatus;
    }
}
