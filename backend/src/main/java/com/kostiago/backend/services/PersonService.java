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
import com.kostiago.backend.dto.PersonDTO;
import com.kostiago.backend.dto.PersonInsertDTO;
import com.kostiago.backend.entities.City;
import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.Person;
import com.kostiago.backend.repositories.CityRepository;
import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.repositories.PersonRepository;
import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAllPaged(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Person> list = repository.findAll(pageable);

        return list.map(pers -> new PersonDTO(pers));

    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {

        Optional<Person> object = repository.findById(id);
        Person entity = object.orElseThrow(() -> new ResourceNotFoundExeception("ID '" + id + "' não encontrado"));

        return new PersonDTO(entity);
    }

    @Transactional
    public PersonDTO insert(PersonInsertDTO dto) {

        // Verfica se a Pessoa ja existe
        Optional<Person> cpfAlready = repository.findByCpf(dto.getCpf());
        Optional<Person> emailAlready = repository.findByEmail(dto.getEmail());

        // Verifica se o CPF ja existe no banco de dados
        if (cpfAlready.isPresent()) {
            throw new AlreadyRegisteredException("CPF '" + dto.getCpf() + "' já cadastrado!");
        }
        // Verifica se o email ja existe no banco de dados
        if (emailAlready.isPresent()) {
            throw new AlreadyRegisteredException("Email '" + dto.getEmail() + "' já cadastrado!");
        }

        // Se não existir cria uma nova Pessoa
        Person entity = new Person();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.saveAndFlush(entity);
        emailService.sendEmailText(entity.getEmail(), "Cadastro na virtual marketplace",
                "registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");

        return new PersonDTO(entity);

    }

    @Transactional
    public PersonDTO update(Long id, PersonDTO dto) {
        try {

            Person entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.saveAndFlush(entity);

            return new PersonDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }
    }

    public void delete(Long id) {

        // Verifica se a pessoa existe
        Optional<Person> entity = repository.findById(id);

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
    private void copyDtoToEntity(PersonDTO dto, Person entity) {
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
