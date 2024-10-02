package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")// Referencia a id del Producto
    private Producto producto;

    @ManyToOne
    @JoinColumn(name="empleado_id")
    private Empleado empleado;

   // Getters y Setters
    private Date fechaVenta;
    private String metodoPago;

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

    public Date getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}

