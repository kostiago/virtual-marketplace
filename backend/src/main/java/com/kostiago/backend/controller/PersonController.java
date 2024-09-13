package com.kostiago.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kostiago.backend.dto.PersonDTO;
import com.kostiago.backend.services.PersonService;


@RestController
@RequestMapping("api/person")
public class PersonController {
    
    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> findAll(
        @RequestParam (defaultValue = "0") Integer page,
        @RequestParam (defaultValue = "4") Integer size
    ) 
    {
        Page<PersonDTO> dto = service.findAllPaged(page,size);

        return ResponseEntity.ok().body(dto);
    }
}
