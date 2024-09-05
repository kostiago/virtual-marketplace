package com.kostiago.backend.services.exceptions;

public class InvalidAcronymException extends RuntimeException {

    public InvalidAcronymException(String menssage) {
        super(menssage);
    }
}
