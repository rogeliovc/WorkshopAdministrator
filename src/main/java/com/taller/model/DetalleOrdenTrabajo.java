package com.taller.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleOrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
