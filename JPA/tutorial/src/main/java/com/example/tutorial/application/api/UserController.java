package com.example.tutorial.application.api;

import com.example.tutorial.application.UserCommandService;
import com.example.tutorial.application.UserQueryService;
import com.example.tutorial.application.command.CreateUserCommand;
import com.example.tutorial.application.command.DeleteUserCommand;
import com.example.tutorial.application.command.UpdateUserCommand;
import com.example.tutorial.application.dto.request.CreateUserRequest;
import com.example.tutorial.application.dto.request.UpdateUserRequest;
import com.example.tutorial.application.dto.response.UserResponse;
import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.vo.UserId;
import com.example.tutorial.domain.user.read.UserSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserCommandService commandService;
    private final UserQueryService queryService;

    public UserController(UserCommandService commandService, UserQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        var userId = commandService.create(new CreateUserCommand(
                request.getUsername(),
                request.getNombre(),
                request.getApellidos(),
                request.getPassword(),
                request.getEmail(),
                request.getTelefono()
        ));

        var summary = queryService.findById(userId.getValue().toString()).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(summary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return queryService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return queryService.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/activo/{activo}")
    public List<UserResponse> getByActivo(@PathVariable Activo activo) {
        return queryService.findByActivo(activo).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request) {
        var userId = UserId.of(id);
        commandService.update(new UpdateUserCommand(
                userId,
                request.getNombre(),
                request.getApellidos(),
                request.getPassword(),
                request.getEmail(),
                request.getActivo(),
                request.getTelefono()
        ));

        return queryService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var userId = UserId.of(id);
        commandService.delete(new DeleteUserCommand(userId));
        return ResponseEntity.noContent().build();
    }

    private UserResponse toResponse(UserSummary summary) {
        return new UserResponse(
                summary.getId(),
                summary.getUsername(),
                summary.getNombre(),
                summary.getApellidos(),
                summary.getEmail(),
                summary.getActivo(),
                summary.getTelefono()
        );
    }
}
