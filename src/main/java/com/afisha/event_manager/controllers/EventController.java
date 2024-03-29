package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.EventType;
import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.Location;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import com.afisha.event_manager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;
    @Autowired
    private EventService eventService;

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
        Map<String, List<?>> data = eventService.getLocationsAndEventTypes();
        model.addAllAttributes(data);
        return "event-add";
    }

    @PostMapping("/events/add")
    public String eventPostAdd(@ModelAttribute("event") Event event, @RequestParam("location_id") Long locationId, @RequestParam("type_id") Long typeId) {
        eventService.addEvent(event, locationId, typeId);
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String eventInfo(@PathVariable(value = "id") Long id, Model model) {
        Optional<Event> event = eventRepository.findById(id);

        if (!event.isPresent())
            return "redirect:/";

        model.addAttribute("event", event.get());
        return "event-details";
    }


    @GetMapping("/events/{id}/edit")
    public String eventEdit(@PathVariable(value = "id") Long id, Model model) {
        Map<String, Object> data = eventService.getEventAndDataById(id);
        model.addAllAttributes(data);
        return "event-edit";
    }

    @PostMapping("/events/{id}/edit")
    public String eventPostUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String description,
                                  @RequestParam LocalDateTime startDateTime,
                                  @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        eventService.updateEvent(id, name, description, startDateTime, location_id, type_id);
        return "redirect:/";
    }

//    @PostMapping("/events/{id}/edit")
//    public String eventPostUpdate(@PathVariable(value = "id") Long id, @ModelAttribute("event") Event event, Model model) {
//        eventService.updateEvent(id, event);
//        return "redirect:/";
//    }

    @PostMapping("/events/{id}/remove")
    public String eventPostDelete(@PathVariable(value = "id") Long id, Model model) {
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/";
    }
}
