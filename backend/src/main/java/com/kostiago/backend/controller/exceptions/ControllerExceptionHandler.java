package com.kostiago.backend.controller.exceptions;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kostiago.backend.services.exceptions.InvalidAcronymException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExeception.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundExeception e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidAcronymException.class)
    public ResponseEntity<StandardError> invalidAcronym(InvalidAcronymException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Invalid Acronym");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> database(DataIntegrityViolationException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database Exception");
        error.setMessage("Estado não pode ser deletado, há cidades vinculadas!");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }
}