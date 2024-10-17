package com.kostiago.backend.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserSituation {

    ATIVO("A", "Ativo"),
    INATIVO("I", "Inativo"),
    PENDENTE("P", "Pendente");

    private String code;
    private String description;

    private UserSituation(String code, String description) {
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

    @JsonCreator
    public static UserSituation doValue(String code) {

        if (code.equals("A")) {
            return ATIVO;
        } else if (code.equals("I")) {
            return INATIVO;
        }

        else if (code.equals("P")) {
            return PENDENTE;
        }

        else {
            return null;
        }

    }

}
