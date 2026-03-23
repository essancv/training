package com.example.tutorial.infrastructure.jpa.event;

import com.example.tutorial.domain.user.vo.Activo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

@Entity
@Table(name = "user_events")
public class UserEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private Instant happenedAt;

    @Column(nullable = false)
    private Long userId;

    @Column
    private String username;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Activo activo;

    @Column
    private String telefono;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Instant creationDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateDate;

    public UserEventEntity() {
    }

    public UserEventEntity(String eventType, Instant happenedAt, Long userId, String username, String nombre, String apellidos, String email, Activo activo, String telefono, Instant creationDate, Instant updateDate) {
        this.eventType = eventType;
        this.happenedAt = happenedAt;
        this.userId = userId;
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.activo = activo;
        this.telefono = telefono;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public Instant getHappenedAt() {
        return happenedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Activo getActivo() {
        return activo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }
}