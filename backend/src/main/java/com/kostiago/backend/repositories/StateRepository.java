package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
