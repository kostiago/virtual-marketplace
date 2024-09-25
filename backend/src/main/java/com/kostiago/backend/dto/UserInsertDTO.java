package com.kostiago.backend.dto;

import com.kostiago.backend.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    private String password;

    public UserInsertDTO() {
    }

    public UserInsertDTO(String password) {
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
