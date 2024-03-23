package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.Events;
import com.afisha.event_manager.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
    @Autowired
    private EventsRepository eventsRepository;
    @GetMapping("/")
    public String eventMain(Model model) {
        Iterable<Events> events = eventsRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }
    @GetMapping("/event-add")
    public String greeting(Model model) {
        return "event-add";
    }
}
