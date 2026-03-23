package com.example.tutorial.application;

import com.example.tutorial.domain.user.vo.Activo;
import com.example.tutorial.domain.user.read.UserSummary;
import com.example.tutorial.domain.user.port.UserReadRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UserReadRepository repository;

    public UserQueryService(UserReadRepository repository) {
        this.repository = repository;
    }

    public Optional<UserSummary> findById(String id) {
        return repository.findById(id);
    }

    public Optional<UserSummary> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<UserSummary> findByActivo(Activo activo) {
        return repository.findByActivo(activo);
    }

    public List<UserSummary> findAll() {
        return repository.findAll();
    }
}
