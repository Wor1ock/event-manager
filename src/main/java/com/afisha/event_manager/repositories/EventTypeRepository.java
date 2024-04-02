package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
