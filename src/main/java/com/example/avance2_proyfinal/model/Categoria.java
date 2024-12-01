package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre de la categoría es requerido") // Valida que el campo no sea nulo
    @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres") // Valida la longitud del nombre
    @Column(name = "nombre") // Define el nombre de la columna en la tabla
    private String nombre;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre de la categoría es requerido") @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre de la categoría es requerido") @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres")String nombre) {
        this.nombre = nombre;
    }
}
