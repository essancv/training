package com.example.tutorial.application.command;

import com.example.tutorial.domain.user.vo.UserId;

public final class DeleteUserCommand {
    private final UserId id;

    public DeleteUserCommand(UserId id) {
        this.id = id;
    }

    public UserId getId() { return id; }
}
