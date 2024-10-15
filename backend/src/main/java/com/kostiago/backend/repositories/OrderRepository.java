package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kostiago.backend.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
