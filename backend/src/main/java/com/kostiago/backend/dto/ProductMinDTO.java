package com.kostiago.backend.dto;

import java.io.Serializable;
import java.util.Iterator;

import com.kostiago.backend.entities.Product;
import com.kostiago.backend.entities.ProductImage;

public class ProductMinDTO implements Serializable {

    private Integer id;

    private String name;

    private Double sale;

    private String imageName;

    public ProductMinDTO() {
    }

    public ProductMinDTO(Integer id, String name, Double sale, String imageName) {

        this.id = id;
        this.name = name;
        this.sale = sale;
        this.imageName = imageName;

    }

    public ProductMinDTO(Product entity) {

        this.id = entity.getId();
        this.name = entity.getName();

        this.sale = entity.getSale();

        if (!entity.getImages().isEmpty()) {
            Iterator<ProductImage> firstImage = entity.getImages().iterator();
            this.imageName = firstImage.next().getName();
        } else {
            this.imageName = null;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
