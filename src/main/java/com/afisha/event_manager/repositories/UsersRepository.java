package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
