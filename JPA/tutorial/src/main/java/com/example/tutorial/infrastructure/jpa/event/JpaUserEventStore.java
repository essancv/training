package com.example.tutorial.infrastructure.jpa.event;

import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.event.UserCreatedEvent;
import com.example.tutorial.domain.user.event.UserDeletedEvent;
import com.example.tutorial.domain.user.event.UserEvent;
import com.example.tutorial.domain.user.event.UserEventStore;
import com.example.tutorial.domain.user.event.UserUpdatedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaUserEventStore implements UserEventStore {

    private final JpaUserEventRepository repository;

    public JpaUserEventStore(JpaUserEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void append(UserEvent event) {
        UserEventEntity entity;
        if (event instanceof UserCreatedEvent created) {
            var user = created.getUser();
            entity = new UserEventEntity(
                    event.eventType(),
                    event.happenedAt(),
                    created.getUserId().getValue(),
                    user.getUsername(),
                    user.getNombre(),
                    user.getApellidos(),
                    user.getEmail(),
                    user.getActivo(),
                    user.getTelefono(),
                    user.getCreationDate(),
                    user.getUpdateDate()
            );
        } else if (event instanceof UserUpdatedEvent updated) {
            var user = updated.getUser();
            entity = new UserEventEntity(
                    event.eventType(),
                    event.happenedAt(),
                    updated.getUserId().getValue(),
                    user.getUsername(),
                    user.getNombre(),
                    user.getApellidos(),
                    user.getEmail(),
                    user.getActivo(),
                    user.getTelefono(),
                    user.getCreationDate(),
                    user.getUpdateDate()
            );
        } else if (event instanceof UserDeletedEvent deleted) {
            entity = new UserEventEntity(
                    event.eventType(),
                    event.happenedAt(),
                    deleted.getUserId().getValue(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        } else {
            entity = new UserEventEntity(event.eventType(), event.happenedAt(), null, null, null, null, null, null, null, null, null);
        }
        repository.save(entity);
    }

    @Override
    public List<UserEvent> history() {
        return repository.findAllByOrderByHappenedAtAsc().stream().map(e -> {
            switch (e.getEventType()) {
                case "UserCreated":
                    return new UserCreatedEvent(com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()),
                            new com.example.tutorial.domain.user.User(
                                    com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()),
                                    e.getUsername(),
                                    e.getNombre(),
                                    e.getApellidos(),
                                    "[REDACTED]",
                                    e.getEmail(),
                                    e.getActivo() != null ? e.getActivo() : Activo.TRUE,
                                    e.getTelefono(),
                                    e.getCreationDate(),
                                    e.getUpdateDate()
                            )
                    );
                case "UserUpdated":
                    return new UserUpdatedEvent(com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()),
                            new com.example.tutorial.domain.user.User(
                                    com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()),
                                    e.getUsername(),
                                    e.getNombre(),
                                    e.getApellidos(),
                                    "[REDACTED]",
                                    e.getEmail(),
                                    e.getActivo() != null ? e.getActivo() : Activo.TRUE,
                                    e.getTelefono(),
                                    e.getCreationDate(),
                                    e.getUpdateDate()
                            )
                    );
                case "UserDeleted":
                    return new UserDeletedEvent(com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()));
                default:
                    return new UserDeletedEvent(com.example.tutorial.domain.user.vo.UserId.of(e.getUserId()));
            }
        }).collect(Collectors.toList());
    }
}
