package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kostiago.backend.entities.UserVerifying;

public interface UserVerifyindRepository extends JpaRepository<UserVerifying, Long> {

    UserVerifying findByUuid(String uuid);
}
