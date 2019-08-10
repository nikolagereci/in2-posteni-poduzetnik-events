package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.SearchCriteria;

public interface EventService {
	public Event getEventById(Long id);
	public Iterable<Event> getAllEvents();
	public Iterable<Event> searchEvents(SearchCriteria criteria);
	public void saveEvent(Event event );
	public void deleteEvent( Event event );
}
