package com.kostiago.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kostiago.backend.dto.ProductImageDTO;
import com.kostiago.backend.services.ProductImageService;

@RestController
@RequestMapping("/api/image")
public class ProductImageController {

    @Autowired
    private ProductImageService service;

    @GetMapping
    public ResponseEntity<Page<ProductImageDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size) {
        Page<ProductImageDTO> dto = service.findAllPaged(page, size);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductImageDTO> findById(@PathVariable Long id) {

        ProductImageDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductImageDTO> insert(@RequestParam("id") Long id, @RequestParam MultipartFile file) {

        ProductImageDTO dto = service.insert(id, file);

        return ResponseEntity.ok().body(dto);
    }
}
