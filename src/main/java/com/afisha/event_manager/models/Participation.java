package com.afisha.event_manager.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "participations")
@NoArgsConstructor
@IdClass(ParticipationKey.class)
public class Participation {
    @Id
//    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_id")
    private Long event_id;

    @Id
//    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
    private Long user_id;

    @Column(name = "status")
    private Boolean status;
}
