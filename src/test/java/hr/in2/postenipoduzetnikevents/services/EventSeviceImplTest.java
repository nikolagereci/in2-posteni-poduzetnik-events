package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.Event;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventSeviceImplTest {

    @Autowired
    EventSeviceImpl eventSevice;

    @Test
    public void getEventByIdTest() {
        Event event = eventSevice.getEventById(1);
        assertNotNull(event);
        assertFalse(Strings.isEmpty(event.getName()));
    }

    @Test
    public void getAllEvents() {
        Iterable<Event> allEvents = eventSevice.getAllEvents();
        assertNotNull(allEvents);
        List<Event> events = new LinkedList<>();
        allEvents.forEach(event -> events.add(event));
        assertEquals(events.size(), 11);
    }

    @Test
    public void searchEvents() {
        //TODO implementirati
    }

    @Test
    public void createEvent() {
    }

    @Test
    public void updateEvent() {
    }

    @Test
    public void deleteEvent() {
    }
}