package com.kostiago.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.kostiago.backend.dto.UserPasswordRecoveryDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.services.UserManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {

    @Autowired
    private UserManagementService service;

    @PostMapping("/request-code")
    public String requestCode(@Valid @RequestBody User dto) {
        return service.requestCode(dto.getEmail());
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @RequestBody UserPasswordRecoveryDTO dto) {
        return service.changePassword(dto);
    }

}
