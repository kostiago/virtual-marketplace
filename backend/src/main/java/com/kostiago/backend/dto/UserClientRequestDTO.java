package com.kostiago.backend.dto;

import org.springframework.beans.BeanUtils;

import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.enums.UserSituation;

public class UserClientRequestDTO {

    private String name;

    private String cpf;

    private String email;

    private String address;

    private String cep;

    private UserSituation situation;

    private CityDTO city;

    public UserClientRequestDTO() {
    }

    public User convertUser(UserClientRequestDTO userClientRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userClientRequestDTO, user);
        return user;
    }

    public UserClientRequestDTO(String name, String cpf, String email, String address, String cep,
            UserSituation situation) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.address = address;
        this.cep = cep;

    }

    public UserClientRequestDTO(User entity) {
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
        this.cep = entity.getCep();

        this.situation = entity.getSituation();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public UserSituation getSituation() {
        return situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

}
