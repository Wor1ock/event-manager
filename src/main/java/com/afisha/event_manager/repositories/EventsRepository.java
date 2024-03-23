package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Events;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<Events, Long> {
}
