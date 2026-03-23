package com.example.tutorial.infrastructure.jpa.persistence;

import com.example.tutorial.domain.user.User;
import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.vo.UserId;
import com.example.tutorial.domain.user.read.UserSummary;
import com.example.tutorial.domain.user.port.UserReadRepository;
import com.example.tutorial.domain.user.port.UserWriteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("jpa")
public class JpaUserRepositoryAdapter implements UserWriteRepository, UserReadRepository {

    private final JpaUserEntityRepository jpaRepository;

    public JpaUserRepositoryAdapter(JpaUserEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public void update(User user) {
        if (!jpaRepository.existsById(user.getId().getValue())) {
            throw new IllegalStateException("Usuario no existe: " + user.getId());
        }
        UserEntity entity = toEntity(user);
        jpaRepository.save(entity);
    }

    @Override
    public void delete(UserId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepository.findById(id.getValue()).map(JpaUserRepositoryAdapter::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserSummary> findById(String id) {
        if (id == null || id.isBlank()) return Optional.empty();
        try {
            Long userId = Long.valueOf(id);
            return jpaRepository.findById(userId).map(JpaUserRepositoryAdapter::toSummary);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserSummary> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(JpaUserRepositoryAdapter::toSummary);
    }

    @Override
    public List<UserSummary> findAll() {
        return jpaRepository.findAll().stream().map(JpaUserRepositoryAdapter::toSummary).collect(Collectors.toList());
    }

    @Override
    public List<UserSummary> findByActivo(Activo activo) {
        return jpaRepository.findByActivo(activo).stream().map(JpaUserRepositoryAdapter::toSummary).collect(Collectors.toList());
    }

    private static UserEntity toEntity(User user) {
        Long id = (user.getId() != null && user.getId().isGenerated()) ? user.getId().getValue() : null;
        return new UserEntity(
                id,
                user.getUsername(),
                user.getNombre(),
                user.getApellidos(),
                user.getPassword(),
                user.getEmail(),
                user.getActivo(),
                user.getTelefono(),
                user.getCreationDate(),
                user.getUpdateDate()
        );
    }

    private static User toDomain(UserEntity entity) {
        return new User(
                UserId.of(entity.getId()),
                entity.getUsername(),
                entity.getNombre(),
                entity.getApellidos(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getActivo(),
                entity.getTelefono(),
                entity.getCreationDate(),
                entity.getUpdateDate()
        );
    }

    private static UserSummary toSummary(UserEntity entity) {
        return new UserSummary(
                entity.getId() != null ? entity.getId().toString() : null,
                entity.getUsername(),
                entity.getNombre(),
                entity.getApellidos(),
                entity.getEmail(),
                entity.getActivo(),
                entity.getTelefono(),
                entity.getCreationDate(),
                entity.getUpdateDate()
        );
    }
}
