package com.mikolajjanik.hospital_catering_admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dieta")
@Getter
@Setter
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nazwa")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diet")
    @JsonIgnore
    private Set<Meal> meals = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diet")
    @JsonIgnore
    private Set<Patient> patients = new HashSet<>();

}
