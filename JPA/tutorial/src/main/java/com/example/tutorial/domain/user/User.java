package com.example.tutorial.domain.user;

import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.vo.UserId;

import java.time.Instant;
import java.util.Objects;

public final class User {
    private final UserId id;
    private final String username;
    private final String nombre;
    private final String apellidos;
    private final String password;
    private final String email;
    private final Activo activo;
    private final String telefono;
    private final Instant creationDate;
    private final Instant updateDate;

    private static final java.util.regex.Pattern EMAIL_PATTERN = java.util.regex.Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", java.util.regex.Pattern.CASE_INSENSITIVE);

    public User(UserId id, String username, String nombre, String apellidos, String password, String email, Activo activo, String telefono) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("username es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre es obligatorio");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("apellidos son obligatorios");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("password es obligatorio");
        if (password.length() < 6) throw new IllegalArgumentException("password debe tener al menos 6 caracteres");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email es obligatorio");
        if (!EMAIL_PATTERN.matcher(email).matches()) throw new IllegalArgumentException("email no tiene formato válido");

        this.id = id; // Permitir null para generación automática
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.activo = Objects.requireNonNull(activo, "activo no puede ser null");
        this.telefono = telefono;
        this.creationDate = Instant.now();
        this.updateDate = Instant.now();
    }

    // Constructor privado para reconstrucción (ej. desde DB o eventos)
    public User(UserId id, String username, String nombre, String apellidos, String password, String email, Activo activo, String telefono, Instant creationDate, Instant updateDate) {
        this.id = Objects.requireNonNull(id, "id no puede ser null");
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.activo = Objects.requireNonNull(activo, "activo no puede ser null");
        this.telefono = telefono;
        this.creationDate = Objects.requireNonNull(creationDate, "creationDate no puede ser null");
        this.updateDate = Objects.requireNonNull(updateDate, "updateDate no puede ser null");
    }

    // Constructor para creación sin especificar activo, por defecto TRUE
    public User(UserId id, String username, String nombre, String apellidos, String password, String email, String telefono) {
        this(id, username, nombre, apellidos, password, email, Activo.TRUE, telefono);
    }

    public UserId getId() { return id; }
    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Activo getActivo() { return activo; }
    public String getTelefono() { return telefono; }
    public Instant getCreationDate() { return creationDate; }
    public Instant getUpdateDate() { return updateDate; }

    public User change(String nombre, String apellidos, String password, String email, Activo activo, String telefono) {
        return new User(id, username, nombre, apellidos, password, email, activo, telefono, creationDate, Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
