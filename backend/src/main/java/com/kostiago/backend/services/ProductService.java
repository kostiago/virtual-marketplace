package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.CategoryDTO;
import com.kostiago.backend.dto.ProductDTO;
import com.kostiago.backend.entities.Brand;
import com.kostiago.backend.entities.Category;
import com.kostiago.backend.entities.Product;
import com.kostiago.backend.repositories.BrandRepository;
import com.kostiago.backend.repositories.CategoryRepository;
import com.kostiago.backend.repositories.ProductRepository;
import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> list = repository.findAll(pageable);

        return list.map(prod -> new ProductDTO(prod));

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Optional<Product> object = repository.findById(id);
        Product entity = object.orElseThrow(() -> new ResourceNotFoundExeception("Produto não encontrado"));

        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        // Verfica se o Produto ja existe
        Optional<Product> productAlready = repository.findByName(dto.getName());

        // Exception caso o Produto ja exista
        if (productAlready.isPresent()) {
            throw new AlreadyRegisteredException("Produto '" + dto.getName() + "' já cadastrado!");
        }

        // Se não existir cria um novo Produto
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.saveAndFlush(entity);

        return new ProductDTO(entity);

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setShortDescription(dto.getShortDescription());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setSale(dto.getSale());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());

        Brand brand = brandRepository.findById(dto.getBrand()
                .getId()).orElseThrow(() -> new ResourceNotFoundExeception("Marca não encontrada"));

        entity.setBrand(brand);
        System.err.println("estou aqui TALVES");

        // ManyToMany
        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            System.err.println("estou aqui");
            Category cat = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(cat);
        }
    }

}
