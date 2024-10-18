package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.UserClientRequestDTO;
import com.kostiago.backend.dto.UserDTO;

import com.kostiago.backend.entities.City;
import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.enums.UserSituation;
import com.kostiago.backend.repositories.CityRepository;
import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.repositories.UserRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class UserClientService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public UserDTO signup(UserClientRequestDTO dto) {

        User entity = new User();

        copyDtoToEntity(dto, entity);
        entity.setSituation(UserSituation.PENDENTE);

        Permission clientPermission = permissionRepository
                .findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundExeception("Permissão 'ROLE_USER' não encontrada"));

        entity.getPermissions().add(clientPermission);
        entity.setId(null);

        // Verifica se o DTO tem um cidade
        City city = cityRepository.findById(dto.getCity().getId())
                .orElseThrow(() -> new ResourceNotFoundExeception("Cidade  não encontrada"));

        entity.setCity(city);

        repository.saveAndFlush(entity);

        emailService.sendEmailText(entity.getEmail(), "Cadastro na loja Cubos", "Olá, '" + entity.getName()
                + "' seu cadastro na loja Cubos foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!"
                + entity.getPasswordRecoveryCode());

        return new UserDTO(entity);
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

    private void copyDtoToEntity(UserClientRequestDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setCep(dto.getCep());

    }

    public void bindClientPermission(UserDTO dto) {

        User user = new User();

        BeanUtils.copyProperties(dto, user);

        Permission clientPermission = permissionRepository.findByName("ROLE_USER").get();

        if (clientPermission != null) {
            user.getPermissions().add(clientPermission);

            repository.saveAndFlush(user);
        }
    }
}
