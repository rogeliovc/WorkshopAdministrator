package com.taller.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String marca;
    private String modelo;
    private String color;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
