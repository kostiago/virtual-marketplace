package com.kostiago.backend.dto;

import java.io.Serializable;

import com.kostiago.backend.entities.ProductImage;

public class ProductImageDTO implements Serializable {

    private Long id;

    private String name;

    public ProductImageDTO() {
    }

    public ProductImageDTO(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public ProductImageDTO(ProductImage entity) {
        this.id = entity.getId();
        this.name = entity.getName();

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

}
