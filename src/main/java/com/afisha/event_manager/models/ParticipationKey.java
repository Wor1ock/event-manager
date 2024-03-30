package com.afisha.event_manager.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ParticipationKey  implements Serializable {
    private Long event_id;
    private Long user_id;
}
