package com.kostiago.backend.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.kostiago.backend.services.exceptions.FieldMessage;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
