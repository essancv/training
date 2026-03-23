package com.example.tutorial.infrastructure.jpa.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserEventRepository extends JpaRepository<UserEventEntity, Long> {
    List<UserEventEntity> findAllByOrderByHappenedAtAsc();
}
