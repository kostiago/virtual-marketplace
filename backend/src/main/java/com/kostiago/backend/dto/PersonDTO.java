package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.Person;

public class PersonDTO implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private String cep;
    private Instant createDate;
    private Instant updateDate;

    private CityDTO city;

    private List<PermissionDTO> permissions = new ArrayList<>();

    public PersonDTO() {
    }

    public PersonDTO(String address, String cep, String cpf, Instant createDate, String email, Long id, String name,
            Instant updateDate) {
        this.address = address;
        this.cep = cep;
        this.cpf = cpf;
        this.createDate = createDate;
        this.email = email;
        this.id = id;
        this.name = name;
        this.updateDate = updateDate;
    }

    public PersonDTO(Person entity) {
        this.address = entity.getAddress();
        this.cep = entity.getCep();
        this.cpf = entity.getCpf();
        this.createDate = entity.getCreateDate();
        this.email = entity.getEmail();
        this.id = entity.getId();
        this.name = entity.getName();
        this.updateDate = entity.getUpdateDate();

        if (entity.getCity() != null) {
            this.city = new CityDTO(entity.getCity());
        }
    }

    public PersonDTO(Person entity, Set<Permission> permissions) {
        this(entity);

        permissions.forEach(personPermission -> this.permissions.add(new PermissionDTO(personPermission)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

}
