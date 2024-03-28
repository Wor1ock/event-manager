package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {
}
