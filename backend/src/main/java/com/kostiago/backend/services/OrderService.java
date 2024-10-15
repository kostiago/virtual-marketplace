package com.kostiago.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.OrderDTO;
import com.kostiago.backend.entities.Order;
import com.kostiago.backend.repositories.OrderRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("Pedido não encontrado!"));
        return new OrderDTO(order);
    }
}
