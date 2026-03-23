package com.example.tutorial.domain.user.read;

import com.example.tutorial.domain.user.vo.Activo;

import java.time.Instant;

public final class UserSummary {
    private final String id;
    private final String username;
    private final String nombre;
    private final String apellidos;
    private final String email;
    private final Activo activo;
    private final String telefono;
    private final Instant creationDate;
    private final Instant updateDate;

    public UserSummary(String id, String username, String nombre, String apellidos, String email, Activo activo, String telefono, Instant creationDate, Instant updateDate) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.activo = activo;
        this.telefono = telefono;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public Activo getActivo() { return activo; }
    public String getTelefono() { return telefono; }
    public Instant getCreationDate() { return creationDate; }
    public Instant getUpdateDate() { return updateDate; }
}
