package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.Person;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Long> {
    
}
