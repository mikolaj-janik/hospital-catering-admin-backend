package com.mikolajjanik.hospital_catering_admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jasłospis")
@Getter
@Setter
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dietaid")
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "posiłekid")
    private Meal meal;

    @Column(name = "data")
    private Date date;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "zamówienie_jadłospis",
            joinColumns = { @JoinColumn(name = "jadłospisid") },
            inverseJoinColumns = { @JoinColumn(name = "zamówienieid") }
    )
    private Set<Order> orders = new HashSet<>();
}
