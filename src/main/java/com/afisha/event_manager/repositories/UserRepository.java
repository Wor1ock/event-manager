package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
