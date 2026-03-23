package com.example.tutorial;

import com.example.tutorial.application.UserCommandService;
import com.example.tutorial.application.UserQueryService;
import com.example.tutorial.application.command.CreateUserCommand;
import com.example.tutorial.application.command.DeleteUserCommand;
import com.example.tutorial.application.command.UpdateUserCommand;
import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.event.UserEventStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class UserServiceTest {

    @Autowired
    private UserCommandService command;

    @Autowired
    private UserQueryService query;

    @Autowired
    private UserEventStore eventStore;

    @Test
    void createReadUpdateDeleteUser() {

        var userId = command.create(new CreateUserCommand("maria", "María", "López", "123456", "maria@example.com", "600999888"));
        assertNotNull(userId);

        var saved = query.findById(userId.getValue().toString()).orElseThrow();
        assertEquals("maria", saved.getUsername());

        command.update(new UpdateUserCommand(userId, "María", "López Ruiz", "123456", "mruiz@example.com", Activo.TRUE, "600999889"));
        var updated = query.findById(userId.getValue().toString()).orElseThrow();
        assertEquals("López Ruiz", updated.getApellidos());

        command.delete(new DeleteUserCommand(userId));
        assertFalse(query.findById(userId.getValue().toString()).isPresent());

        assertEquals(3, eventStore.history().size());
    }

    @Test
    void createUserWithDuplicateUsernameShouldFail() {
        command.create(new CreateUserCommand("dani", "Daniel", "Martínez", "pwd123", "dani@example.com", "600111222"));

        assertThrows(IllegalArgumentException.class, () ->
                command.create(new CreateUserCommand("dani", "Daniel", "Martínez", "pwd123", "dani2@example.com", "600111223"))
        );
    }
}
