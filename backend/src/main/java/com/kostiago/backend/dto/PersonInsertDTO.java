package com.kostiago.backend.dto;

public class PersonInsertDTO extends PersonDTO {

    private String password;

    public PersonInsertDTO() {
    }

    public PersonInsertDTO(String password) {
        super();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
