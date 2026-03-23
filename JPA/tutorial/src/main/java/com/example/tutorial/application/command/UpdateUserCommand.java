package com.example.tutorial.application.command;

import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.vo.UserId;

public final class UpdateUserCommand {
    private final UserId id;
    private final String nombre;
    private final String apellidos;
    private final String password;
    private final String email;
    private final Activo activo;
    private final String telefono;

    public UpdateUserCommand(UserId id, String nombre, String apellidos, String password, String email, Activo activo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.activo = activo;
        this.telefono = telefono;
    }

    public UserId getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Activo getActivo() { return activo; }
    public String getTelefono() { return telefono; }
}
