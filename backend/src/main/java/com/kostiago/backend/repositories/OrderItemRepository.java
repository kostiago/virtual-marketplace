package com.kostiago.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kostiago.backend.entities.OrderItem;
import com.kostiago.backend.entities.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {

}
