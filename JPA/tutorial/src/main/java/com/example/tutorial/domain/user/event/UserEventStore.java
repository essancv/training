package com.example.tutorial.domain.user.event;

import java.util.List;

public interface UserEventStore {
    void append(UserEvent event);
    List<UserEvent> history();
}
