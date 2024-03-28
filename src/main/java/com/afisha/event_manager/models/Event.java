package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "startDateTime")
    private LocalDateTime startDateTime;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EventType type;

    public Event(String name, String description, LocalDateTime startDateTime, Location location, EventType type) {
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.location = location;
        this.type = type;
    }

}
