package com.example.tutorial.infrastructure.jpa.persistence;

import com.example.tutorial.domain.user.vo.Activo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Index;

import java.time.Instant;

@Entity
@Table(name = "users",indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_email", columnList = "email")
})
public class UserEntity {
    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Activo activo;

    @Column
    private String telefono;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant creationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateDate;

    public UserEntity() { }

    public UserEntity(Long id, String username, String nombre, String apellidos, String password, String email, Activo activo, String telefono, Instant creationDate, Instant updateDate) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.activo = activo;
        this.telefono = telefono;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Activo getActivo() { return activo; }
    public String getTelefono() { return telefono; }
    public Instant getCreationDate() { return creationDate; }
    public Instant getUpdateDate() { return updateDate; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setActivo(Activo activo) { this.activo = activo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCreationDate(Instant creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(Instant updateDate) { this.updateDate = updateDate; }
}
