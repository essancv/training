package com.example.tutorial.application.command;

import com.example.tutorial.domain.user.vo.UserId;

public abstract class UserCommand {
    private final UserId id;

    protected UserCommand(UserId id) {
        this.id = id;
    }

    public UserId getId() { return id; }
}
