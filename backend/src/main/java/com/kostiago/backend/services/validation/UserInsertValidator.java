package com.kostiago.backend.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.repositories.UserRepository;

import com.kostiago.backend.services.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Verifica se o email ja existe no banco de dados
        User emailAlready = repository.findByEmail(dto.getEmail()).get();
        if (emailAlready != null) {
            list.add(new FieldMessage("email", "Email '" + dto.getEmail() + "' já cadastrado!"));
        }

        // Verifica se o CPF ja existe no banco de dados
        User cpfAlready = repository.findByCpf(dto.getCpf());
        if (cpfAlready != null) {
            list.add((new FieldMessage("cpf", "CPF '" + dto.getCpf() + "' já cadastrado!")));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
