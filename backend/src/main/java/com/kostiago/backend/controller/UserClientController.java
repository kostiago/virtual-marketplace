package com.kostiago.backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kostiago.backend.dto.UserDTO;
import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.services.UserClientService;
import com.kostiago.backend.services.UserDetailService;

@RestController
@RequestMapping("api/client")
public class UserClientController {

    @Autowired
    private UserClientService service;

    @Autowired
    private UserDetailService userDetailService;

    @PreAuthorize("hasAnyRole( 'ROLE_USER')")
    @GetMapping(value = "/users/{me}")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = userDetailService.getMe();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "sign-up")
    public ResponseEntity<UserDTO> signup(@RequestBody UserInsertDTO dto) {

        UserDTO newDto = service.signup(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
