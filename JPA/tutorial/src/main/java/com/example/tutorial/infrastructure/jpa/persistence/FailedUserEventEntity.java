package com.example.tutorial.infrastructure.jpa.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "failed_user_events")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor 
public class FailedUserEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String payloadJson;

    private String errorMessage;

    public FailedUserEventEntity(String eventType, String payloadJson, String errorMessage) {
        this.eventType = eventType;
        this.payloadJson = payloadJson;
        this.errorMessage = errorMessage;
    }

}
