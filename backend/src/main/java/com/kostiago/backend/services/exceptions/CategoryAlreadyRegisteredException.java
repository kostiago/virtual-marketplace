package com.kostiago.backend.services.exceptions;

public class CategoryAlreadyRegisteredException extends RuntimeException {

    public CategoryAlreadyRegisteredException(String message) {
        super(message);
    }
}
