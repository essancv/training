package com.example.tutorial.domain.user.port;

import com.example.tutorial.domain.user.event.UserCreatedEvent;
import com.example.tutorial.domain.user.event.UserUpdatedEvent;
import com.example.tutorial.domain.user.event.UserDeletedEvent;

public interface UserEventPublisher {
    void publishUserCreated(UserCreatedEvent event);
    void publishUserUpdated(UserUpdatedEvent event);
    void publishUserDeleted(UserDeletedEvent event);
}
