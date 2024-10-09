package com.kostiago.backend.controller.exceptions;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.DatabaseException;
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

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<StandardError> categoryAlready(AlreadyRegisteredException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Object Already Registered in the Database");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();

        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);

    }
}
