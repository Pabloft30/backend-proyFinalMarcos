package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre del producto es requerido")//Valida que el campo no sea nulo
    @Size(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")//Valida que el campo tenga una longitud entre 3 y 50 caracteres
    @Column(name = "name")//Define el nombre de la columna en la tabla
    private String nombre;

    @NotNull(message = "La cantidad es requerida")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @NotNull(message = "El precio de día es requerido")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio de día no puede ser negativo")
    private double precio_dia;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio de noche no puede ser negativo")
    private double precio_noche;

    @NotNull(message = "La descripción es requerida")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;

    @NotNull(message = "La categoría es requerida")
    @Size(min = 3, max = 50, message = "La categoría debe tener entre 3 y 50 caracteres")
    private String categoria;

    @NotNull(message = "La fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de ingreso no puede ser una fecha futura")
    private LocalDate fecha_ingreso;

    @NotNull(message = "El proveedor es requerido")
    @Size(min = 3, max = 50, message = "El nombre del proveedor debe tener entre 3 y 50 caracteres")
    private String proveedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre del producto es requerido") @Size(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre del producto es requerido") @Size(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "La cantidad es requerida") @Min(value = 0, message = "La cantidad no puede ser negativa") int getCantidad() {
        return cantidad;
    }

    public void setCantidad(@NotNull(message = "La cantidad es requerida") @Min(value = 0, message = "La cantidad no puede ser negativa") int cantidad) {
        this.cantidad = cantidad;
    }

    public @NotNull(message = "El precio de día es requerido") @DecimalMin(value = "0.0", inclusive = true, message = "El precio de día no puede ser negativo") double getPrecio_dia() {
        return precio_dia;
    }

    public void setPrecio_dia(@NotNull(message = "El precio de día es requerido") @DecimalMin(value = "0.0", inclusive = true, message = "El precio de día no puede ser negativo") double precio_dia) {
        this.precio_dia = precio_dia;
    }

    public @DecimalMin(value = "0.0", inclusive = true, message = "El precio de noche no puede ser negativo") double getPrecio_noche() {
        return precio_noche;
    }
    public void setPrecio_noche(@DecimalMin(value = "0.0", inclusive = true, message = "El precio de noche no puede ser negativo") double precio_noche) {
        this.precio_noche = precio_noche;
    }

    public @NotNull(message = "La descripción es requerida") @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres") String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(@NotNull(message = "La descripción es requerida") @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull(message = "La categoría es requerida") @Size(min = 3, max = 50, message = "La categoría debe tener entre 3 y 50 caracteres") String getCategoria() {
        return categoria;
    }
    public void setCategoria(@NotNull(message = "La categoría es requerida") @Size(min = 3, max = 50, message = "La categoría debe tener entre 3 y 50 caracteres") String categoria) {
        this.categoria = categoria;
    }

    public @NotNull(message = "La fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de ingreso no puede ser una fecha futura") LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }
    public void setFecha_ingreso(@NotNull(message = "La fecha de ingreso es requerida") @PastOrPresent(message = "La fecha de ingreso no puede ser una fecha futura") LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public @NotNull(message = "El proveedor es requerido")
    @Size(min = 3, max = 50, message = "El nombre del proveedor debe tener entre 3 y 50 caracteres") String getProveedor() {
        return proveedor;
    }
    public void setProveedor(@NotNull(message = "El proveedor es requerido") @Size(min = 3, max = 50, message = "El nombre del proveedor debe tener entre 3 y 50 caracteres") String proveedor) {
        this.proveedor = proveedor;
    }

}
