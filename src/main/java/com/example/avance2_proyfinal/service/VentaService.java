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

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Venta getVentaById(int ventaId) {
        return ventaRepository.findById(ventaId).orElse(null);
    }

    public Venta createVenta(Venta venta) {
        Producto prod = productoRepository.findById(venta.getProducto().getId()).orElse(null);
        Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId()).orElse(null);

        if (prod != null && emp != null) {
            venta.setProducto(prod);
            venta.setEmpleado(emp);
            return ventaRepository.save(venta);
        }
        return null; // O manejar el error como prefieras
    }

    public Venta updateVenta(int ventaId, Venta venta) {
        Venta existingVenta = ventaRepository.findById(ventaId).orElse(null);

        if (existingVenta != null) {
            Producto prod = productoRepository.findById(venta.getProducto().getId()).orElse(null);
            Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId()).orElse(null);

            if (prod != null && emp != null) {
                existingVenta.setProducto(prod);
                existingVenta.setEmpleado(emp);
                existingVenta.setFechaVenta(venta.getFechaVenta());
                existingVenta.setMetodoPago(venta.getMetodoPago());
                return ventaRepository.save(existingVenta);
            }
        }
        return null; // O manejar el error como prefieras
    }

    public boolean deleteVenta(int ventaId) {
        Venta venta = ventaRepository.findById(ventaId).orElse(null);
        if (venta != null) {
            ventaRepository.delete(venta);
            return true; // Indica que se eliminó correctamente
        }
        return false; // O manejar el error como prefieras
    }
}
