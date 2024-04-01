package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.Participation;
import com.afisha.event_manager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT p FROM Participation p WHERE p.user_id.id = :userId AND p.event_id.id = :eventId")
    Participation findById(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Query("SELECT p.event_id FROM Participation p WHERE p.user_id.id = :userId")
    List<Event> findAllEventsByUserId(@Param("userId") Long userId);

    @Query("SELECT p.user_id FROM Participation p WHERE p.event_id.id = :eventId")
    List<User> findAllUsersByEventId(@Param("eventId") Long eventId);
}
