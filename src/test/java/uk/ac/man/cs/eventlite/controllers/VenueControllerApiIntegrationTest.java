package uk.ac.man.cs.eventlite.controllers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import uk.ac.man.cs.eventlite.EventLite;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EventLite.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class VenueControllerApiIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	private HttpEntity<String> httpEntity;

	@Autowired
	private TestRestTemplate template;

	@BeforeEach
	public void setup() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		httpEntity = new HttpEntity<String>(headers);
	}

	@Test
	public void testVenue() {
		
		String expectedResult = "\"{\n" +
		"\"name\" : \"Kilburn G23\",\n"+ 
		"\"address\" : \"Kilburn Building, Oxford Rd, Manchester, M13 9PL\",\n" +
		"\"capacity\" : 80,\",\n" +
		"\"longitude\" : -2.237172,\n" +
		"\"latitude\" : 53.470184,\n" +
		"\"_links\" : {\n" +
			"\"self\" : {\n" +
				"\"href\" : \"http://localhost:8080/api/venues/1\"\n" +
			"},\n" +
			"\"venue\" : {\n" +
				"\"href\" : \"http://localhost:8080/api/venues/1\"\n" +
			"},\n" +
			"\"events\" : {\n" +
				"\"href\" : \"http://localhost:8080/api/venues/1/events\"\n" +
			"},\n" +
			"\"next3events\": {\n" +
				"\"href\": \" http://localhost:8080/api/venues/1/next3events\"\n" +
			"}\n" +
		"}\n" +
		"}\"\n";
	
		
		ResponseEntity<String> output = template.exchange("http://localhost:8080/api/venues/1", HttpMethod.GET, httpEntity, String.class);
		assertThat(output.getBody(), containsString(expectedResult));
		
		

	}
	
	
	
	
	
}
