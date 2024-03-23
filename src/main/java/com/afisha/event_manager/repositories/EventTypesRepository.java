package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.EventTypes;
import org.springframework.data.repository.CrudRepository;

public interface EventTypesRepository extends CrudRepository<EventTypes, Long> {
}
