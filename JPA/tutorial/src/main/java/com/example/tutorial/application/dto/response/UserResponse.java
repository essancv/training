package com.example.tutorial.application.dto.response;

import com.example.tutorial.domain.user.vo.Activo;

public class UserResponse {
    private String id;
    private String username;
    private String nombre;
    private String apellidos;
    private String email;
    private Activo activo;
    private String telefono;

    public UserResponse() {}

    public UserResponse(String id, String username, String nombre, String apellidos, String email, Activo activo, String telefono) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.activo = activo;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Activo getActivo() { return activo; }
    public void setActivo(Activo activo) { this.activo = activo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
