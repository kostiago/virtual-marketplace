package com.kostiago.backend.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user_verifying")
public class UserVerifying {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private Instant codeExpirationDate;

    @ManyToOne
    @JoinColumn(name = "id_user", unique = true)
    private User user;

    public UserVerifying() {
    }

    public UserVerifying(Long id, String uuid, Instant codeExpirationDate, User user) {
        this.id = id;
        this.uuid = uuid;
        this.codeExpirationDate = codeExpirationDate;
        this.user = user;
    }

    public UserVerifying(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Instant getCodeExpirationDate() {
        return codeExpirationDate;
    }

    public void setCodeExpirationDate(Instant codeExpirationDate) {
        this.codeExpirationDate = codeExpirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserVerifying other = (UserVerifying) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
