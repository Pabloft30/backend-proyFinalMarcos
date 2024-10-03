package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RegistroVenta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String producto;
    private String cliente;
    private String vendedor;
    private LocalDate fecha; // Ahora usando LocalDate
    private String metodo_pago;
    private int cantidad;
    private double total;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto =producto;
    }

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public double getTotal() {
            return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
