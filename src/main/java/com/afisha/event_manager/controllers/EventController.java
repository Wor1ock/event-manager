package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.EventType;
import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.Location;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @GetMapping("/")
    public String eventMain(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events")
    public String eventMain2(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events/add")
    public String eventAdd(Model model) {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);

        List<EventType> eventTypes = new ArrayList<>();
        eventTypeRepository.findAll().forEach(eventTypes::add);

        model.addAttribute("locations", locations);
        model.addAttribute("eventTypes", eventTypes);
        return "event-add";
    }

    @PostMapping("/events/add")
    public String eventPostAdd(@RequestParam String name, @RequestParam String description,
                               @RequestParam LocalDateTime startDateTime,
                               @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        Location location = locationRepository.findById(location_id).orElse(null);
        EventType eventType = eventTypeRepository.findById(type_id).orElse(null);

        Event event = new Event(name, description, startDateTime, location, eventType);
        eventRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String eventInfo(@PathVariable(value = "id") Long id, Model model) {
        if(!eventRepository.existsById(id)){
            return "redirect:/";
        }

        Optional <Event> event = eventRepository.findById(id);
        ArrayList <Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-details";
    }

    @GetMapping("/events/{id}/edit")
    public String eventEdit(@PathVariable(value = "id") Long id, Model model) {
        Optional <Event> event = eventRepository.findById(id);
        ArrayList <Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);

        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);

        List<EventType> eventTypes = new ArrayList<>();
        eventTypeRepository.findAll().forEach(eventTypes::add);

        model.addAttribute("locations", locations);
        model.addAttribute("eventTypes", eventTypes);
        return "event-edit";
    }

    @PostMapping("/events/{id}/edit")
    public String eventPostUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String description,
                               @RequestParam LocalDateTime startDateTime,
                               @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        Location location = locationRepository.findById(location_id).orElse(null);
        EventType eventType = eventTypeRepository.findById(type_id).orElse(null);

        Event event = eventRepository.findById(id).orElseThrow();
        event.setName(name);
        event.setDescription(description);
        event.setStartDateTime(startDateTime);
        event.setLocation(location);
        event.setType(eventType);

        eventRepository.save(event);
        return "redirect:/";
    }
//    @GetMapping("/events/{id}/remove")
//    public String eventGetDelete(@PathVariable(value = "id") Long id, Model model) {
//        return "redirect:/events/ + ${id}";
//    }
    @PostMapping("/events/{id}/remove")
    public String eventPostDelete(@PathVariable(value = "id") Long id, Model model) {
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/";
    }
}
