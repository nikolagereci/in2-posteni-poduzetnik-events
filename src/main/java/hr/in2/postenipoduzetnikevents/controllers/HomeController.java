package hr.in2.postenipoduzetnikevents.controllers;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import hr.in2.postenipoduzetnikevents.model.SearchCriteria;
import hr.in2.postenipoduzetnikevents.services.CityService;
import hr.in2.postenipoduzetnikevents.services.EventService;
import hr.in2.postenipoduzetnikevents.services.OrgUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    EventService eventService;

    @Autowired
    CityService cityService;

    @Autowired
    OrgUnitService orgUnitService;

    @RequestMapping("/")
    public String home(Model model){
        Iterable<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") long id, Model model) {
        Event event;
        if (id != 0)
            event = eventService.getEventById(id);
        else
            event = new Event();
        Iterable<City> cities = cityService.getAllCities();
        model.addAttribute("event", event);
        model.addAttribute("cities", cities);
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

    @GetMapping("/search")
    public String searchEvent(Model model) {
        Iterable<OrgUnit> regions = orgUnitService.getAllRegions();
        Iterable<OrgUnit> counties = orgUnitService.getAllCounties();
        Iterable<City> cities = cityService.getAllCities();
        model.addAttribute("search", true);
        model.addAttribute("searchCriteria", new SearchCriteria());
        model.addAttribute("cities", cities);
        model.addAttribute("regions", regions);
        model.addAttribute("counties", counties);
        home(model);
        return "index";
    }

    @PostMapping("/search")
    public String postSearchEvent(SearchCriteria criteria, Model model){

        return "index";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(Event event, Model model) {
        if (event != null){
            eventService.saveEvent(event);
        }
        else
            throw new IllegalArgumentException("Pogrešan id!");
        home(model);
        return "index";
    }

}
