package com.example.tutorial.infrastructure.jpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedUserEventRepository extends JpaRepository<FailedUserEventEntity, Long> {
}
