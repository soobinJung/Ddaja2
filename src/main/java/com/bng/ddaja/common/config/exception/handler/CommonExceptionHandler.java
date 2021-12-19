package com.bng.ddaja.common.config.exception.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;
import com.bng.ddaja.common.config.exception.exception.MemberNotFoundException;
import com.bng.ddaja.common.config.exception.exception.NotAcceptableSocialLoginException;
import com.bng.ddaja.common.config.exception.exception.NotAcceptableSocialResponseException;
import com.bng.ddaja.common.dto.ExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e){
        ExceptionResponse error = new ExceptionResponse(ExceptionCode.INVALID_PARAMETER);
        error.setDetails(e.getAllErrors().stream().map(o -> (FieldError)o).collect(Collectors.toList()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> handleJwtException(JwtException e) {
        ExceptionResponse error = new ExceptionResponse(ExceptionCode.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e) {    
        ExceptionResponse error = new ExceptionResponse(ExceptionCode.UNAUTHORIZED);
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ExceptionResponse response = new ExceptionResponse(ExceptionCode.NOT_READABLE_MESSAGE);
        response.setReason(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAcceptableSocialLoginException.class)
    public ResponseEntity<ExceptionResponse> handleNotAcceptableSocialLoginException(NotAcceptableSocialLoginException e) {
        return new ResponseEntity<>(new ExceptionResponse(e), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotAcceptableSocialResponseException.class)
    public ResponseEntity<ExceptionResponse> handleNotAcceptableSocialResponseException(NotAcceptableSocialResponseException e) {
        return new ResponseEntity<>(new ExceptionResponse(e), HttpStatus.NOT_ACCEPTABLE);
    }
}