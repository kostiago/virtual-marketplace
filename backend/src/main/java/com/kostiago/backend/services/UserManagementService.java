package com.kostiago.backend.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kostiago.backend.dto.UserPasswordRecoveryDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.repositories.UserRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String requestCode(String email) {

        User user = repository.findByEmail(email).get();
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

    // Método para envio da senha inicial após cadastro de admins
    public String sendInitialPassword(UserPasswordRecoveryDTO dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundExeception("Usuario não encontrado"));

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPasswordRecoveryCode(tempPassword);
        user.setDateSendingCode(new Date());

        repository.saveAndFlush(user);

        String subject = "Bem-vindo ao Sistema Administrativo";
        String message = "Olá, " + user.getName() + ", sua conta foi criada com sucesso. "
                + "Use o e-mail e o código abaixo para configurar sua senha no sistema: "
                + "\nCódigo de configuração de senha: " + tempPassword;

        emailService.sendEmailText(user.getEmail(), subject, message);

        return "Código enviado com sucesso!";
    }

    // Método para validar e definir a senha usando o código temporário (com o DTO)
    public String setInitialPassword(UserPasswordRecoveryDTO dto) {
        User entity = repository.findByEmailAndPasswordRecoveryCode(dto.getEmail(), dto.getPasswordRecoveryCode());

        if (entity != null) {
            Date difference = new Date(new Date().getTime() - entity.getDateSendingCode().getTime());
            if (difference.getTime() / 1000 < 900) {
                entity.setPassword(passwordEncoder.encode(dto.getPassword()));
                entity.setPasswordRecoveryCode(null);
                repository.saveAndFlush(entity);

                return "Senha alterada com sucesso!";
            } else {
                return "Tempo expirado, solicite um novo codigo";
            }
        } else {
            return "Email ou codigo não encontrado";
        }
    }

    private String getPasswordRecoveryCode(Long id) {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
