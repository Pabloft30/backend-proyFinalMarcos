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
        // Validar existencia del producto
        Producto producto = productoRepository.findById(venta.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar existencia del empleado
        Empleado empleado = empleadoRepository.findById(venta.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Validar stock suficiente
        if (producto.getCantidad() < venta.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para realizar la venta");
        }

        // Descontar el stock
        producto.setCantidad(producto.getCantidad() - venta.getCantidad());
        productoRepository.save(producto); // Actualizar producto en la base de datos

        // Configurar las relaciones en la venta
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

        // Si la cantidad del producto cambia, se ajusta el stock
        int diferenciaCantidad = ventaDetails.getCantidad() - venta.getCantidad();
        if (producto.getCantidad() < diferenciaCantidad) {
            throw new RuntimeException("Stock insuficiente para realizar la venta");
        }

        // Descontar o sumar al stock según la diferencia
        producto.setCantidad(producto.getCantidad() - diferenciaCantidad);
        productoRepository.save(producto);  // Actualizar el producto con el nuevo stock

        // Configurar los valores de la venta
        venta.setProducto(producto);
        venta.setEmpleado(empleado);
        venta.setMetodoPago(ventaDetails.getMetodoPago());
        venta.setCantidad(ventaDetails.getCantidad());
        venta.setNombreCliente(ventaDetails.getNombreCliente());
        venta.setTotal(ventaDetails.getTotal());

        return ventaRepository.save(venta);
    }

    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Restaurar el stock del producto
        Producto producto = venta.getProducto();
        producto.setCantidad(producto.getCantidad() + venta.getCantidad());
        productoRepository.save(producto); // Actualizar el stock en la base de datos

        ventaRepository.delete(venta); // Eliminar la venta
    }
}




