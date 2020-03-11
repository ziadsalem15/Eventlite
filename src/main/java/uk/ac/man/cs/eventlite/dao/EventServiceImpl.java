package uk.ac.man.cs.eventlite.dao;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
=======
>>>>>>> refs/remotes/origin/ViewInSpeparatePage

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
		
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
		@Override
	public Optional<Event> findById(long id) {
		return eventRepository.findById(id);
	}
<<<<<<< Upstream, based on branch 'master' of ssh://gitlab@gitlab.cs.man.ac.uk:22222/comp23412_2019/eventlite_H13.git
	
	public void deleteById(long id) {
		eventRepository.deleteById(id);
	}
	
	public Iterable<Event> sort()
	{
		return eventRepository.findAllByOrderByDateAscTimeAsc();
	}
	
	public Iterable<Event> listEventByName(String name){
		return eventRepository.findByNameContaining(name);
=======
		
	public Event save(Event event) {
		return eventRepository.save(event);
>>>>>>> b6e3fbc Delete unused methods
	}
}

