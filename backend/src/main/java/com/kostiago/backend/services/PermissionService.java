package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.PermissionDTO;
import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Transactional(readOnly = true)
    public Page<PermissionDTO> findAllPaged(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Permission> list = repository.findAll(pageable);

        return list.map(perm -> new PermissionDTO(perm));
    }

    @Transactional(readOnly = true)
    public PermissionDTO findById(Long id) {

        Optional<Permission> object = repository.findById(id);
        Permission entity = object.orElseThrow(() -> new ResourceNotFoundExeception("ID '" + id + "' não encontrado"));

        return new PermissionDTO(entity);
    }

    @Transactional
    public PermissionDTO insert(PermissionDTO dto) {

        Optional<Permission> permissionAlready = repository.findByName(dto.getName());

        if (permissionAlready.isPresent()) {
            throw new AlreadyRegisteredException("Permissão '" + dto.getName() + "' já cadastrado!");
        }

        Permission entity = new Permission();
        entity.setName(dto.getName());
        entity = repository.saveAndFlush(entity);

        return new PermissionDTO(entity);
    }

    @Transactional
    public PermissionDTO update(Long id, PermissionDTO dto) {

        try {
            Permission entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.saveAndFlush(entity);

            return new PermissionDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {

        Optional<Permission> permission = repository.findById(id);

        if (permission.isEmpty()) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Violação de integridade: " + e.getMessage());
        }
    }

}
