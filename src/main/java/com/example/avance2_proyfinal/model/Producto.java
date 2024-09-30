package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    private String nombre;
    private int cantidad;
    private double precio_dia;
    private double precio_noche;
    private String descripcion;
    private String categoria;
    private Date fecha_ingreso;
    private String proveedor;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_dia() {
        return precio_dia;
    }

    public void setPrecio_dia(double precio_dia) {
        this.precio_dia = precio_dia;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }
    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

}