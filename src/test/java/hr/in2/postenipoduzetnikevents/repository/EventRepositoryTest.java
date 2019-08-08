package hr.in2.postenipoduzetnikevents.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hr.in2.postenipoduzetnikevents.model.Event;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

	@Autowired
	EventRepository eventRepository;
	
	@Test
	public void test() {
		long eventsCount = eventRepository.count();
		assertEquals(11, eventsCount);
//		Iterable<Event> events = eventRepository.findAll();
//		events.forEach(event -> System.out.println(event.getCity().getOrgUnit().getParent().getName()));
	}	

}
