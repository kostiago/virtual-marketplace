package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.CityDTO;
import com.kostiago.backend.entities.City;
import com.kostiago.backend.repositories.CityRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(PageRequest pageRequest) {

        Page<City> list = repository.findAll(pageRequest);
        return list.map(st -> new CityDTO(st));
    }

    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {

        Optional<City> object = repository.findById(id);
        City entity = object.orElseThrow(() -> new ResourceNotFoundExeception("ID do produto não encontrado"));
        return new CityDTO(entity);
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
