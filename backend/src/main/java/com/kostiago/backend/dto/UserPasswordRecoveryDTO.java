package com.kostiago.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserPasswordRecoveryDTO {

    @Email(message = "Informe o email")
    private String email;

    @NotBlank(message = "Campo Obrigatorio")
    private String passwordRecoveryCode;

    @NotBlank(message = "Campo Obrigatorio")
    private String password;

    public UserPasswordRecoveryDTO() {
    }

    public UserPasswordRecoveryDTO(@Email(message = "Informe o email") String email,
            @NotBlank(message = "Campo Obrigatorio") String passwordRecoveryCode,
            @NotBlank(message = "Campo Obrigatorio") String password) {
        this.email = email;
        this.passwordRecoveryCode = passwordRecoveryCode;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordRecoveryCode() {
        return passwordRecoveryCode;
    }

    public void setPasswordRecoveryCode(String passwordRecoveryCode) {
        this.passwordRecoveryCode = passwordRecoveryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
