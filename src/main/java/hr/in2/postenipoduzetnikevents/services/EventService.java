package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.SearchCriteria;

public interface EventService {
	public Event getEventById(long id);
	public Iterable<Event> getAllEvents();
	public Iterable<Event> searchEvents(SearchCriteria criteria);
	public void createEvent( Event event );
	public void updateEvent( Event event );
	public void deleteEvent( Event event );
}
