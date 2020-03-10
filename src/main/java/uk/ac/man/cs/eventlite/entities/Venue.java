package uk.ac.man.cs.eventlite.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "venues")
	
public class Venue {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	private int capacity;
	
	@OneToMany(mappedBy="venue")
	private List<Event> events = new ArrayList<Event>();
	
	public Venue(long id, String name, int capacity, Venue venue)
	{
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}
	
	public Venue() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
