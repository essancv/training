package com.example.tutorial.domain.user.port;

import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.read.UserSummary;

import java.util.List;
import java.util.Optional;

public interface UserReadRepository {
    Optional<UserSummary> findById(String id);
    Optional<UserSummary> findByUsername(String username);
    List<UserSummary> findAll();
    List<UserSummary> findByActivo(Activo activo);
}
