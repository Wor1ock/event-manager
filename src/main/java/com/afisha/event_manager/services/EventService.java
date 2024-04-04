package com.afisha.event_manager.services;

import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.EventType;
import com.afisha.event_manager.models.Location;
import com.afisha.event_manager.models.User;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import com.afisha.event_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    private final EventTypeRepository eventTypeRepository;
    @Autowired
    private final UserRepository userRepository;

    public void addEvent(Principal principal, Event event, Long locationId, Long typeId) {
        event.setUser(getUserByPrincipal(principal));
        Location location = locationRepository.findById(locationId).orElse(null);
        EventType type = eventTypeRepository.findById(typeId).orElse(null);

        if (location != null && type != null) {
            event.setLocation(location);
            event.setType(type);
            eventRepository.save(event);
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public Map<String, List<?>> getLocationsAndEventTypes() {
        Map<String, List<?>> data = new HashMap<>();

        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);

        List<EventType> eventTypes = new ArrayList<>();
        eventTypeRepository.findAll().forEach(eventTypes::add);

        data.put("locations", locations);
        data.put("eventTypes", eventTypes);

        return data;
    }

    public Map<String, Object> getEventAndDataById(Long id) {
        Map<String, Object> data = new HashMap<>();

        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(e -> data.put("event", e));

        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        data.put("locations", locations);

        List<EventType> eventTypes = new ArrayList<>();
        eventTypeRepository.findAll().forEach(eventTypes::add);
        data.put("eventTypes", eventTypes);

        return data;
    }

    public void updateEvent(Event updatedEvent, Long locationId, Long typeId) {
        Event event = eventRepository.findById(updatedEvent.getId()).orElseThrow();
        Location location = locationRepository.findById(locationId).orElse(null);
        EventType eventType = eventTypeRepository.findById(typeId).orElse(null);

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setStartDateTime(updatedEvent.getStartDateTime());
        event.setLocation(location);
        event.setType(eventType);

        eventRepository.save(event);
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public void addEventType(EventType eventType) {
        eventTypeRepository.save(eventType);
    }
}
