package com.example.tutorial.domain.user.event;

import com.example.tutorial.domain.user.User;
import com.example.tutorial.domain.user.vo.UserId;

import java.time.Instant;

public final class UserCreatedEvent implements UserEvent {
    private final UserId userId;
    private final User user;
    private final Instant happenedAt;

    public UserCreatedEvent(UserId userId, User user) {
        this.userId = userId;
        this.user = user;
        this.happenedAt = Instant.now();
    }

    public UserId getUserId() { return userId; }
    public User getUser() { return user; }

    @Override
    public String eventType() { return "UserCreated"; }

    @Override
    public Instant happenedAt() { return happenedAt; }
}
