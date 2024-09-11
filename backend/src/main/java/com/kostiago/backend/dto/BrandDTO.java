package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;

import com.kostiago.backend.entities.Brand;

public class BrandDTO implements Serializable {

    private Long id;

    private String name;

    private Instant createDate = Instant.now();

    private Instant updateDate = Instant.now();

    public BrandDTO() {
    }

    public BrandDTO(Long id, String name, Instant createDate, Instant updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public BrandDTO(Brand entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.createDate = entity.getCreateDate();
        this.updateDate = entity.getUpdateDate();
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

}
