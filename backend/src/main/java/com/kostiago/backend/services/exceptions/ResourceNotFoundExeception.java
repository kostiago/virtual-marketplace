package com.kostiago.backend.services.exceptions;

public class ResourceNotFoundExeception extends RuntimeException {

    public ResourceNotFoundExeception(String message) {
        super(message);
    }
}
