package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.*;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import com.afisha.event_manager.repositories.ParticipationRepository;
import com.afisha.event_manager.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private ParticipationRepository participationRepository;

    @GetMapping({"/", "/events"})
    public String eventMain(Principal principal, Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);

        User user = eventService.getUserByPrincipal(principal);
        model.addAttribute("current_user", user);
        return "events";
    }

    @GetMapping("/events/add")
    public String eventAdd(Principal principal, Model model) {
        Map<String, List<?>> data = eventService.getLocationsAndEventTypes();
        model.addAllAttributes(data);

        User user = eventService.getUserByPrincipal(principal);
        model.addAttribute("current_user", user);
        return "event-add";
    }

    @PostMapping("/events/add")
    public String eventPostAdd(@ModelAttribute("event") Event event, @RequestParam("location_id") Long locationId,
                               @RequestParam("type_id") Long typeId, Principal principal) {
        eventService.addEvent(principal, event, locationId, typeId);
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String eventInfo(@PathVariable(value = "id") Long id, Principal principal, Model model) {
        Event event = eventRepository.findById(id).orElseThrow();
        model.addAttribute("event", event);

        User user = eventService.getUserByPrincipal(principal);
        model.addAttribute("current_user", user);

        Participation participation = participationRepository.findById(user.getId(), event.getId());
        model.addAttribute("participation", participation);
        return "event-details";
    }


    @GetMapping("/events/{id}/edit")
    public String eventEdit(@PathVariable(value = "id") Long id, Principal principal, Model model) {
        Map<String, Object> data = eventService.getEventAndDataById(id);
        model.addAllAttributes(data);

        User user = eventService.getUserByPrincipal(principal);
        model.addAttribute("current_user", user);
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
