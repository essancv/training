package com.example.tutorial.application;

import com.example.tutorial.application.command.CreateUserCommand;
import com.example.tutorial.application.command.DeleteUserCommand;
import com.example.tutorial.application.command.UpdateUserCommand;
import com.example.tutorial.domain.user.User;
import com.example.tutorial.domain.user.vo.UserId;
import com.example.tutorial.domain.user.event.UserCreatedEvent;
import com.example.tutorial.domain.user.event.UserDeletedEvent;
import com.example.tutorial.domain.user.event.UserUpdatedEvent;
import com.example.tutorial.domain.user.port.UserWriteRepository;
import com.example.tutorial.domain.user.event.UserEvent;
import com.example.tutorial.domain.user.event.UserEventStore;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    private final UserWriteRepository repository;
    private final UserEventStore eventStore;

    public UserCommandService(UserWriteRepository repository, UserEventStore eventStore) {
        this.repository = repository;
        this.eventStore = eventStore;
    }

    public UserId create(CreateUserCommand command) {
        if (repository.existsByUsername(command.getUsername())) {
            throw new IllegalArgumentException("Ya existe un usuario con username: " + command.getUsername());
        }
        if (repository.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con email: " + command.getEmail());
        }

        User user = new User(UserId.create(), command.getUsername(), command.getNombre(), command.getApellidos(), command.getPassword(), command.getEmail(), command.getTelefono());
        User saved = repository.save(user);

        UserId generatedId = saved.getId();
        UserEvent created = new UserCreatedEvent(generatedId, saved);
        eventStore.append(created);

        return generatedId;
    }

    public void update(UpdateUserCommand command) {
        Optional<User> existing = repository.findById(command.getId());
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado: " + command.getId());
        }

        User updated = existing.get().change(command.getNombre(), command.getApellidos(), command.getPassword(), command.getEmail(), command.getActivo(), command.getTelefono());
        repository.update(updated);

        UserEvent event = new UserUpdatedEvent(command.getId(), updated);
        eventStore.append(event);
    }

    public void delete(DeleteUserCommand command) {
        Optional<User> existing = repository.findById(command.getId());
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado: " + command.getId());
        }

        repository.delete(command.getId());
        UserEvent event = new UserDeletedEvent(command.getId());
        eventStore.append(event);
    }
}
