package uk.ac.man.cs.eventlite.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.ac.man.cs.eventlite.entities.Venue;

@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	private VenueRepository venueRepository;

	@Override
	public long count() {
		return venueRepository.count();
	}

	@Override
	public Iterable<Venue> findAll() {
		return venueRepository.findAll();
	}
	
	public Venue save(Venue venue) {
		return venueRepository.save(venue);
	}
	
	public Iterable<Venue> sort() {
		return venueRepository.findAll();
	}

	public Iterable<Venue> listVenueByName(String name){
		return venueRepository.findByNameContainingOrderByNameAsc(name);
	}
	
	@Override
	public List<Venue> findTopUsedVenues() 
	{
		Pageable venuesRequest = PageRequest.of(0, 3);
		return venueRepository.findTopUsedVenues(venuesRequest);
		
	}

	
	
	
}