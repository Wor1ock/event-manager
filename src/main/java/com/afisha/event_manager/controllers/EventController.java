package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.EventTypes;
import com.afisha.event_manager.models.Events;
import com.afisha.event_manager.models.Locations;
import com.afisha.event_manager.repositories.EventsRepository;
import com.afisha.event_manager.repositories.LocationsRepository;
import com.afisha.event_manager.repositories.EventTypesRepository;
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
    private EventsRepository eventsRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private EventTypesRepository eventTypesRepository;

    @GetMapping("/")
    public String eventMain(Model model) {
        Iterable<Events> events = eventsRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/event/add")
    public String eventAdd(Model model) {
        List<Locations> locations = new ArrayList<>();
        locationsRepository.findAll().forEach(locations::add);

        List<EventTypes> eventTypes = new ArrayList<>();
        eventTypesRepository.findAll().forEach(eventTypes::add);

        model.addAttribute("locations", locations);
        model.addAttribute("eventTypes", eventTypes);
        return "event-add";
    }

    @PostMapping("/event/add")
    public String eventPostAdd(@RequestParam String name, @RequestParam String description,
                               @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime,
                               @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        Locations location = locationsRepository.findById(location_id).orElse(null);
        EventTypes eventType = eventTypesRepository.findById(type_id).orElse(null);

        Events event = new Events(name, description, startDateTime, endDateTime, location, eventType);
        eventsRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String eventInfo(@PathVariable(value = "id") Long id, Model model) {
        if(!eventsRepository.existsById(id)){
            return "redirect:/";
        }

        Optional <Events> event = eventsRepository.findById(id);
        ArrayList <Events> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-details";
    }

    @GetMapping("/events/{id}/edit")
    public String eventEdit(@PathVariable(value = "id") Long id, Model model) {
        Optional <Events> event = eventsRepository.findById(id);
        ArrayList <Events> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);

        List<Locations> locations = new ArrayList<>();
        locationsRepository.findAll().forEach(locations::add);

        List<EventTypes> eventTypes = new ArrayList<>();
        eventTypesRepository.findAll().forEach(eventTypes::add);

        model.addAttribute("locations", locations);
        model.addAttribute("eventTypes", eventTypes);
        return "event-edit";
    }

    @PostMapping("/events/{id}/edit")
    public String eventPostUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String description,
                               @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime,
                               @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        Locations location = locationsRepository.findById(location_id).orElse(null);
        EventTypes eventType = eventTypesRepository.findById(type_id).orElse(null);

        Events event = eventsRepository.findById(id).orElseThrow();
        event.setName(name);
        event.setDescription(description);
        event.setStartDateTime(startDateTime);
        event.setEndDateTime(endDateTime);
        event.setLocation(location);
        event.setType(eventType);

        eventsRepository.save(event);
        return "redirect:/";
    }
//    @GetMapping("/events/{id}/remove")
//    public String eventGetDelete(@PathVariable(value = "id") Long id, Model model) {
//        return "redirect:/events/ + ${id}";
//    }
    @PostMapping("/events/{id}/remove")
    public String eventPostDelete(@PathVariable(value = "id") Long id, Model model) {
        Events event = eventsRepository.findById(id).orElseThrow();
        eventsRepository.delete(event);
        return "redirect:/";
    }
}
