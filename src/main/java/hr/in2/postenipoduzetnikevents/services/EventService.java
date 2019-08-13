package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.view.SearchCriteria;

public interface EventService {
	Event getEventById(Long id);
	Iterable<Event> getAllEvents();
	Iterable<Event> searchEvents(SearchCriteria criteria);
	void saveEvent(Event event );
	void deleteEvent( Event event );
}
