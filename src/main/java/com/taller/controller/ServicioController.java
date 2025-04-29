package com.taller.controller;

import com.taller.model.Servicio;
import com.taller.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    @PostMapping
    public Servicio createServicio(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Servicio updateServicio(@PathVariable Long id, @RequestBody Servicio servicioDetails) {
        return servicioRepository.findById(id).map(servicio -> {
            servicio.setNombre(servicioDetails.getNombre());
            servicio.setDescripcion(servicioDetails.getDescripcion());
            servicio.setPrecio(servicioDetails.getPrecio());
            return servicioRepository.save(servicio);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteServicio(@PathVariable Long id) {
        servicioRepository.deleteById(id);
    }
}
