package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;

import com.kostiago.backend.entities.City;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CityDTO implements Serializable {

    private Long id;

    private String name;

    private Instant createDate;

    private Instant updateDate;

    private StateDTO state;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, Instant createDate, Instant updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public CityDTO(City entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.createDate = entity.getCreateDate();
        this.updateDate = entity.getUpdateDate();

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

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

    @PrePersist
    protected void onCreate() {
        createDate = Instant.now();
        updateDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = Instant.now();
    }
}
