package uk.ac.man.cs.eventlite.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import uk.ac.man.cs.eventlite.EventLite;
import uk.ac.man.cs.eventlite.entities.*;


@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EventLite.class)
@DirtiesContext
@ActiveProfiles("test")
public class EventServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private EventService eventService;

	@Test
	public void findAll() {
		List<Event> events = (List<Event>) eventService.findAll();
		long count = eventService.count();

		assertThat("All events should be here.", count, equalTo((long) events.size()));
	}
	
	@Test
	public void saveEvent() {
		
		long count = eventService.count();

		Event event = new Event();
		eventService.save(event);

		assertThat("Count should be +1 because we added an event.", (count + 1), equalTo(eventService.count()));
		assertThat("There should be non-zero event_id.", 0L, not(equalTo(event.getId())));
	}
	
	@Test
	public void searchEvent() {
		
		String eventName = "Iron Bru";
		
		Event event = new Event();
		event.setName(eventName);
		event.setId(9);
		eventService.save(event);
		long eventID = event.getId();
		
		Iterable<Event> searchResult = eventService.listEventByName(eventName);
		
		Event groupH = searchResult.iterator().next();
				
		assertThat("The event name we found using our search function and the name of the event should be the same", groupH.getName(), equalTo(eventName));
		
	}
}
