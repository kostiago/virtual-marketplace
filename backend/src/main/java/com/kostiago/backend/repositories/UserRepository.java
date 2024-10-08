package com.kostiago.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.User;
import com.kostiago.backend.projections.UserDetailProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf(String cpf);

    User findByEmail(String email);

    User findByEmailAndPasswordRecoveryCode(String email, String passwordRecoveryCode);

    @Query(nativeQuery = true, value = """
            SELECT tb_person.email AS username, tb_person.password, tb_permission.id AS permissionId, tb_permission.name AS permission
            FROM tb_person
            INNER JOIN tb_person_permission ON tb_person.id = tb_person_permission.person_id
            INNER JOIN tb_permission ON tb_permission.id = tb_person_permission.permission_id
            WHERE tb_person.email = :email
            """)
    List<UserDetailProjection> searchUserAndRolesByEmail(String email);
}
