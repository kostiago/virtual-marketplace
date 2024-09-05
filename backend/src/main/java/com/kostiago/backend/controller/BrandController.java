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

import com.kostiago.backend.entities.Brand;
import com.kostiago.backend.services.BrandService;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    
    @Autowired
    private BrandService service;

    @GetMapping
    public ResponseEntity<List<Brand>> findAll() {
        List<Brand> brand = service.findAll();
        return ResponseEntity.ok().body(brand);
    }


    @PostMapping(value = "/")
    public ResponseEntity<Brand> insert (@RequestBody Brand brand) {
        
        brand = service.insert(brand);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(brand.getId()).toUri();

        return ResponseEntity.created(uri).body(brand);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Brand> update(@RequestBody Brand brand) {
        
        brand = service.update(brand);
        return ResponseEntity.ok().body(brand);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
