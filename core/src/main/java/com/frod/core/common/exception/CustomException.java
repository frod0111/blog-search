package com.frod.core.common.exception;

import com.frod.core.common.constant.CustomExceptionType;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final CustomExceptionType type;
    private final Object data;

    public CustomException(CustomExceptionType type) {
        this(type, null);
    }

    public CustomException(CustomExceptionType type, Object data) {
        super(type.getMessage());
        this.type = type;
        this.data = data;
    }

    public CustomException(String message, CustomExceptionType type, Object data) {
        super(message);
        this.type = type;
        this.data = data;
    }
}
