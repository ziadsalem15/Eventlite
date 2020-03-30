package uk.ac.man.cs.eventlite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.man.cs.eventlite.dao.VenueService;

@Controller
@RequestMapping(value = "/venues", produces = { MediaType.TEXT_HTML_VALUE })
public class VenueController {

	@Autowired
	private VenueService venueService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllVenues(Model model, @RequestParam (value="venuesearch", required= false) String search)  {
		
		if (search == null) {
			model.addAttribute("venues", venueService.sort());
			
			return "venues/index";
		} else {
			model.addAttribute("venues", venueService.listVenueByName(search));
			return "venues/index";
		}

	}
	/**
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String event(@PathVariable("id") long id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

		Optional<Event> venue = venueService.findById(id);
		model.addAttribute("venue", venue);

		return "venues/show";
	}**/

}
