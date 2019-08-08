package hr.in2.postenipoduzetnikevents.repository;

import org.springframework.data.repository.CrudRepository;

import hr.in2.postenipoduzetnikevents.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
