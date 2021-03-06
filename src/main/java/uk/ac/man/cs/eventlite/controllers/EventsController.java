package uk.ac.man.cs.eventlite.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;

import uk.ac.man.cs.eventlite.entities.*;

@Controller
@RequestMapping(value = "/events", produces = { MediaType.TEXT_HTML_VALUE })
public class EventsController {

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllEvents(Model model, @RequestParam (value = "search", required = false) String search) {
			if (search == null) {
				model.addAttribute("events", eventService.sort());
				return "events/index";
			} else {
			model.addAttribute("events", eventService.listEventByName(search));
			return "events/index";
			}
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public String deleteEvent(@PathVariable("id") long id, Model model) {
		eventService.deleteById(id);
		
		return "redirect:/events";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String event(@PathVariable("id") long id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

		Optional<Event> event = eventService.findById(id);
		model.addAttribute("event", event);

		return "events/show";
	}

}
