package com.taller.config;

import com.taller.model.*;
import com.taller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;
    @Autowired
    private DetalleOrdenTrabajoRepository detalleOrdenTrabajoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clientes
        Cliente cli1 = new Cliente();
        cli1.setNombre("Juan Pérez");
        cli1.setTelefono("555-1234");
        cli1.setEmail("juan@mail.com");
        clienteRepository.save(cli1);

        Cliente cli2 = new Cliente();
        cli2.setNombre("Ana Gómez");
        cli2.setTelefono("555-5678");
        cli2.setEmail("ana@mail.com");
        clienteRepository.save(cli2);

        // Vehículos
        Vehiculo v1 = new Vehiculo();
        v1.setPlaca("ABC123");
        v1.setModelo("Corolla");
        v1.setMarca("Toyota");
        v1.setColor("Blanco");
        v1.setCliente(cli1);
        vehiculoRepository.save(v1);

        Vehiculo v2 = new Vehiculo();
        v2.setPlaca("XYZ789");
        v2.setModelo("Civic");
        v2.setMarca("Honda");
        v2.setColor("Negro");
        v2.setCliente(cli2);
        vehiculoRepository.save(v2);

        // Servicios
        Servicio s1 = new Servicio();
        s1.setNombre("Cambio de Aceite");
        s1.setPrecio(500.0);
        s1.setDescripcion("Cambio de aceite de motor");
        servicioRepository.save(s1);

        Servicio s2 = new Servicio();
        s2.setNombre("Alineación");
        s2.setPrecio(300.0);
        s2.setDescripcion("Alineación y balanceo");
        servicioRepository.save(s2);

        // OrdenTrabajo 1
        OrdenTrabajo ot1 = new OrdenTrabajo();
        ot1.setFechaIngreso(LocalDate.now().minusDays(3));
        ot1.setFechaEntregaEstimada(LocalDate.now().plusDays(2));
        ot1.setEstado(EstadoOrden.EN_PROCESO);
        ot1.setVehiculo(v1);
        ot1.setNotas("Revisar frenos también");
        ordenTrabajoRepository.save(ot1);

        DetalleOrdenTrabajo dot1 = new DetalleOrdenTrabajo();
        dot1.setOrdenTrabajo(ot1);
        dot1.setServicio(s1);
        dot1.setCantidad(1);
        dot1.setPrecioUnitario(500.0);
        dot1.setSubtotal(500.0);
        detalleOrdenTrabajoRepository.save(dot1);

        DetalleOrdenTrabajo dot2 = new DetalleOrdenTrabajo();
        dot2.setOrdenTrabajo(ot1);
        dot2.setServicio(s2);
        dot2.setCantidad(1);
        dot2.setPrecioUnitario(300.0);
        dot2.setSubtotal(300.0);
        detalleOrdenTrabajoRepository.save(dot2);

        ot1.setDetalles(Arrays.asList(dot1, dot2));
        ot1.setCostoTotal(800.0);
        ordenTrabajoRepository.save(ot1);

        // OrdenTrabajo 2
        OrdenTrabajo ot2 = new OrdenTrabajo();
        ot2.setFechaIngreso(LocalDate.now().minusDays(1));
        ot2.setFechaEntregaEstimada(LocalDate.now().plusDays(3));
        ot2.setEstado(EstadoOrden.PENDIENTE);
        ot2.setVehiculo(v2);
        ot2.setNotas("Cliente solicita revisión general");
        ordenTrabajoRepository.save(ot2);

        DetalleOrdenTrabajo dot3 = new DetalleOrdenTrabajo();
        dot3.setOrdenTrabajo(ot2);
        dot3.setServicio(s2);
        dot3.setCantidad(2);
        dot3.setPrecioUnitario(300.0);
        dot3.setSubtotal(600.0);
        detalleOrdenTrabajoRepository.save(dot3);

        ot2.setDetalles(Arrays.asList(dot3));
        ot2.setCostoTotal(600.0);
        ordenTrabajoRepository.save(ot2);
    }
}
