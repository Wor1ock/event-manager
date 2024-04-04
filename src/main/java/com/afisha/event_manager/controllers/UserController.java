package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.Event;
import com.afisha.event_manager.models.EventType;
import com.afisha.event_manager.models.Location;
import com.afisha.event_manager.models.User;
import com.afisha.event_manager.repositories.EventRepository;
import com.afisha.event_manager.repositories.EventTypeRepository;
import com.afisha.event_manager.repositories.LocationRepository;
import com.afisha.event_manager.repositories.ParticipationRepository;
import com.afisha.event_manager.services.EventService;
import com.afisha.event_manager.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final EventService eventService;
    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final ParticipationRepository participationRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/login?logout";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("current_user", user);
        model.addAttribute("events", user.getEvents());
        return "/user-info";
    }

    @PostMapping("/events/{id}/follow")
    public String followEvent(@PathVariable(value = "id") Long id, Principal principal) {
        User user = eventService.getUserByPrincipal(principal);
        Event event = eventRepository.findById(id).orElseThrow();
        userService.followEvent(user, event);
        return "redirect:/events/{id}";
    }

    @GetMapping("/my-profile")
    public String showMyProfile(Principal principal, Model model) {
        User user = eventService.getUserByPrincipal(principal);
        model.addAttribute("current_user", user);

        model.addAttribute("myEvents", user.getEvents());

        List<Event> followedEvents = participationRepository.findAllEventsByUserId(user.getId());
        model.addAttribute("followedEvents", followedEvents);
        return "my-profile";
    }

    @PostMapping("/admin/add/location")
    public String followEvent(@ModelAttribute Location location) {
        eventService.addLocation(location);
        return "redirect:/admin";
    }

    @PostMapping("/admin/add/event-type")
    public String followEvent(@ModelAttribute EventType eventType) {
        eventService.addEventType(eventType);
        return "redirect:/admin";
    }
}