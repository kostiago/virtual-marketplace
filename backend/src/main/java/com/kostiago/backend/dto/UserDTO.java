package com.kostiago.backend.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.enums.UserSituation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 5, max = 60, message = "Nome deve ter entre 5 e 60 caracteres")
    private String name;

    private String cpf;

    @Email(message = "Informe um email válido")
    private String email;

    private String cep;

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    private Instant createDate;
    private Instant updateDate;

    private String passwordRecoveryCode;
    private Date dateSendingCode;

    private UserSituation situation;

    private List<PermissionDTO> permissions = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(String cep, String cpf, Instant createDate, String email, Long id, String name,
            Instant updateDate, Date dateSendingCode, String passwordRecoveryCode, String logradouro,
            String complemento, String bairro, String localidade, String uf) {

        this.cep = cep;
        this.cpf = cpf;
        this.createDate = createDate;
        this.email = email;
        this.id = id;
        this.name = name;
        this.updateDate = updateDate;
        this.dateSendingCode = dateSendingCode;
        this.passwordRecoveryCode = passwordRecoveryCode;

        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public UserDTO(User entity) {

        this.cep = entity.getCep();
        this.cpf = entity.getCpf();
        this.createDate = entity.getCreateDate();
        this.email = entity.getEmail();
        this.id = entity.getId();
        this.name = entity.getName();
        this.updateDate = entity.getUpdateDate();
        this.dateSendingCode = entity.getDateSendingCode();
        this.passwordRecoveryCode = entity.getPasswordRecoveryCode();
        this.situation = entity.getSituation();

        this.logradouro = entity.getLogradouro();
        this.complemento = entity.getComplemento();
        this.bairro = entity.getBairro();
        this.localidade = entity.getLocalidade();
        this.uf = entity.getUf();

        entity.getPermissions()
                .forEach(personPermission -> this.permissions.add(new PermissionDTO(personPermission)));

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public Date getDateSendingCode() {
        return dateSendingCode;
    }

    public void setDateSendingCode(Date dateSendingCode) {
        this.dateSendingCode = dateSendingCode;
    }

    public String getPasswordRecoveryCode() {
        return passwordRecoveryCode;
    }

    public void setPasswordRecoveryCode(String passwordRecoveryCode) {
        this.passwordRecoveryCode = passwordRecoveryCode;
    }

    public UserSituation getSituation() {
        return situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }

}
