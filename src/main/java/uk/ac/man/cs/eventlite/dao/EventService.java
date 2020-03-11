package uk.ac.man.cs.eventlite.dao;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventService {

	public long count();

	public Iterable<Event> findAll();
	
	public Event save(Event event);
	
	public Iterable<Event> sort();
	
	public Iterable<Event> listEventByName(String name);

} 
