package com.kostiago.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.entities.Brand;
import com.kostiago.backend.repositories.BrandRepository;



@Service
public class BrandService {

    @Autowired
    private BrandRepository repository;

    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Brand insert(Brand brand) {
        Brand addBrand = repository.saveAndFlush(brand);
        return addBrand;
    }

    @Transactional
    public Brand update(Brand brand) {
        Brand updateBrand = repository.saveAndFlush(brand);
        return updateBrand;
    }

    @Transactional
    public void delete(Long id) {
        Brand brand = repository.findById(id).get();
        repository.delete(brand);
    }
    
}
