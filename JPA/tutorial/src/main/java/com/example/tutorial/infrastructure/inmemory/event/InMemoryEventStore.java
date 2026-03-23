package com.example.tutorial.infrastructure.inmemory.event;

import com.example.tutorial.domain.user.event.UserEvent;
import com.example.tutorial.domain.user.event.UserEventStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryEventStore implements UserEventStore {

    private final List<UserEvent> events = new ArrayList<>();

    @Override
    public void append(UserEvent event) {
        events.add(event);
    }

    @Override
    public List<UserEvent> history() {
        return Collections.unmodifiableList(events);
    }
}
