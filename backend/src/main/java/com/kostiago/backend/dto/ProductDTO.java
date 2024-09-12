package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kostiago.backend.entities.Brand;
import com.kostiago.backend.entities.Category;
import com.kostiago.backend.entities.Product;


public class ProductDTO implements  Serializable {

    private Integer id;
    private String name;
    private String shortDescription;
    private String description;
    private Double price;
    private Double sale;   
    private Instant createDate;
    private Instant updateDate;

    //Atributo para o relacionamento ManyToOne com Brand
    private BrandDTO brand;

    //Atributo para o relacionamento ManyToMany com Category
    private List<CategoryDTO> category = new ArrayList<>();

    public ProductDTO() {}

    public ProductDTO(BrandDTO brand, Instant createDate, String description, Integer id, String name, Double price, Double sale, String shortDescription, Instant updateDate) {
        this.brand = brand;
        this.createDate = createDate;
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.shortDescription = shortDescription;
        this.updateDate = updateDate;
    }

    public ProductDTO( Product entity) {
        
        this.createDate = entity.getCreateDate();
        this.description = entity.getDescription();
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.sale = entity.getSale();
        this.shortDescription = entity.getShortDescription();
        this.updateDate = entity.getUpdateDate();

        //Converte a entidade brand em DTO (ManyToOne)
        if(entity.getBrand() != null) {
            this.brand = new BrandDTO(entity.getBrand());
        }
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        
        this(entity);

        // ManyToMany: Convertendo as entidades Category em CategoryDTO

        categories.forEach(cat -> this.category.add(new CategoryDTO(cat)));
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
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

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public List<CategoryDTO> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryDTO> category) {
        this.category = category;
    }

    
    
}
