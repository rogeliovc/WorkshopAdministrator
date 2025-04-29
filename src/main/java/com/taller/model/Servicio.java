package com.taller.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
}
