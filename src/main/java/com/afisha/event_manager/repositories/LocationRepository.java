package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
