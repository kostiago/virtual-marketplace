package com.kostiago.backend.services;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.PermissionDTO;

import com.kostiago.backend.dto.UserDTO;
import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.dto.UserUpdateDTO;

import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.ViaCepResponse;

import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.repositories.UserRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;
import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

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

        return new UserDTO(entity);

    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
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

    private ViaCepResponse getAdressFromViaCep(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String cepLine = "";
            StringBuilder jsonCep = new StringBuilder();

            while ((cepLine = br.readLine()) != null) {
                jsonCep.append(cepLine);
            }

            System.err.println(jsonCep.toString());

            return new Gson().fromJson(jsonCep.toString(), ViaCepResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * METODO AUXILIAR
     */
    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());

        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());

        entity.setCep(dto.getCep());

        ViaCepResponse viaCepResponse = getAdressFromViaCep(entity.getCep());

        entity.setLogradouro(viaCepResponse.getLogradouro());
        entity.setBairro(viaCepResponse.getBairro());
        entity.setLocalidade(viaCepResponse.getLocalidade());
        entity.setUf(viaCepResponse.getUf());
        entity.setComplemento(viaCepResponse.getComplemento());

        entity.getPermissions().clear();
        for (PermissionDTO permissionDTO : dto.getPermissions()) {
            Permission permission = permissionRepository.getReferenceById(permissionDTO.getId());
            entity.getPermissions().add(permission);
        }
    }
}
