package uk.ac.man.cs.eventlite.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import uk.ac.man.cs.eventlite.EventLite;
import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EventLite.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class HomeControllerTest {

	private MockMvc mvc;
	
	@Mock
	private EventService eventService;
	
	@Mock
	private VenueService venueService;
	

	@InjectMocks
	private HomeController homeController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}
	
	@Test
	public void findUpcomingEventsAndMostUsedVenues() throws Exception {
		Venue venue = new Venue();
		venue.setId(1);
		venue.setName("Kilburn LF31");
		venue.setCapacity(60);
		
		Event event = new Event();
		event.setId(0);
		event.setName("Event");
		event.setDate(LocalDate.now().plusDays(1));
		event.setTime(LocalTime.now().plusHours(1));
		event.setVenue(venue);
		
		when(eventService.findUpcoming3Events()).thenReturn(Collections.<Event> singletonList(event));
		when(venueService.findTopUsedVenues()).thenReturn(Collections.<Venue> singletonList(venue));
		
		mvc.perform(get("/").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(handler().methodName("findUpcomingEventsAndMostUsedVenues"))
				.andExpect(view().name("home/index"))
				.andExpect(model().attribute("events", equalTo(Collections.<Event> singletonList(event))))
				.andExpect(model().attribute("venues", equalTo(Collections.<Venue> singletonList(venue))))
				.andExpect(handler().methodName("findUpcomingEventsAndMostUsedVenues"));
	}
	
	@Test
	public void findUpcomingEventsAndMostUsedVenuesWithNo() throws Exception {
		mvc.perform(get("/").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
				.andExpect(handler().methodName("findUpcomingEventsAndMostUsedVenues"))
				.andExpect(view().name("home/index"))
				.andExpect(model().attribute("events", equalTo(Collections.<Event> emptyList())))
				.andExpect(model().attribute("venues", equalTo(Collections.<Venue> emptyList())))
				.andExpect(handler().methodName("findUpcomingEventsAndMostUsedVenues"));
	}
	
	
}


