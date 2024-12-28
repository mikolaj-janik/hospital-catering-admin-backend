package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.dto.OrderDTO;
import com.mikolajjanik.hospital_catering_admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    Page<OrderDTO> getOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }
}
