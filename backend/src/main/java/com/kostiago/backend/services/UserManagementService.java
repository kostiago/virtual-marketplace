package com.kostiago.backend.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kostiago.backend.dto.UserPasswordRecoveryDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.repositories.UserRepository;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String requestCode(String email) {

        User user = repository.findByEmail(email);
        user.setPasswordRecoveryCode(getPasswordRecoveryCode(user.getId()));
        user.setDateSendingCode(new Date());

        repository.saveAndFlush(user);

        emailService.sendEmailText(user.getEmail(), "Código de Recuperação de Senha", "Olá, '" + user.getName()
                + "' seu codigo de verificação para recuperação de senha é:" + user.getPasswordRecoveryCode());

        return "Codigo enviando!";

    }

    public String changePassword(UserPasswordRecoveryDTO dto) {

        User entity = repository
                .findByEmailAndPasswordRecoveryCode(dto.getEmail(), dto.getPasswordRecoveryCode());

        if (entity != null) {
            Date difference = new Date(new Date().getTime() - entity.getDateSendingCode().getTime());

            if (difference.getTime() / 1000 < 900) {

                entity.setPassword(passwordEncoder.encode(dto.getPassword()));
                entity.setPasswordRecoveryCode(null);
                repository.saveAndFlush(entity);

                return "Senha alterada";
            } else {
                return "Tempo expirado, solicite um novo codigo";
            }
        } else {
            return "Email ou codigo não encontrado";
        }

    }

    private String getPasswordRecoveryCode(Long id) {

        DateFormat format = new SimpleDateFormat("ddMMyyyyHHssmm");
        return format.format(new Date()) + id;
    }
}
