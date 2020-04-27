package uk.ac.man.cs.eventlite.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;

@Controller
@RequestMapping(value = "/events", produces = { MediaType.TEXT_HTML_VALUE })
public class EventsController {

	private static final String CONSUMER_KEY = "VPqSlzWEL9sdHnCYxbhCqsjrd";
	private static final String CONSUMER_SECRET = "VdgY81RyxNL0BjD9Ciqu3mF6CIfd0yP7ggeELc0XdBDb931sp3";
	private static final String ACCESS_KEY = "1252219375433781248-aLIfXPRbvlRVEOSRlGdc3CkYJQLtTs";
	private static final String ACCESS_SECRET = "bOji6K257zHlg0zHSEp90WW8G6ziD34JUsHxiOAWU8Wa5";
	
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
	
	@RequestMapping(value = "/tweet/{id}", method = RequestMethod.POST)
	public String tweet(Model model, HttpServletRequest request, @PathVariable("id") long id) {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true);
		config.setOAuthConsumerKey(CONSUMER_KEY);
		config.setOAuthConsumerSecret(CONSUMER_SECRET);
		config.setOAuthAccessToken(ACCESS_KEY);
		config.setOAuthAccessTokenSecret(ACCESS_SECRET);
		
		TwitterFactory factory = new TwitterFactory(config.build());
		Twitter twitter = factory.getInstance();
		try {
			Status status = twitter.updateStatus(request.getParameter("tweetMsg") + " http://localhost:8080/events/" + id);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "events/show";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getEvent(Model model, @PathVariable("id") long id) {
		Event event = eventService.findById(id).get();
		model.addAttribute("updateEvent", event);
		model.addAttribute("venues", venueService.findAll());
		return "events/update";
	} 
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateTheEvent(@PathVariable("id") long id,
			Model model, @RequestBody @Valid @ModelAttribute Event event, RedirectAttributes redirectAttrs, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("updateEvent", event);
			return "events/update";
		}

		eventService.save(event);
		redirectAttrs.addFlashAttribute("ok_message", "The event is updated");
		model.addAttribute("events", eventService.findAll());
		return "redirect:/events";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String event(@PathVariable("id") long id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

		Event event = eventService.findById(id).get();
		model.addAttribute("event", event);
		return "events/show";
	}	
	
	@RequestMapping(value = "/newevent", method = RequestMethod.GET)
	public String newEvent(Model model, HttpServletRequest request ) {
		if(!model.containsAttribute("event")) {
			model.addAttribute("event", new Event());
		}

		return "events/newevent";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addEvent(@RequestBody @Valid @ModelAttribute Event event,
			BindingResult errors, Model model, RedirectAttributes redirectAttrs) {

		if (errors.hasErrors()) {
			model.addAttribute("event", event);
			//model.addAttribute("venues", venueService.findAll());
			return "events/newevent";
		}

		eventService.save(event);
		//redirectAttrs.addFlashAttribute("ok_message", "New event added.");
		//model.addAttribute("events", eventService.findAll());
		return "redirect:/events";
	}

	
	
	
	
	
}
