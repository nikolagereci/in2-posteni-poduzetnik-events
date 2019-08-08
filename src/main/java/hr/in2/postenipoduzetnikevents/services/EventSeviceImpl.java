package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.SearchCriteria;
import hr.in2.postenipoduzetnikevents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSeviceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Iterable<Event> searchEvents(SearchCriteria criteria) {
        return null;
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(Event event) {
        if (eventRepository.existsById(event.getId()))
            eventRepository.save(event);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void deleteEvent(Event event) {
        if (eventRepository.existsById(event.getId()))
            eventRepository.delete(event);
        else
            throw new IllegalArgumentException();
    }
}
