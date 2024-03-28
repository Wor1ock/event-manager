package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(ParticipationKey.class)
public class Participation {
    @Id
    @Column(name = "event_id")
    private Long event_id;
    @Id
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "status")
    private Boolean status;
}
