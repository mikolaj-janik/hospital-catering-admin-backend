package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.OrderItemRepository;
import com.mikolajjanik.hospital_catering_admin.dao.OrderRepository;
import com.mikolajjanik.hospital_catering_admin.dto.OrderDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Order;
import com.mikolajjanik.hospital_catering_admin.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.getOrdersByOrderByOrderDateDesc(pageable);

        List<OrderDTO> ordersList = new ArrayList<>();

        for (Order order : orders.getContent()) {
            List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(order.getId());
            OrderDTO finalOrder = new OrderDTO(
                    order.getId(),
                    order.getPatient(),
                    order.getTotalPrice(),
                    order.getOrderDate(),
                    orderItems
            );
            ordersList.add(finalOrder);
        }
        return new PageImpl<>(ordersList, pageable, orders.getTotalElements());
    }
}
