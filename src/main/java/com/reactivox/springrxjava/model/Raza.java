package com.reactivox.springrxjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_raza")
@Data
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String descripción;
}
