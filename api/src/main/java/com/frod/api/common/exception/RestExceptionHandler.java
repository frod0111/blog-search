package com.frod.api.common.exception;

import com.frod.api.common.model.ErrorResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(status.toString(), exception);
        ErrorResponse errorResponse = new ErrorResponse(status.name(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(CustomException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getType().toString(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, exception.getType().getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError fe = bindingResult.getFieldError();

        String message = "[" + fe.getField() + "] " + fe.getRejectedValue() + " (" + fe.getDefaultMessage() + ")";
        ErrorResponse errorResponse = new ErrorResponse(CustomExceptionType.INVALID_PARAMETER.name(), message);

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
