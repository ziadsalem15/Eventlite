package uk.ac.man.cs.eventlite.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@RestController
@RequestMapping(value = "/api/venues", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
public class VenueControllerApi {

	@Autowired
	private VenueService venueService;
	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public Resources<Resource<Venue>> getAllVenues() {

		return venueToResource(venueService.findAll());
	}
	
	private Resource<Venue> venueToResource(Venue venue) {
		Link selfLink = linkTo(VenueControllerApi.class).slash(venue.getId()).withSelfRel();

		return new Resource<Venue>(venue, selfLink);
	}

	private Resources<Resource<Venue>> venueToResource(Iterable<Venue> venues) {
		Link selfLink = linkTo(methodOn(VenueControllerApi.class).getAllVenues()).withSelfRel();

		List<Resource<Venue>> resources = new ArrayList<Resource<Venue>>();
		for (Venue venue : venues) {
			resources.add(venueToResource(venue));
		}

		return new Resources<Resource<Venue>>(resources, selfLink);
	}
	/**
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Venue> deleteEvent(@PathVariable("id") long id) {
		return ResponseEntity.noContent().build();
	}**/
	
	private Resource<Event> eventToResource(Event event) {
		Link selfLink = linkTo(EventsControllerApi.class).slash(event.getId()).withSelfRel();

		return new Resource<Event>(event, selfLink);
	}
	
	
	private Resources<Resource<Event>> eventToResource(Iterable<Event> events, long id) {
		Link selfLink = linkTo(methodOn(VenueControllerApi.class).get3Events(id)).withSelfRel();

		List<Resource<Event>> resources = new ArrayList<Resource<Event>>();
		int eventno = 0;
		for (Event event : events) {
			if(eventno<3 && event.getVenue().getId() == id ) {
				resources.add(eventToResource(event));
				eventno = eventno + 1;
			}
				
		}
		return new Resources<Resource<Event>>(resources, selfLink);
	
}
	
	
	
	@RequestMapping(value = "/{id}/next3events", method=RequestMethod.GET)
	public Resources<Resource<Event>> get3Events(@PathVariable("id") long id) {
		return eventToResource(eventService.findAll(), id);
	}

}
