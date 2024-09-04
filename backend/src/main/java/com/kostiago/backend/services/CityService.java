package com.kostiago.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.entities.City;
import com.kostiago.backend.repositories.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<City> findAll() {
        return repository.findAll();
    }

    @Transactional
    public City insert(City city) {

        City addCity = repository.saveAndFlush(city);
        return addCity;
    }

    @Transactional
    public City update(City city) {

        City updateCity = repository.saveAndFlush(city);
        return updateCity;

    }

    @Transactional
    public void delete(Long id) {
        City city = repository.findById(id).get();
        repository.delete(city);
    }

}
