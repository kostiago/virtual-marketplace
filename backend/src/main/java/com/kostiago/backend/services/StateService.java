package com.kostiago.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kostiago.backend.dto.StateDTO;
import com.kostiago.backend.entities.State;
import com.kostiago.backend.repositories.StateRepository;
import com.kostiago.backend.services.exceptions.DatabaseException;
import com.kostiago.backend.services.exceptions.InvalidAcronymException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    @Transactional(readOnly = true)
    public List<StateDTO> findAll() {

        List<State> list = repository.findAll();
        return list.stream().map(st -> new StateDTO(st)).collect(Collectors.toList());
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
            throw new InvalidAcronymException("A sigla deve ter exatamente dois caracteres.");
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
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundExeception("ID não encontrado " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

}
