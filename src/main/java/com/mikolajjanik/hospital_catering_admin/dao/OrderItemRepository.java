package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
}
