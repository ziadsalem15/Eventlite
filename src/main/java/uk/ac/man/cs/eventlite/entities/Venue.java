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
	
	//private String address;

	private int capacity;
	
	@OneToMany(mappedBy="venue")
	private List<Event> events = new ArrayList<Event>();
	
	public Venue(long id, String name, String address, int capacity, Venue venue)
	{
		this.id = id;
		this.name = name;
		//this.address = address;
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
	
	//public String getAddress() {
		//return address;
	//}
	
	//public void setAddress(String address) {
		//this.address = address;
	//}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
