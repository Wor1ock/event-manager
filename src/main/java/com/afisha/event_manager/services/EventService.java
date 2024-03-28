package com.afisha.event_manager.services;

import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.EventType;
import com.afisha.event_manager.models.Location;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final EventTypeRepository eventTypeRepository;

    public void addEvent(Event event, Long locationId, Long typeId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        EventType type = eventTypeRepository.findById(typeId).orElse(null);

        if (location != null && type != null) {
            event.setLocation(location);
            event.setType(type);
            eventRepository.save(event);
        }
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

    public void updateEvent(Long id, String name, String description, LocalDateTime startDateTime, Long locationId, Long typeId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        EventType eventType = eventTypeRepository.findById(typeId).orElse(null);

        Event event = eventRepository.findById(id).orElseThrow();

        event.setName(name);
        event.setDescription(description);
        event.setStartDateTime(startDateTime);
        event.setLocation(location);
        event.setType(eventType);

        eventRepository.save(event);
    }
}
