package com.kostiago.backend.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_permission")
@Data
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", updatable = false)
    private Instant createDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateDate;

    public Permission() {
    }
}