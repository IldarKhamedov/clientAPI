package ru.khamedov.ildar.clientApi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler
    public ResponseEntity<JsonError> catchHttpClientErrorException(HttpClientErrorException e) {
        return new ResponseEntity<>(new JsonError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}