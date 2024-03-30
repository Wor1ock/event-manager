package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(cascade =  CascadeType.REFRESH, fetch = FetchType.LAZY,
    mappedBy = "location")
    private List<Event> events = new ArrayList<>();
}
