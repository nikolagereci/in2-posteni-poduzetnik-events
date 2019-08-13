package hr.in2.postenipoduzetnikevents.services;

import hr.in2.postenipoduzetnikevents.model.City;
import hr.in2.postenipoduzetnikevents.model.Event;
import hr.in2.postenipoduzetnikevents.model.view.SearchCriteria;
import org.apache.commons.collections4.IterableUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EventSeviceImplTest {

    @Autowired
    EventSeviceImpl eventService;

    @Autowired
    CityService cityService;

    @Test
    public void getEventByIdTest() {
        Event event = eventService.getEventById(1L);
        assertNotNull(event);
        assertFalse(Strings.isEmpty(event.getName()));
    }

    @Test
    public void getAllEvents() {
        Iterable<Event> allEvents = eventService.getAllEvents();
        assertNotNull(allEvents);
        List<Event> events = new LinkedList<>();
        allEvents.forEach(event -> events.add(event));
        assertEquals(events.size(), 11);
    }

    @Test
    public void searchEventsName() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setName("Dani");
        criteria.setFree(true);
        Iterable<Event> events = eventService.searchEvents(criteria);
        assertEquals(2, IterableUtils.size(events));
    }

    @Test
    public void searchEventsFree() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setFree(true);
        Iterable<Event> events = eventService.searchEvents(criteria);
        assertEquals(7, IterableUtils.size(events));
        criteria.setFree(false);
        Iterable<Event> eventsPayed = eventService.searchEvents(criteria);
        assertEquals(4, IterableUtils.size(eventsPayed));
    }

    @Test
    public void searchEventsCity() {
        //Svi eventi u Splitu
        SearchCriteria criteria = new SearchCriteria();
        List<City> cities= new LinkedList<>();
        cities.add(cityService.getCity(10L));
        criteria.setCities(cities);
        Iterable<Event> events = eventService.searchEvents(criteria);
        assertEquals(1, IterableUtils.size(events));

        //Svi eventi u Splitu i Zagrebu
        cities.add(cityService.getCity(4L));
        events = eventService.searchEvents(criteria);
        assertEquals(3, IterableUtils.size(events));

        //Svi eventi u Splitu, Zagrebu i Dubrovniku
        cities.add(cityService.getCity(11L));
        events = eventService.searchEvents(criteria);
        assertEquals(4, IterableUtils.size(events));
    }

    @Test
    public void searchEventsTimeFrom(){
        //Svi eventi koji pocinju nakon 01.01.2020
        SearchCriteria criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2020,1,1,0,0));
        Iterable<Event> events = eventService.searchEvents(criteria);
        assertEquals(2, IterableUtils.size(events));
        assertTrue(IterableUtils.get(events, 0).getName().startsWith("Lejdi"));
        assertTrue(IterableUtils.get(events, 1).getName().startsWith("Panonian"));

        //Svi eventi koji pocinju izmedju 01.01.2020 i 01.01.2021
        criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2020,1,1,0,0));
        criteria.setStartTo(LocalDateTime.of(2021,1,1,0,0));
        events = eventService.searchEvents(criteria);
        assertEquals(1, IterableUtils.size(events));
        assertTrue(IterableUtils.get(events, 0).getName().startsWith("Panonian"));

        //Svi eventi koji pocinju nakon 01.01.2020 i zavrsavaju nakon 01.06.2020
        criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2020,1,1,0,0));
        criteria.setEndFrom(LocalDateTime.of(2020,6,1,0,0));
        events = eventService.searchEvents(criteria);
        assertEquals(2, IterableUtils.size(events));
        assertTrue(IterableUtils.get(events, 0).getName().startsWith("Lejdi"));
        assertTrue(IterableUtils.get(events, 1).getName().startsWith("Panonian"));

        //Svi eventi koji pocinju izmedju 01.01.2019 i 01.06.2019, i zavrsavaju nakon 01.12.2019
        criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2019,1,1,0,0));
        criteria.setStartTo(LocalDateTime.of(2019,6,1,0,0));
        criteria.setEndFrom(LocalDateTime.of(2019,12,1,0,0));
        events = eventService.searchEvents(criteria);
        assertEquals(0, IterableUtils.size(events));

        //Svi eventi koji pocinju izmedju 01.01.2019 i 01.06.2019, i zavrsavaju nakon 01.12.2019
        criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2022,1,1,0,0));
        criteria.setStartTo(LocalDateTime.of(2022,6,1,0,0));
        criteria.setEndFrom(LocalDateTime.of(2022,5,1,0,0));
        events = eventService.searchEvents(criteria);
        assertEquals(1, IterableUtils.size(events));

        //Svi eventi koji pocinju izmedju 01.01.2022 i 01.06.2022, i zavrsavaju izmedju 15.05.2022 i 17.05.2022
        criteria = new SearchCriteria();
        criteria.setStartFrom(LocalDateTime.of(2022,1,1,0,0));
        criteria.setStartTo(LocalDateTime.of(2022,6,1,0,0));
        criteria.setEndFrom(LocalDateTime.of(2022,5,15,0,0));
        criteria.setEndTo(LocalDateTime.of(2022,5,17,0,0));
        events = eventService.searchEvents(criteria);
        assertEquals(1, IterableUtils.size(events));
    }



    @Test
    public void searchEventsComplex(){
        //Svi besplatni eventi u Zagrebu
        SearchCriteria criteria = new SearchCriteria();
        List<City> cities= new LinkedList<>();
        cities.add(cityService.getCity(4L));
        criteria.setCities(cities);
        criteria.setFree(true);
        Iterable<Event> events = eventService.searchEvents(criteria);
        assertEquals(1, IterableUtils.size(events));

        //Svi besplatni eventi na "D"
        criteria = new SearchCriteria();
        criteria.setFree(true);
        criteria.setName("D");
        events = eventService.searchEvents(criteria);
        assertEquals(2, IterableUtils.size(events));
    }

    @Test
    public void saveEvent() {
        assertThrows(NoSuchElementException.class, () -> eventService.getEventById(12L));
        Event e = new Event();
        e.setName("Testni Dogadjaj");
        e.setFree(true);
        e.setTimeFrom(LocalDateTime.now());
        e.setTimeTo(LocalDateTime.now());
        e.setCity(cityService.getCity(1L));
        eventService.saveEvent(e);
        Event event = eventService.getEventById(12L);
        assertFalse(event == null);
    }

    @Test
    public void deleteEvent() {
        Iterable<Event> allEvents = eventService.getAllEvents();
        assertNotNull(allEvents);
        List<Event> events = new LinkedList<>();
        allEvents.forEach(event -> events.add(event));
        assertEquals(events.size(), 11);
        Event lastEvent = events.get(events.size() - 1);
        eventService.deleteEvent(lastEvent);
        assertThrows(NoSuchElementException.class, () -> eventService.getEventById(lastEvent.getId()));
    }
}