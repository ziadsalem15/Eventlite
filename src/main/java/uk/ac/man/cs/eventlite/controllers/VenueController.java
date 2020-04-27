package uk.ac.man.cs.eventlite.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Venue;

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
	
	@RequestMapping(value = "/newvenue", method = RequestMethod.GET)
	public String newVenue(Model model, HttpServletRequest request ) {
		if(!model.containsAttribute("venue")) {
			model.addAttribute("venue", new Venue());
		}

		return "venues/newvenue";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addVenue(@RequestBody @Valid @ModelAttribute Venue venue,
			BindingResult errors, Model model, RedirectAttributes redirectAttrs) {

		if (errors.hasErrors()) {
			model.addAttribute("venue", venue);
			//model.addAttribute("venues", venueService.findAll());
			return "venues/newvenue";
		}

		venueService.save(venue);
		//redirectAttrs.addFlashAttribute("ok_message", "New event added.");
		//model.addAttribute("events", eventService.findAll());
		return "redirect:/venues";
	}

	
	
	
	

}
