package com.kostiago.backend.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.PermissionDTO;
import com.kostiago.backend.dto.UserDTO;
import com.kostiago.backend.dto.UserInsertDTO;
import com.kostiago.backend.entities.Permission;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.ViaCepResponse;
import com.kostiago.backend.entities.enums.UserSituation;

import com.kostiago.backend.repositories.PermissionRepository;
import com.kostiago.backend.repositories.UserRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;
import com.nimbusds.jose.shaded.gson.Gson;

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

    @Transactional
    public UserDTO signup(UserInsertDTO dto) {

        User entity = new User();

        copyDtoToEntity(dto, entity);

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Defini a situação com pendente
        entity.setSituation(UserSituation.PENDENTE);

        // Atribui a permissão padrão 'ROLE_USER
        entity.getPermissions().add(findClientPermission());

        // Garante que o ID seja nulo para novo cadastro
        entity.setId(null);

        repository.saveAndFlush(entity);

        // Envia o e-mail de confirmação
        sendSignupConfirmationEmail(entity);

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

    // Método privado para buscar a permissão de cliente
    private Permission findClientPermission() {
        return permissionRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundExeception("Permissão 'ROLE_USER' não encontrada"));

    }

    // Método privado para enviar e-mail de confirmação
    private void sendSignupConfirmationEmail(User entity) {

        emailService.sendEmailText(entity.getEmail(), "Cadastro na loja Cubos", "Olá, '" + entity.getName()
                + "' seu cadastro na loja Cubos foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!"
                + entity.getPasswordRecoveryCode());
    }

}
