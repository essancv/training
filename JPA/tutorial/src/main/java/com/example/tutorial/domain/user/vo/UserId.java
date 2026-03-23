package com.example.tutorial.domain.user.vo;

import java.util.Objects;

public final class UserId {
    private final Long value;

    private UserId(Long value) {
        this.value = value; // permitir null para creación
    }

    public static UserId of(Long value) {
        if (value == null) throw new IllegalArgumentException("UserId no puede crearse desde null");
        return new UserId(value);
    }

    public static UserId of(String value) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("UserId no puede ser null o vacío");
        try {
            return new UserId(Long.valueOf(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("UserId debe ser numérico", e);
        }
    }

    public static UserId create() {
        return new UserId(null); // para creación, ID generado en BD
    }

    public Long getValue() {
        if (value == null) throw new IllegalStateException("UserId no ha sido generado aún");
        return value;
    }

    public boolean isGenerated() {
        return value != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return "UserId{value=" + value + "}"; }
}
