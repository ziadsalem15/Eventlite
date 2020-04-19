package uk.ac.man.cs.eventlite.dao;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import uk.ac.man.cs.eventlite.entities.Event;

@SuppressWarnings("deprecation")

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
	public List<Event> findUpcoming3Events() {
		LocalDate date = LocalDate.now();
			Pageable limit = new PageRequest(0, 3);
			return eventRepository.findAllByDateAfterOrderByDate(date, limit);
	} // findUpcomingEvents
	

}

