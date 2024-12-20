package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    @Column(unique = true, nullable = false)
    private String nombre;

    @NotNull(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$",
            message = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial (@#$%^&+=)")
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotNull(message = "El nombre de usuario es requerido")
                          @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$",
            message = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial (@#$%^&+=)") String getPassword() {
        return password;
    }
    public void setPassword(@NotNull(message = "La contraseña es requerida")
                            @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
                            @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$",
                                    message = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial (@#$%^&+=)") String password) {
        this.password = password;
    }

}
