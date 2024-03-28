package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
