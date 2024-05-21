package com.reactivox.springrxjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_perro")
@Data
public class Perro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombrePerro;
    @Column(nullable = false)
    private Integer edad;
    @ManyToOne
    @JoinColumn(name = "fk_raza", nullable = false)
    private Raza raza;
}
