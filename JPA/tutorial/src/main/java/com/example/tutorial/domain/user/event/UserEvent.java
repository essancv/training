package com.example.tutorial.domain.user.event;

import java.time.Instant;

public interface UserEvent {
    String eventType();
    Instant happenedAt();
}
