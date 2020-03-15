package uk.ac.man.cs.eventlite.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private VenueRepository venueRepository;
	
	@Override
	public long count() {
		return eventRepository.count();
	}

	@Override
	public Iterable<Event> findAll() {
		
		return eventRepository.findAll();
	}
	
	@Override
	public Iterable<Venue> findAllVenues() {
		return venueRepository.findAll();
	}
	
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
		@Override
	public Optional<Event> findById(long id) {
		return eventRepository.findById(id);
	}
	
	public void deleteById(long id) {
		eventRepository.deleteById(id);
	}
	
	public Iterable<Event> sort()
	{
		return eventRepository.findAllByOrderByDateAscTimeAsc();
	}
	
	public Iterable<Event> listEventByName(String name){
		return eventRepository.findByNameContaining(name);
	}
}

