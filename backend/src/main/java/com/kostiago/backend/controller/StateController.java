package com.kostiago.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kostiago.backend.entities.State;
import com.kostiago.backend.services.StateService;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    private StateService service;

    @GetMapping
    public ResponseEntity<List<State>> findAll() {

        List<State> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/")
    public ResponseEntity<State> insert(@RequestBody State state) {
        state = service.insert(state);
        return ResponseEntity.ok().body(state);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<State> update(@RequestBody State state) {
        state = service.update(state);
        return ResponseEntity.ok().body(state);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
