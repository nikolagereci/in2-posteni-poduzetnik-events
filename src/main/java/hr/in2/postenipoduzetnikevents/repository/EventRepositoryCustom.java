package hr.in2.postenipoduzetnikevents.repository;

import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.view.SearchCriteria;

public interface EventRepositoryCustom {
    Iterable<Event> searchEvents(SearchCriteria criteria);
}
