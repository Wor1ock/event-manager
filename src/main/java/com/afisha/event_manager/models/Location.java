package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "adress")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "capacity")
    private Integer capacity;
}
