package com.kostiago.backend.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kostiago.backend.dto.StateDTO;
import com.kostiago.backend.entities.State;
import com.kostiago.backend.repositories.StateRepository;
import com.kostiago.backend.services.execptions.ResourceNotFoundExeception;

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

        State entity = new State();
        entity.setName(dto.getName());
        entity = repository.saveAndFlush(entity);
        return new StateDTO(entity);
    }

    public State update(State state) {
        state.setUpdateDate(new Date(0));
        return repository.saveAndFlush(state);
    }

    public void delete(Long id) {
        State state = repository.findById(id).get();
        repository.delete(state);
    }

}
