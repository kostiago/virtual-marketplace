package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf(String cpf);

    User findByEmail(String email);

    User findByEmailAndPasswordRecoveryCode(String email, String passwordRecoveryCode);
}
