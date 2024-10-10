package com.kostiago.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.CategoryDTO;
import com.kostiago.backend.dto.ProductDTO;
import com.kostiago.backend.entities.Category;
import com.kostiago.backend.entities.Product;
import com.kostiago.backend.repositories.CategoryRepository;
import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.DatabaseException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> list = repository.findAll(pageable);

        return list.map(cat -> new CategoryDTO(cat));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByCategory(Long id) {
        Optional<Category> result = repository.findById(id);
        List<Product> products = new ArrayList<>(result.get().getProducts());
        return products.stream().map(x -> new ProductDTO(x)).toList();

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        Optional<Category> object = repository.findById(id);
        Category entity = object.orElseThrow(() -> new ResourceNotFoundExeception("Categoria não encontrada"));

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {

        // Verfica se a categoria ja existe
        Optional<Category> categoryAlready = repository.findByName(dto.getName());

        // Exception caso a categoria ja exista
        if (categoryAlready.isPresent()) {
            throw new AlreadyRegisteredException("Categoria '" + dto.getName() + "' já cadastrada");
        }

        // Se não existir criar nova categoria
        Category entity = new Category();
        entity.setName(dto.getName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
        entity = repository.saveAndFlush(entity);

        return new CategoryDTO(entity);

    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.saveAndFlush(entity);

            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID'" + id + "'não encontrado");
        }
    }

    private void copyDtoToEntity(CategoryDTO dto, Category entity) {
        entity.setName(dto.getName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
    }

    public void delete(Long id) {

        // Verifica se a categoria existe
        Optional<Category> category = repository.findById(id);

        // Exception caso o categoria não exista
        if (category.isEmpty()) {
            throw new ResourceNotFoundExeception("ID'" + id + "'não encontrado");
        }

        // Bloco TryCatch para deletar pelo ID
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de Integridade" + e.getMessage());
        }
    }

}
