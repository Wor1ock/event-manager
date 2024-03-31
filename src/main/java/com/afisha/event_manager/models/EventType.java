package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade =  CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "type")
    private List<Event> events = new ArrayList<>();

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
