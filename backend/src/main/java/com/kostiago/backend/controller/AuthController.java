package com.kostiago.backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kostiago.backend.dto.UserDTO;
import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.services.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping(value = "sign-up")
    public ResponseEntity<UserDTO> signup(@RequestBody UserInsertDTO dto) {

        UserDTO newDto = service.signup(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }
}
