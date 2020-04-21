package uk.ac.man.cs.eventlite.config.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Component
@Profile({ "default", "test" })
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;
	
	private final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiZ3JvdXAtaDEzIiwiYSI6ImNrOGZ4dXdxbTAwaTgzZ3A3ZDg5NjR0a2IifQ.zJHpLl4QfI0v-AR9mbSGcw";

	/*
	 * 	Takes venue address as string and returns latitude and longitude as a double array with two values
	 * 
	 * 	@param address the address of the venue to return the latitude and longitude of
	 * 	@return the latitude and longitude respectively as a double array of size 2
	 */
	private double[] getGeocoding(String address) {
		MapboxGeocoding geocoding = MapboxGeocoding.builder().accessToken(MAPBOX_ACCESS_TOKEN).query(address).build();
		
		double[] latLongPair = new double[2];
		geocoding.enqueueCall(new Callback<GeocodingResponse>() {

			@Override
			public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
				List<CarmenFeature> results = response.body().features();
				
				if(results.size() > 0) {
					latLongPair[0] = results.get(0).center().latitude();
					latLongPair[1] = results.get(0).center().longitude();
				}
			}

			@Override
			public void onFailure(Call<GeocodingResponse> call, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}
			
		});
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return latLongPair;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (eventService.count() > 0 || venueService.count() > 0 ) {
			log.info("Database already populated. Skipping data initialization.");
			return;
			
		} else 
		{
			Venue venue1 = new Venue();
			venue1.setName("Kilburn G23");
			venue1.setAddress("Kilburn Building, Oxford Rd, Manchester, M13 9PL");
			venue1.setCapacity(80);
			double[] latLongPair = this.getGeocoding(venue1.getAddress());
			venue1.setLatitude(latLongPair[0]);
			venue1.setLongitude(latLongPair[1]);
			venueService.save(venue1);
			
			/*
			 * Venue 2 and venue 3 are only for demonstration of the event map
			 */
			Venue venue2 = new Venue();
			venue2.setName("University Place");
			venue2.setAddress("176 Oxford Rd, Manchester, M13 9PL");
			venue2.setCapacity(120);
			latLongPair = this.getGeocoding(venue2.getAddress());
			venue2.setLatitude(latLongPair[0]);
			venue2.setLongitude(latLongPair[1]);
			venueService.save(venue2);

			Venue venue3 = new Venue();
			venue3.setName("Stopford Building");
			venue3.setAddress("99 Oxford Rd, Manchester, M13 9PG");
			venue3.setCapacity(75);
			latLongPair = this.getGeocoding(venue3.getAddress());
			venue3.setLatitude(latLongPair[0]);
			venue3.setLongitude(latLongPair[1]);
			venueService.save(venue3);
			
			Event event1 = new Event();
			LocalDate date1 = LocalDate.of(2020, 05, 11);
			LocalTime time1 = LocalTime.of(15, 00);
			event1.setName("COMP23412 Showcase, group G");
			event1.setDate(date1);
			event1.setTime(time1);
			event1.setVenue(venue1);
			eventService.save(event1);
			
			Event event2 = new Event();
			LocalTime time2 = LocalTime.of(10, 00);
			LocalTime time3 = LocalTime.of(11, 00);
			event2.setDate(date1);
			event2.setTime(time2);
			event2.setVenue(venue2);
			event2.setName("COMP23412 Showcase, group H");
			eventService.save(event2);
			
			Event event3 = new Event();
			event3.setDate(date1);
			event3.setTime(time3);
			event3.setVenue(venue3);
			event3.setName("COMP23412 Showcase, group F");
			eventService.save(event3);
			
			Event event4 = new Event();
			event4.setDate(date1);
			event4.setTime(time3);
			event4.setVenue(venue2);
			event4.setName("COMP23412 Showcase, group H");
			eventService.save(event4);
			
			Event event5 = new Event();
			event5.setDate(date1);
			event5.setTime(time3);
			event5.setVenue(venue3);
			event5.setName("COMP23412 Showcase, group H");
			eventService.save(event5);
		}
		// Build and save initial models here.

	}
}
