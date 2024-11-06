package com.kostiago.backend.services;

import java.time.Instant;
import java.util.Date;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.UserPasswordRecoveryDTO;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.UserVerifying;
import com.kostiago.backend.entities.enums.UserSituation;
import com.kostiago.backend.repositories.UserRepository;
import com.kostiago.backend.repositories.UserVerifyindRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserVerifyindRepository userVerifyindRepository;

    @Transactional
    public String verifyUserCode(String verificationCode) {
        UserVerifying verifying = userVerifyindRepository.findByUuid(verificationCode);

        if (verifying == null) {
            return "Código de verificação inválido";
        }

        Instant now = Instant.now();
        if (now.isAfter(verifying.getCodeExpirationDate())) {
            return "Código expirado, solicite um novo código";
        }

        // Código válido - confirmar o usuário
        User user = verifying.getUser();
        user.setSituation(UserSituation.ATIVO);
        repository.save(user);

        // Remover a verificação, se desejado
        userVerifyindRepository.delete(verifying);

        return "Usuário verificado com sucesso";
    }

    /**
     * METODO PARA RECUPERAR SENHA
     * 
     * @param email ENVIA CODIGO PARA O EMAIL
     * @return
     */
    @Transactional
    public String requestCode(String email) {

        User user = repository.findByEmail(email).get();
        user.setPasswordRecoveryCode(getPasswordRecoveryCode(user.getId()));
        user.setDateSendingCode(new Date());

        repository.saveAndFlush(user);

        emailService.sendEmailText(user.getEmail(), "Código de Recuperação de Senha", "Olá, '" + user.getName()
                + "' seu codigo de verificação para recuperação de senha é:" + user.getPasswordRecoveryCode());

        return "Codigo enviando!";

    }

    @Transactional
    public String changePassword(UserPasswordRecoveryDTO dto) {

        User entity = repository
                .findByEmailAndPasswordRecoveryCode(dto.getEmail(), dto.getPasswordRecoveryCode());

        if (entity != null) {
            Date difference = new Date(new Date().getTime() - entity.getDateSendingCode().getTime());

            if (difference.getTime() / 1000 < 60) {

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
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String resendVerificationCode(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-mail fornecido não encontrado" + email));

        if (user.getSituation() != UserSituation.PENDENTE) {
            throw new ResourceNotFoundExeception("Usuário já está ativo ou em situação inválida.");
        }

        UserVerifying verifying = userVerifyindRepository.findUserById(user.getId())
                .orElseGet(() -> new UserVerifying(user));

        String newVerificationCode = getPasswordRecoveryCode(user.getId());
        verifying.setUuid(newVerificationCode);
        verifying.setCodeExpirationDate(Instant.now().plusMillis(900000));

        userVerifyindRepository.saveAndFlush(verifying);

        emailService.sendEmailText(user.getEmail(), "Código de Recuperação de Senha", "Olá, '" + user.getName()
                + "' seu codigo de verificação de conta é:" + newVerificationCode);

        return "Codigo enviando!";

    }

}
