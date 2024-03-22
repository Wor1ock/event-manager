package com.afisha.event_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
@GetMapping("/Events")
        public String eventMain(Model model) {
    return "event-main";

}
}
