package com.frod.core.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomExceptionType {
    API_ERROR("에러", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER("유효 하지 않은 파라미터 입니다.", HttpStatus.BAD_REQUEST),
    API_CLIENT_NOT_FOUND("해당정보로 요청한정보가 존재하지 않습니다.",HttpStatus.NOT_FOUND);
    private final String message;
    private final HttpStatus httpStatus;

    CustomExceptionType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
