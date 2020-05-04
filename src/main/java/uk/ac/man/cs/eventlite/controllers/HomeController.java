package uk.ac.man.cs.eventlite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;

@Controller
@RequestMapping(value = "/", produces = { MediaType.TEXT_HTML_VALUE })
public class HomeController {
	
	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String findUpcomingEventsAndMostUsedVenues(Model model) {

		model.addAttribute("events", eventService.findUpcoming3Events());
		model.addAttribute("venues", venueService.findTopUsedVenues());
		
		
		return "home/index";
	}
}
