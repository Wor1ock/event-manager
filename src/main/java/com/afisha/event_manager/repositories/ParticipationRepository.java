package com.afisha.event_manager.repositories;

import com.afisha.event_manager.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT p FROM Participation p WHERE p.user_id.id = :userId AND p.event_id.id = :eventId")
    Participation findById(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
