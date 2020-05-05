package uk.ac.man.cs.eventlite.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@NotNull
	@GeneratedValue
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date may not be null")
	@Future
	private LocalDate date;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	
	

	@NotEmpty(message = "Name may not be empty")
	@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters long")
	private String name;
	
	@ManyToOne
	@NotNull(message = "Venue may not be null")
	private Venue venue;
	
	public Event(long id, LocalDate date, LocalTime time, String name, Venue venue)
	{
		this.id = id;
		this.date = date;
		this.time = time;
		this.name = name;
		this.venue = venue;
	}
	
	public Event() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
}
