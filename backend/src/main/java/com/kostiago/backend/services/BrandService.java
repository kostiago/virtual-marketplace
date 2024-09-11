package com.kostiago.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.BrandDTO;
import com.kostiago.backend.entities.Brand;
import com.kostiago.backend.repositories.BrandRepository;
import com.kostiago.backend.services.exceptions.AlreadyRegisteredException;
import com.kostiago.backend.services.exceptions.DatabaseException;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repository;

    @Transactional(readOnly = true)
    public Page<BrandDTO> findAllPaged(Integer page, Integer size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Brand> list = repository.findAll(pageable);

        return list.map(br -> new BrandDTO(br));
    }

    @Transactional(readOnly = true)
    public BrandDTO findById(Long id) {

        Optional<Brand> object = repository.findById(id);
        Brand entity = object
                .orElseThrow(() -> new ResourceNotFoundExeception("Não foi possivel localizar a marca informada"));

        return new BrandDTO(entity);
    }

    @Transactional
    public BrandDTO insert(BrandDTO dto) {

        // Verfica se a marca ja está cadastrada
        Optional<Brand> brandAlready = repository.findByName(dto.getName());

        // Exception caso a marca exista
        if (brandAlready.isPresent()) {
            throw new AlreadyRegisteredException("A Marca '" + dto.getName() + "'já  cadastrada");
        }

        // Cria uma nova marca
        Brand entity = new Brand();
        copyDtoToEntity(dto, entity);
        entity = repository.saveAndFlush(entity);

        return new BrandDTO(entity);

    }

    @Transactional
    public BrandDTO update(Long id, BrandDTO dto) {
        try {
            Brand entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.saveAndFlush(entity);

            return new BrandDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeception("ID '" + id + "' não encontrado");
        }
    }

    public void delete(Long id) {

        // Verifica se a marca existe
        Optional<Brand> entity = repository.findById(id);

        // Exception caso a marca exista
        if (entity.isEmpty()) {
            throw new ResourceNotFoundExeception("ID' " + id + "' não encontrado");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de Integridade" + e.getMessage());
        }
    }

    // Metodo Auxiliar
    private void copyDtoToEntity(BrandDTO dto, Brand entity) {
        entity.setName(dto.getName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());

    }

}
