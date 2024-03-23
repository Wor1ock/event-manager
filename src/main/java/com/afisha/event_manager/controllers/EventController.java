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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
        return "event-add";
    }

    @PostMapping("/event/add")
    public String eventPostAdd(@RequestParam String name, @RequestParam String description,
                               @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime,
                               @RequestParam Long location_id, @RequestParam Long type_id, Model model) {
        Locations location = locationsRepository.findById(location_id).orElse(null);
        EventTypes eventType = eventTypesRepository.findById(type_id).orElse(null);

        if (location == null || eventType == null) {
            // Обработка случая, если локация или тип не найдены
            return "error-page";
        }

        Events event = new Events(name, description, startDateTime, endDateTime, location, eventType);
        eventsRepository.save(event);
        return "redirect:/";
    }
}
