package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {
}
