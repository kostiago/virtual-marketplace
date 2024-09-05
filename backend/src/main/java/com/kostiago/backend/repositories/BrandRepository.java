package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.Brand;

@Repository
public interface  BrandRepository extends  JpaRepository<Brand, Long> {
    
}
