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

import com.kostiago.backend.entities.City;
import com.kostiago.backend.services.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        List<City> cities = service.findAll();
        return ResponseEntity.ok().body(cities);
    }

    @PostMapping(value = "/")
    public ResponseEntity<City> insert(@RequestBody City city) {

        city = service.insert(city);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(city.getId()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @PutMapping(value = "/")
    public ResponseEntity<City> update(@RequestBody City city) {
        city = service.update(city);
        return ResponseEntity.ok().body(city);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
