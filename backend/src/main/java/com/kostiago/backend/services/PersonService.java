package com.kostiago.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.PersonDTO;
import com.kostiago.backend.entities.Person;
import com.kostiago.backend.repositories.CityRepository;
import com.kostiago.backend.repositories.PersonRepository;


@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private CityRepository CityRepository;

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAllPaged(Integer page, Integer size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Person> list = repository.findAll(pageable);

        return  list.map(pers -> new PersonDTO(pers));
        
    }
    
}
