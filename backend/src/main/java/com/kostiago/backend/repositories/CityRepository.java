package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
