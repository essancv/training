package com.example.tutorial.infrastructure.inmemory.persistence;

import com.example.tutorial.domain.user.User;
import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.vo.UserId;
import com.example.tutorial.domain.user.port.UserReadRepository;
import com.example.tutorial.domain.user.port.UserWriteRepository;
import com.example.tutorial.domain.user.read.UserSummary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("inmemory")
public class InMemoryUserRepository implements UserWriteRepository, UserReadRepository {

    private final Map<UserId, User> byId = new HashMap<>();

    @Override
    public User save(User user) {
        if (byId.containsKey(user.getId())) {
            throw new IllegalStateException("Usuario ya existe con id " + user.getId());
        }
        byId.put(user.getId(), user);
        return user;
    }

    @Override
    public void update(User user) {
        if (!byId.containsKey(user.getId())) {
            throw new IllegalStateException("Usuario no existe para actualizar " + user.getId());
        }
        byId.put(user.getId(), user);
    }

    @Override
    public void delete(UserId id) {
        byId.remove(id);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(byId.get(id));
    }

    @Override
    public boolean existsByUsername(String username) {
        return byId.values().stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return byId.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public Optional<UserSummary> findById(String id) {
        return byId.values().stream()
                .filter(u -> u.getId().getValue().toString().equals(id))
                .map(InMemoryUserRepository::toSummary)
                .findAny();
    }

    @Override
    public List<UserSummary> findByActivo(Activo activo) {
        return byId.values().stream()
                .filter(u -> u.getActivo() == activo)
                .map(InMemoryUserRepository::toSummary)
                .toList();
    }

    @Override
    public Optional<UserSummary> findByUsername(String username) {
        return byId.values().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .map(InMemoryUserRepository::toSummary)
                .findAny();
    }

    @Override
    public List<UserSummary> findAll() {
        var out = new ArrayList<UserSummary>();
        byId.values().forEach(u -> out.add(toSummary(u)));
        return out;
    }

    private static UserSummary toSummary(User u) {
        return new UserSummary(
                u.getId().getValue() != null ? u.getId().getValue().toString() : null,
                u.getUsername(),
                u.getNombre(),
                u.getApellidos(),
                u.getEmail(),
                u.getActivo(),
                u.getTelefono(),
                u.getCreationDate(),
                u.getUpdateDate()
        );
    }
}
