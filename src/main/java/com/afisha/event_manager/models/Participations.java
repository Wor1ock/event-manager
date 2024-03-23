package com.afisha.event_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Participations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long part_id;

    public Long getPart_id() {
        return part_id;
    }

    public void setPart_id(Long part_id) {
        this.part_id = part_id;
    }

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

    private Long event_id, user_id;
    private Boolean status;
}
