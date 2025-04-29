package com.taller.service;

import com.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Service;

@Service
public class OrdenTrabajoService {
    // Calcula el costo total de la orden sumando los subtotales de los detalles
    public double calcularCostoTotal(OrdenTrabajo orden) {
        if (orden.getDetalles() == null) return 0.0;
        return orden.getDetalles().stream()
                .mapToDouble(det -> det.getSubtotal() != null ? det.getSubtotal() : 0.0)
                .sum();
    }

    // Valida que la orden tenga al menos un detalle
    public boolean validarOrden(OrdenTrabajo orden) {
        return orden.getDetalles() != null && !orden.getDetalles().isEmpty();
    }
}
