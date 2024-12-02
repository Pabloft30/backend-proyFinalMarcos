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

        // Verificar si hay suficiente stock
        if (producto.getCantidad() < venta.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el producto " + producto.getNombre());
        }

        // Restar la cantidad vendida del stock
        producto.setCantidad(producto.getCantidad() - venta.getCantidad());

        // Guardar el producto con el stock actualizado
        productoRepository.save(producto);

        // Establecer el producto y empleado en la venta
        venta.setProducto(producto);
        venta.setEmpleado(empleado);

        // Guardar la venta
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta ventaDetails) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Producto producto = productoRepository.findById(ventaDetails.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Empleado empleado = empleadoRepository.findById(ventaDetails.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Restaurar el stock del producto anterior (de la venta actual)
        Producto productoAnterior = venta.getProducto();
        productoAnterior.setCantidad(productoAnterior.getCantidad() + venta.getCantidad()); // Restaurar el stock

        // Verificar si hay suficiente stock para la nueva venta
        if (producto.getCantidad() < ventaDetails.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el producto " + producto.getNombre());
        }

        // Actualizar el stock con la nueva cantidad vendida
        producto.setCantidad(producto.getCantidad() - ventaDetails.getCantidad());

        // Guardar el producto con el stock actualizado
        productoRepository.save(producto);

        // Establecer los nuevos valores en la venta
        venta.setProducto(producto);
        venta.setEmpleado(empleado);
        venta.setMetodoPago(ventaDetails.getMetodoPago());
        venta.setCantidad(ventaDetails.getCantidad());
        venta.setNombreCliente(ventaDetails.getNombreCliente());
        venta.setTotal(ventaDetails.getTotal());

        // Guardar la venta actualizada
        return ventaRepository.save(venta);
    }

    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }




}
