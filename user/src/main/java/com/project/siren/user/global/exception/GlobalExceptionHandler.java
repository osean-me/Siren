package com.project.siren.user.global.exception;

import com.project.siren.user.global.response.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BasicResponse<String>> bindExceptionHandler(BindException bindException) {
        String defaultMessage = Objects.requireNonNull(bindException.getFieldError()).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BasicResponse.fail(defaultMessage));
    }
}
