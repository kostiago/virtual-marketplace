package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.CategoryDTO;
import com.kostiago.backend.entities.Category;
import com.kostiago.backend.repositories.CategoryRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

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
    public CategoryDTO findById(Long id) {

        Optional<Category> object = repository.findById(id);
        Category entity = object.orElseThrow(() -> new ResourceNotFoundExeception("Objeto não encontrado"));

        return new CategoryDTO(entity);
    }

}
