package com.taller.controller;

import com.taller.model.OrdenTrabajo;
import com.taller.repository.OrdenTrabajoRepository;
import com.taller.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenTrabajoController {
    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping
    public List<OrdenTrabajo> getAllOrdenes() {
        return ordenTrabajoRepository.findAll();
    }

    @PostMapping
    public OrdenTrabajo createOrden(@RequestBody OrdenTrabajo ordenTrabajo) {
        // Lógica de negocio antes de guardar
        ordenTrabajo.setCostoTotal(ordenTrabajoService.calcularCostoTotal(ordenTrabajo));
        if (!ordenTrabajoService.validarOrden(ordenTrabajo)) {
            throw new IllegalArgumentException("La orden debe tener al menos un servicio.");
        }
        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    @GetMapping("/{id}")
    public OrdenTrabajo getOrdenById(@PathVariable Long id) {
        return ordenTrabajoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public OrdenTrabajo updateOrden(@PathVariable Long id, @RequestBody OrdenTrabajo ordenDetails) {
        return ordenTrabajoRepository.findById(id).map(orden -> {
            orden.setFechaIngreso(ordenDetails.getFechaIngreso());
            orden.setFechaEntregaEstimada(ordenDetails.getFechaEntregaEstimada());
            orden.setFechaEntregaReal(ordenDetails.getFechaEntregaReal());
            orden.setEstado(ordenDetails.getEstado());
            orden.setVehiculo(ordenDetails.getVehiculo());
            orden.setDetalles(ordenDetails.getDetalles());
            // Calcula el costo total usando la lógica de negocio
            orden.setCostoTotal(ordenTrabajoService.calcularCostoTotal(orden));
            orden.setNotas(ordenDetails.getNotas());
            if (!ordenTrabajoService.validarOrden(orden)) {
                throw new IllegalArgumentException("La orden debe tener al menos un servicio.");
            }
            return ordenTrabajoRepository.save(orden);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
