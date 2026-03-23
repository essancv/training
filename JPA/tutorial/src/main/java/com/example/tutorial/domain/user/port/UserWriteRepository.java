package com.example.tutorial.domain.user.port;

import com.example.tutorial.domain.user.User;
import com.example.tutorial.domain.user.vo.UserId;

import java.util.Optional;

public interface UserWriteRepository {
    User save(User user);
    void update(User user);
    void delete(UserId id);
    Optional<User> findById(UserId id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
