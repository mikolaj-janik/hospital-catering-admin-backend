package com.mikolajjanik.hospital_catering_admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "zamówienie_posiłek")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "zamówienieid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "posiłekid")
    private Meal meal;

    @Column(name = "data")
    private LocalDate date;
}
