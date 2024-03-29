package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
