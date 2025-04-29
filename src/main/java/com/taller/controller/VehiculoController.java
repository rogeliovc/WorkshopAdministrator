package com.taller.controller;

import com.taller.model.Vehiculo;
import com.taller.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    @PostMapping
    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @GetMapping("/{id}")
    public Vehiculo getVehiculoById(@PathVariable Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Vehiculo updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculoDetails) {
        return vehiculoRepository.findById(id).map(vehiculo -> {
            vehiculo.setPlaca(vehiculoDetails.getPlaca());
            vehiculo.setMarca(vehiculoDetails.getMarca());
            vehiculo.setModelo(vehiculoDetails.getModelo());
            vehiculo.setColor(vehiculoDetails.getColor());
            vehiculo.setCliente(vehiculoDetails.getCliente());
            return vehiculoRepository.save(vehiculo);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteVehiculo(@PathVariable Long id) {
        vehiculoRepository.deleteById(id);
    }
}
