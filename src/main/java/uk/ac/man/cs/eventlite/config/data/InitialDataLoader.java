package uk.ac.man.cs.eventlite.config.data;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Component
@Profile({ "default", "test" })
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (eventService.count() > 0 || venueService.count() > 0 ) {
			log.info("Database already populated. Skipping data initialization.");
			return;
			
		} else 
		{
			
			Venue venue1 = new Venue();
			venue1.setName("Kilburn G23");
			venue1.setAddress("Oxford Rd, Manchester, M13 9PL");
			venue1.setCapacity(80);
			venueService.save(venue1);
			
			Venue venue2 = new Venue();
			venue2.setName("Kilburn LF31");
			
			venue2.setAddress("Oxford Rd, Manchester, M13 9PJ");
			venue2.setCapacity(80);
			venueService.save(venue2);
			
			Venue venue3 = new Venue();
			venue3.setName("Kilburn Tootil 1");
			venue3.setAddress("Oxford Rd, Manchester, M13 9PQ");
			venue3.setCapacity(80);
			venueService.save(venue3);
			
			Event event1 = new Event();
			LocalDate date1 = LocalDate.of(2020, 05, 11);
			LocalTime time1 = LocalTime.of(15, 00);
			event1.setName("COMP23412 Showcase, group G");
			event1.setDate(date1);
			event1.setTime(time1);
			event1.setVenue(venue1);
			eventService.save(event1);
			
			Event event2 = new Event();
			LocalTime time2 = LocalTime.of(10, 00);
			LocalTime time3 = LocalTime.of(11, 00);
			event2.setDate(date1);
			event2.setTime(time2);
			event2.setVenue(venue1);
			event2.setName("COMP23412 Showcase, group H");
			eventService.save(event2);
			
			Event event3 = new Event();
			event3.setDate(date1);
			event3.setTime(time3);
			event3.setVenue(venue1);
			event3.setName("COMP23412 Showcase, group F");
			eventService.save(event3);
			
			Event event4 = new Event();
			event4.setDate(date1);
			event4.setTime(time3);
			event4.setVenue(venue2);
			event4.setName("COMP23412 Showcase, group H");
			eventService.save(event4);
			
			Event event5 = new Event();
			event5.setDate(date1);
			event5.setTime(time3);
			event5.setVenue(venue3);
			event5.setName("COMP23412 Showcase, group H");
			eventService.save(event5);
		}
		// Build and save initial models here.

	}
}
