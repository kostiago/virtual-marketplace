package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.PermissionDTO;
import com.kostiago.backend.dto.UserDTO;
import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.entities.City;
import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.repositories.CityRepository;
import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.repositories.UserRepository;

import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> list = repository.findAll(pageable);

        return list.map(pers -> new UserDTO(pers));

    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {

        Optional<User> object = repository.findById(id);
        User entity = object.orElseThrow(() -> new ResourceNotFoundExeception("ID '" + id + "' não encontrado"));

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {

        // Cria uma nova Pessoa
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.saveAndFlush(entity);
        emailService.sendEmailText(entity.getEmail(), "Cadastro na virtual marketplace",
                "registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");

        return new UserDTO(entity);

    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {

            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.saveAndFlush(entity);

            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }
    }

    public void delete(Long id) {

        // Verifica se a pessoa existe
        Optional<User> entity = repository.findById(id);

        // Exception caso a pessoa exista
        if (entity.isEmpty()) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Violação de integridade");
        }

    }

    /**
     * METODO AUXILIAR
     *
     * @param dto
     * @param entity
     */
    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setCep(dto.getCep());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());

        // Verifica se o DTO tem um cidade
        City city = cityRepository.findById(dto.getCity().getId())
                .orElseThrow(() -> new ResourceNotFoundExeception("Cidade  não encontrada"));

        entity.setCity(city);

        entity.getPermissions().clear();
        for (PermissionDTO permissionDTO : dto.getPermissions()) {
            Permission permission = permissionRepository.getReferenceById(permissionDTO.getId());
            entity.getPermissions().add(permission);
        }
    }

}
