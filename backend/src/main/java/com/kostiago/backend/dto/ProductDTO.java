package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kostiago.backend.entities.Category;
import com.kostiago.backend.entities.Product;
import com.kostiago.backend.entities.ProductImage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO implements Serializable {

    private Long id;

    @Size(min = 5, max = 60, message = "Nome deve ter entre 5 e 60 caracteres!")
    @NotBlank(message = "O campo nome é obrigatorio.")
    private String name;

    @Size(min = 10, max = 30, message = "Nome deve ter entre 5 e 60 caracteres!")
    @NotBlank(message = "O campo nome é obrigatorio")
    private String shortDescription;

    @Size(min = 10, message = "A descrição deve ter no minimo 10 caracteres!")
    @NotBlank(message = "O campo descrição é obrigatorio.")
    private String description;

    @Positive(message = "Preço deve ser maior que zero.")
    private Double price;

    @Positive(message = "Preço de venda deve ser maior que zero.")
    private Double sale;

    private Instant createDate;
    private Instant updateDate;

    // Atributo para o relacionamento ManyToOne com Brand
    private BrandDTO brand;

    // Atributo para o relacionamento ManyToMany com Category
    private List<CategoryDTO> categories = new ArrayList<>();

    // Atributo para o relacionamento ManyToMany com ProductImage
    private List<ProductImageDTO> images = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Instant createDate, String description, Long id, String name, Double price,
            Double sale, String shortDescription, Instant updateDate) {

        this.createDate = createDate;
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.shortDescription = shortDescription;
        this.updateDate = updateDate;
    }

    public ProductDTO(Product entity) {

        this.createDate = entity.getCreateDate();
        this.description = entity.getDescription();
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.sale = entity.getSale();
        this.shortDescription = entity.getShortDescription();
        this.updateDate = entity.getUpdateDate();

        // Converte a entidade brand em DTO (ManyToOne)
        if (entity.getBrand() != null) {
            this.brand = new BrandDTO(entity.getBrand());
        }
    }

    public ProductDTO(Product entity, Set<Category> categories, Set<ProductImage> images) {

        this(entity);

        // ManyToMany: Convertendo as entidades Category em CategoryDTO
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));

        images.forEach(img -> this.images.add(new ProductImageDTO(img)));
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<ProductImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDTO> images) {
        this.images = images;
    }

}
