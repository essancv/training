package com.example.tutorial.application.command;

public final class CreateUserCommand {
    private final String username;
    private final String nombre;
    private final String apellidos;
    private final String password;
    private final String email;
    private final String telefono;

    public CreateUserCommand(String username, String nombre, String apellidos, String password, String email, String telefono) {
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
    }

    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}
