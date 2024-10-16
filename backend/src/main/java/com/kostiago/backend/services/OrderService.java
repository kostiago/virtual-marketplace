package com.kostiago.backend.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostiago.backend.dto.OrderDTO;
import com.kostiago.backend.dto.OrderItemDTO;
import com.kostiago.backend.entities.Order;
import com.kostiago.backend.entities.OrderItem;
import com.kostiago.backend.entities.Product;
import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.enums.OrderStatus;
import com.kostiago.backend.repositories.OrderItemRepository;
import com.kostiago.backend.repositories.OrderRepository;
import com.kostiago.backend.repositories.ProductRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserDetailService service;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("Pedido não encontrado!"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order entity = new Order();

        entity.setMoment(Instant.now());
        entity.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = service.authenticated();
        entity.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(entity, product, itemDTO.getQuantity(), product.getPrice());
            entity.getItems().add(item);
        }

        repository.saveAndFlush(entity);
        orderItemRepository.saveAll(entity.getItems());

        return new OrderDTO(entity);
    }

}
