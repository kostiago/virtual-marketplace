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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kostiago.backend.dto.StateDTO;

import com.kostiago.backend.services.StateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    private StateService service;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {

        List<StateDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable Long id) {

        StateDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/")
    public ResponseEntity<StateDTO> insert(@Valid @RequestBody StateDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StateDTO> update(@PathVariable Long id, @RequestBody StateDTO dto) {

        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
