package com.mikolajjanik.hospital_catering_admin.dto;

import com.mikolajjanik.hospital_catering_admin.entity.OrderItem;
import com.mikolajjanik.hospital_catering_admin.entity.Patient;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Patient patient;
    private double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;

    public OrderDTO(Long id, Patient patient, double totalPrice, LocalDateTime orderDate, List<OrderItem> orderItems) {
        this.id = id;
        this.patient = patient;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }
}
