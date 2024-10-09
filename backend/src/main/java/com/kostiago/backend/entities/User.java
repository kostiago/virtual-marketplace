package com.kostiago.backend.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;
    private String address;
    private String cep;

    private String passwordRecoveryCode;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date dateSendingCode;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", updatable = false)
    private Instant createDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateDate;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_person_permission", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    Set<Permission> permissions = new HashSet<>();

    public User() {
    }

    public User(String address, String cep, String cpf, Instant createDate, String email, Long id, String name,
            String password, Instant updateDate, String passwordRecoveryCode, Date dateSendingCode) {
        this.address = address;
        this.cep = cep;
        this.cpf = cpf;
        this.createDate = createDate;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.updateDate = updateDate;
        this.passwordRecoveryCode = passwordRecoveryCode;
        this.dateSendingCode = dateSendingCode;

    }

    @PrePersist
    protected void onCreate() {
        createDate = Instant.now();
        updateDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = Instant.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public String getPasswordRecoveryCode() {
        return passwordRecoveryCode;
    }

    public void setPasswordRecoveryCode(String passwordRecoveryCode) {
        this.passwordRecoveryCode = passwordRecoveryCode;
    }

    public Date getDateSendingCode() {
        return dateSendingCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setDateSendingCode(Date dateSendingCode) {
        this.dateSendingCode = dateSendingCode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
