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

		if (eventService.count() > 0) {
			log.info("Database already populated. Skipping data initialization.");
			return;
			
		} else 
		{
			eventService.save(new Event(1, LocalDate.of(2020, 05, 11), LocalTime.of(15, 00), "COMP23412 Showcase, group G", new Venue (1, "Tootill 1", 50)));
			eventService.save(new Event(2, LocalDate.of(2020, 05, 05), LocalTime.of(10, 00), "COMP23412 Showcase, group H", new Venue (1, "Tootill 1", 50)));
			eventService.save(new Event(3, LocalDate.of(2020, 05, 07), LocalTime.of(11, 00), "COMP23412 Showcase, group F", new Venue (1, "Tootill 1", 50)));
			
		}
		

		if (venueService.count() > 0) {
			log.info("Database already populated. Skipping data initialization.");
			return;
			
		} else 
		{
			venueService.save(new Venue(1, "Kilburn, G23", 80));
			
		}

		// Build and save initial models here.

	}
}
