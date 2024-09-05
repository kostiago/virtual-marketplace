package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.Category;

@Repository
public interface  CategoryRepository extends JpaRepository<Category, Long> {
    
}
