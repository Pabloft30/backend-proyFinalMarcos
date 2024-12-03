package com.example.avance2_proyfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre del empleado es requerido")
    @Size(min = 3, max = 50, message = "El nombre del empleado debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull(message = "El apellido del empleado es requerido")
    @Size(min = 3, max = 50, message = "El apellido del empleado debe tener entre 3 y 50 caracteres")
    private String apellido;

    private String turno;

    @NotNull(message = "El teléfono es requerido")
    @Min(value = 900000000, message = "El teléfono debe comenzar con 9 y tener 9 dígitos")
    @Max(value = 999999999, message = "El teléfono debe comenzar con 9 y tener 9 dígitos")
    private int telefono;

    @NotNull(message = "El correo del empleado es requerido")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo del empleado no es válido")
    private String correo;

    @NotNull(message = "La edad es requerida")
    @Min(value = 18, message = "La edad mínima debe ser 18 años")
    @Max(value = 70, message = "La edad máxima debe ser 70 años")
    private int edad;

    private String genero;

    @NotNull(message = "La dirección es requerida")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String direccion;

    @NotNull(message = "El nombre de usuario es requerido")
    @Column(unique = true)
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    private String username;

    @NotNull(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "empleados_roles",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Rol> roles;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre del empleado es requerido") @Size(min = 3, max = 50, message = "El nombre del empleado debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotNull(message = "El nombre del empleado es requerido") @Size(min = 3, max = 50, message = "El nombre del empleado debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public  @NotNull(message = "El apellido del empleado es requerido") @Size(min = 3, max = 50, message = "El apellido del empleado debe tener entre 3 y 50 caracteres") String getApellido() {
        return apellido;
    }
    public void setApellido(@NotNull(message = "El apellido del empleado es requerido") @Size(min = 3, max = 50, message = "El apellido del empleado debe tener entre 3 y 50 caracteres") String apellido) {
        this.apellido = apellido;
    }

    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }

    public @NotNull(message = "El teléfono es requerido")
    @Min(value = 900000000, message = "El teléfono debe comenzar con 9 y tener 9 dígitos")
    @Max(value = 999999999, message = "El teléfono debe comenzar con 9 y tener 9 dígitos") int getTelefono() {
        return telefono;
    }
    public void setTelefono(@NotNull(message = "El teléfono es requerido")
                            @Min(value = 900000000, message = "El teléfono debe comenzar con 9 y tener 9 dígitos")
                            @Max(value = 999999999, message = "El teléfono debe comenzar con 9 y tener 9 dígitos") int telefono) {
        // Validación adicional para verificar que comience con 9
        if (String.valueOf(telefono).charAt(0) != '9') {
            throw new IllegalArgumentException("El teléfono debe comenzar con 9");
        }
        this.telefono = telefono;
    }

    public @NotNull(message = "El correo del empleado es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo del empleado no es válido") String getCorreo() {
        return correo;
    }
    public void setCorreo( @NotNull(message = "El correo del empleado es requerido")
                           @Pattern(regexp = "^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo del empleado no es válido") String correo) {
        this.correo = correo;
    }

    public @NotNull(message = "La edad es requerida") // Valida que el campo no sea nulo
    @Min(value = 18, message = "La edad mínima debe ser 18 años") int getEdad() {
        return edad;
    }
    public void setEdad(@NotNull(message = "La edad es requerida") // Valida que el campo no sea nulo
                        @Min(value = 18, message = "La edad mínima debe ser 18 años") int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public @NotNull(message = "La dirección es requerida")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres") String getDireccion() {
        return direccion;
    }
    public void setDireccion(@NotNull(message = "La dirección es requerida")
                             @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres") String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
