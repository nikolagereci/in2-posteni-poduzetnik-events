package hr.in2.postenipoduzetnikevents.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EventRepositoryTest {

	@Autowired
	EventRepository eventRepository;
	
	@Test
	public void test() {
		long eventsCount = eventRepository.count();
		assertEquals(11, eventsCount);
	}

}
