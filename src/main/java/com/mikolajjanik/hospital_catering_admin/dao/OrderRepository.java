package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> getOrdersByOrderByOrderDateDesc(Pageable pageable);
}
