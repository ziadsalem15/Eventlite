package uk.ac.man.cs.eventlite.dao;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.man.cs.eventlite.entities.Event;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public long count() {
		return eventRepository.count();
	}

	@Override
	public Iterable<Event> findAll() {
		
		return eventRepository.findAll();
	}
	
	@Override
	public Optional<Event> findById(long id) {
		return eventRepository.findById(id);
	}
	
	@Override
	public Event findOne(long id) {
		return findById(id).orElse(null);
	}
	
	public Event save(Event event) {
		return eventRepository.save(event);
	}
}
