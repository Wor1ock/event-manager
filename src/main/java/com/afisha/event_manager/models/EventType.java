package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
}
