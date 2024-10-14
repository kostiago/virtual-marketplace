package com.kostiago.backend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.kostiago.backend.dto.UserUpdateDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.repositories.UserRepository;

import com.kostiago.backend.services.exceptions.FieldMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        // Verifica se o email ja existe no banco de dados
        User emailAlready = repository.findByEmail(dto.getEmail()).get();

        if (emailAlready != null && userId != emailAlready.getId()) {
            list.add(new FieldMessage("email", "Email '" + dto.getEmail() + "' já cadastrado!"));
        }

        // Verifica se o CPF ja existe no banco de dados
        User cpfAlready = repository.findByCpf(dto.getCpf());

        if (cpfAlready != null && userId != cpfAlready.getId()) {
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
