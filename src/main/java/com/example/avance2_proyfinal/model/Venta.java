package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name="ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @NotNull(message = "La fecha de venta es requerida")
    @PastOrPresent(message = "La fecha de venta no puede ser una fecha futura")
    private LocalDate fechaVenta; // Ahora usando LocalDate

    @NotNull(message = "El método de pago es requerido")
    private String metodoPago;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    private double total;

    @NotNull(message = "El nombre del cliente es requerido")
    @Size(min = 3, max = 100, message = "El nombre del cliente debe tener entre 3 y 100 caracteres")
    private String nombreCliente;

    // Getters y Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public @NotNull(message = "La fecha de venta es requerida")
    @PastOrPresent(message = "La fecha de venta no puede ser una fecha futura") LocalDate getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(@NotNull(message = "La fecha de venta es requerida")
                              @PastOrPresent(message = "La fecha de venta no puede ser una fecha futura") LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser al menos 1") int getCantidad() {
        return cantidad;
    }
    public void setCantidad(@NotNull(message = "La cantidad es requerida")
                            @Min(value = 1, message = "La cantidad debe ser al menos 1") int cantidad) {
        this.cantidad = cantidad;
    }

    public @NotNull(message = "El nombre del cliente es requerido")
    @Size(min = 3, max = 100, message = "El nombre del cliente debe tener entre 3 y 100 caracteres") String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(@NotNull(message = "El nombre del cliente es requerido")
                                 @Size(min = 3, max = 100, message = "El nombre del cliente debe tener entre 3 y 100 caracteres") String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Venta() {}

    public Venta(int id, Producto producto, Empleado empleado, LocalDate fechaVenta, String metodoPago, double total, String nombreCliente, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.empleado = empleado;
        this.fechaVenta = fechaVenta;
        this.metodoPago = metodoPago;
        this.total = total;
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
    }
}

