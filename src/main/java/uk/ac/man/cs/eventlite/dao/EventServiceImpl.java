package uk.ac.man.cs.eventlite.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public void deleteById(long id) {
		eventRepository.deleteById(id);
	}
	
	public Iterable<Event> sort()
	{
		return eventRepository.findAllByOrderByDateAscTimeAsc();
	}
}

