package com.kostiago.backend.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kostiago.backend.entities.State;
import com.kostiago.backend.repositories.StateRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    @Transactional(readOnly = true)
    public List<State> findAll() {
        return repository.findAll();
    }

    @Transactional
    public State insert(State state) {

        state.setCreateDate(new Date(0));
        State addState = repository.saveAndFlush(state);
        return addState;
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
