package hr.in2.postenipoduzetnikevents.controllers;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    EventService eventService;

    @RequestMapping("/")
    public String home(Model model){
        Iterable<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("edit", true);
        home(model);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id, Model model) {
        Event event = eventService.getEventById(id);
        if (event != null)
            eventService.deleteEvent(event);
        else
            throw new IllegalArgumentException("Pogrešan id!");
        home(model);
        return "index";
    }

}
