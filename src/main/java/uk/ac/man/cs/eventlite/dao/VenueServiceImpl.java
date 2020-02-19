package uk.ac.man.cs.eventlite.dao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import uk.ac.man.cs.eventlite.entities.Venue;

@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	private VenueRepository venuerepository;


	@Override
	public long count() {
		return venuerepository.count();
	}

	@Override
	public Iterable<Venue> findAll() {
		return venuerepository.findAll();
	}
	
	public Venue save(Venue venue) {
		return venuerepository.save(venue);
	}

}
