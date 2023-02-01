package com.d2ovj9.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PlayerApiExceptionHandler {

    // Ha nincs id-nek megfelelő bejegyzés
    @ExceptionHandler(PlayerApiRequestException.class)
    public ResponseEntity<Object> handlePlayerApiException(PlayerApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PlayerApiException playerApiException = new PlayerApiException(
                "Ooopsie! " + e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

    // Ha rossz kérést kap
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        HttpStatus badRequest = HttpStatus.METHOD_NOT_ALLOWED;
        PlayerApiException playerApiException = new PlayerApiException(
                "Ooopsie! " + e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

    // Ha a bejövő JSON felépítése hibás
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PlayerApiException playerApiException = new PlayerApiException(
                "Problem with request body",
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

    // Ezt akkor dobta, ha a REST save / update nem validált...
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException e) {
        HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        PlayerApiException playerApiException = new PlayerApiException(
                "Ooopsie! " + e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

    // Még validálás
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PlayerApiException playerApiException = new PlayerApiException(
                "Ooopsie! " + e.getFieldError().getDefaultMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

    // minHeight {id} betűk szűm helyett
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PlayerApiException playerApiException = new PlayerApiException(
                "Ooopsie! " + e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(playerApiException, badRequest);
    }

}
