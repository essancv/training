package com.example.tutorial.domain.user.event;

import com.example.tutorial.domain.user.vo.UserId;

import java.time.Instant;

public final class UserDeletedEvent implements UserEvent {
    private final UserId userId;
    private final Instant happenedAt;

    public UserDeletedEvent(UserId userId) {
        this.userId = userId;
        this.happenedAt = Instant.now();
    }

    public UserId getUserId() { return userId; }

    @Override
    public String eventType() { return "UserDeleted"; }

    @Override
    public Instant happenedAt() { return happenedAt; }
}
