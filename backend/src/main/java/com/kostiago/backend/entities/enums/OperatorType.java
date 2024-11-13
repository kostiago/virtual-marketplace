package com.kostiago.backend.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

public enum OperatorType {

    ADMIN("A", "Admin"),
    LEVEL_I("I", "Level_I"),
    LEVEL_II("II", "Level_II");

    private String code;
    private String description;

    private OperatorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static OperatorType fromCode(String code) {
        for (OperatorType type : OperatorType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        throw new ResourceNotFoundExeception("Código inválido para Operador: " + code);
    }

}
