package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Venta;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.VentaRepository;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository, EmpleadoRepository empleadoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVentaById(int id) {
        return ventaRepository.findById(id);
    }

    public Venta addVenta(Venta venta) {
        Producto producto = productoRepository.findById(venta.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Empleado empleado = empleadoRepository.findById(venta.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        venta.setProducto(producto);
        venta.setEmpleado(empleado);
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta ventaDetails) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Producto producto = productoRepository.findById(ventaDetails.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Empleado empleado = empleadoRepository.findById(ventaDetails.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));


        venta.setProducto(producto);
        venta.setEmpleado(empleado);
        venta.setMetodoPago(ventaDetails.getMetodoPago());
        venta.setCantidad(ventaDetails.getCantidad());
        venta.setNombreCliente(ventaDetails.getNombreCliente());

        return ventaRepository.save(venta);
    }

    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }




}
