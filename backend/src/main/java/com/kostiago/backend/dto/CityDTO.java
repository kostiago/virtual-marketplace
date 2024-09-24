package com.kostiago.backend.dto;

import java.io.Serializable;

import com.kostiago.backend.entities.City;

public class CityDTO implements Serializable {

    private Long id;

    private String name;

    private StateDTO state;

    public CityDTO() {
    }

    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public CityDTO(City entity) {
        this.id = entity.getId();
        this.name = entity.getName();

        if (entity.getState() != null) {
            this.state = new StateDTO(entity.getState());
        }
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

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

}
