package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.view.SearchCriteria;
import hr.in2.postenipoduzetnikevents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSeviceImpl implements EventService {

    @Autowired
    CityService cityService;

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Iterable<Event> searchEvents(SearchCriteria criteria) {
        return eventRepository.searchEvents(criteria);
    }

    @Override
    public void saveEvent(Event event) {
        //In case of insert, set id to null
        if (event.getId().equals(0L))
            event.setId(null);
        //Set city
        City city = cityService.getCity(event.getCity().getId());
        if(city == null)
            throw new IllegalArgumentException();
        else
            event.setCity(city);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        if (eventRepository.existsById(event.getId()))
            eventRepository.delete(event);
        else
            throw new IllegalArgumentException();
    }
}
