package com.kostiago.backend.services;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.StateDTO;
import com.kostiago.backend.entities.State;
import com.kostiago.backend.repositories.StateRepository;
import com.kostiago.backend.services.exceptions.InvalidAcronymException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    @Transactional(readOnly = true)
    public Page<StateDTO> findAllPaged(PageRequest pageRequest) {

        Page<State> list = repository.findAll(pageRequest);
        return list.map(st -> new StateDTO(st));
    }

    @Transactional(readOnly = true)
    public StateDTO findById(Long id) {
        Optional<State> object = repository.findById(id);
        State entity = object.orElseThrow(() -> new ResourceNotFoundExeception("Entidade não encontrada"));

        return new StateDTO(entity);
    }

    @Transactional
    public StateDTO insert(StateDTO dto) {

        // Validar Sigla do estado
        if (dto.getAcronym() == null || dto.getAcronym().length() != 2) {
            throw new InvalidAcronymException("A sigla deve ter dois caracteres.");
        }

        State entity = new State();
        entity.setName(dto.getName());
        entity.setAcronym(dto.getAcronym());
        entity = repository.saveAndFlush(entity);
        return new StateDTO(entity);
    }

    @Transactional
    public StateDTO update(Long id, StateDTO dto) {

        try {
            State entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setAcronym(dto.getAcronym());
            entity.setUpdateDate(dto.getUpdateDate());
            entity = repository.saveAndFlush(entity);

            return new StateDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID não encontrado " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        Optional<State> state = repository.findById(id);

        if (state.isEmpty()) {
            throw new ResourceNotFoundExeception("ID não encontrado " + id);
        }
        
            repository.deleteById(id);
        
    }

}
