package com.afisha.event_manager.models;

import jakarta.persistence.*;

@Entity
@IdClass(ParticipationKey.class)
public class Participations {
    @Id
    private Long event_id;
    @Id
    private Long user_id;
    private Boolean status;
    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
