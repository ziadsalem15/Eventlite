package uk.ac.man.cs.eventlite.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uk.ac.man.cs.eventlite.entities.Venue;

public interface VenueRepository extends CrudRepository<Venue, Long>  {

	Iterable<Venue> findByNameContainingOrderByNameAsc(String name);

	@Query("select v from Venue v, Event e where v.id = e.venue group by v.id order by count(e.id) desc")
    public List<Venue> findTopUsedVenues(Pageable venuesRequest);	
	
}
