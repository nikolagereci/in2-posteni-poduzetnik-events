package hr.in2.postenipoduzetnikevents.controllers;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.CitySize;
import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.OrgUnit;
import hr.in2.postenipoduzetnikevents.model.view.SearchCriteria;
import hr.in2.postenipoduzetnikevents.model.view.SelectData;
import hr.in2.postenipoduzetnikevents.services.CityService;
import hr.in2.postenipoduzetnikevents.services.EventService;
import hr.in2.postenipoduzetnikevents.services.OrgUnitService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
//        Iterable<Event> events = eventService.getAllEvents();
//        model.addAttribute("events", events);
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
        Iterable<CitySize> citySizes = cityService.getAllCitySizes();

        model.addAttribute("search", true);
        model.addAttribute("searchCriteria", new SearchCriteria());
        model.addAttribute("cities", cities);
        model.addAttribute("regions", regions);
        model.addAttribute("counties", counties);
        model.addAttribute("citySizes", citySizes);
        home(model);
        return "index";
    }

    @PostMapping("/search")
    public String searchPost(SearchCriteria criteria, Model model) {
        //Ako nije odabran nijedan grad, nađi sve gradove koji odgovaraju kriterijima
        if (criteria.getCities() == null || criteria.getCities().size() == 0)
            criteria.setCities (IterableUtils.toList(cityService.searchCities(criteria.getRegions(), criteria.getCounties(), criteria.getCitySizes())));

        //Pretraživanje
        Iterable<Event> events = eventService.searchEvents(criteria);

        model.addAttribute("events", events);
//        searchEvent(model);
//        model.addAttribute("searchCriteria", criteria);
        return "index";
    }

    @PostMapping(value = "/search/selector" , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, List<SelectData>> postSearchEvent(SearchCriteria criteria){
        Map<String,List<SelectData>> returnMap = new HashMap<>();
        List<OrgUnit> counties;
        List<City> cities;
        if (!criteria.getRegions().isEmpty()) {
            counties = IterableUtils.toList(orgUnitService.getCountiesByRegions(criteria.getRegions()));
        }else {
            counties = IterableUtils.toList(orgUnitService.getAllCounties());
        }
        cities = IterableUtils.toList(cityService.searchCities(criteria.getRegions(), criteria.getCounties(), criteria.getCitySizes()));
        List<SelectData> countiesSelect = SelectData.objectListToSelectDataList(Collections.singletonList(counties));
        returnMap.put("counties", countiesSelect);
        List<SelectData> citiesSelect = SelectData.objectListToSelectDataList(Collections.singletonList(cities));
        returnMap.put("cities", citiesSelect);
        return returnMap;
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
