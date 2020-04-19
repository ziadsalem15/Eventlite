package uk.ac.man.cs.eventlite.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventRepository extends CrudRepository <Event, Long> {
	public Iterable<Event> findAllByOrderByDateAscTimeAsc();
	public Iterable<Event> findByNameContainingOrderByDateAscNameAsc(String name);
	
	public List<Event> findAllByDateAfterOrderByDate(LocalDate date, Pageable limit);
	public List<Event> findAllByDateAfterOrderByDate(LocalDate date);


}
