package com.mikolajjanik.hospital_catering_admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacjent")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dietaid")
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "oddziałid")
    private Ward ward;

}
