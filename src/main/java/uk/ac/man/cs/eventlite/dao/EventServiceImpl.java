package uk.ac.man.cs.eventlite.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import uk.ac.man.cs.eventlite.entities.Event;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	private VenueRepository venueRepository;

	@Override
	public long count() {
		return eventRepository.count();
	}

	@Override
	public Iterable<Event> findAll() {
		
		return eventRepository.findAll();
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
		return eventRepository.findByNameContainingOrderByDateAscNameAsc(name);
		}

	@Override
	public Iterable<Event> listEventsRelatedToAVenue(String location) {
		List<Event> events = new ArrayList<Event>();
		for (Event event : findAll())
			if (event.getVenue().getName().toLowerCase().contains(location.toLowerCase()))
			{
				events.add(event);
			}    	
		return events;
	}
	
}

