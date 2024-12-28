package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDTO> getAllOrders(Pageable pageable);
}
