package ca.mcgill.ecse321.library.model;


import java.util.*;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
public class LibraryApplicationSystem {

	private Long applicationId;
	private List<User> users;
	private List<Item> items;
	private List<TimeSlot> timeSlots;
	private List<LibraryHour> libraryHours;
	private List<Event> events;
	private List<Creator> creators;
	private List<Reservation> reservations;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getApplicationId() {
		return applicationId;
	}
	
	public boolean setApplicationId(Long id) {
		applicationId = id;
		return true;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<User> getUsers() {
		return users;
	}
	
	public boolean setUsers(List<User> newUsers) {
		users = newUsers;
		return true;
	}

	public int numberOfUsers() {
		return users.size();
	}

	public boolean hasUsers() {
		return users.size() > 0;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<Item> getItems() {
		return items;
	}
	
	public boolean setItems(List<Item> newItems) {
		items = newItems;
		return true;
	}

	public int numberOfItems() {
		return items.size();
	}

	public boolean hasItems() {
		return items.size() > 0;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	public boolean setTimeSlots(List<TimeSlot> newTimeSlots) {
		timeSlots = newTimeSlots;
		return true;
	}

	public int numberOfTimeSlots() {
		return timeSlots.size();
	}

	public boolean hasTimeSlots() {
		return timeSlots.size() > 0;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<LibraryHour> getLibraryHours() {
		return libraryHours;
	}

	public boolean setLibraryHours(List<LibraryHour> newLibraryHours) {
		libraryHours = newLibraryHours;
		return true;
	}

	public int numberOfLibraryHours() {
		return libraryHours.size();
	}

	public boolean hasLibraryHours() {
		return libraryHours.size() > 0;
	}
	
	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<Event> getEvents() {
		return events;
	}
	
	public boolean setEvents(List<Event> newEvents) {
		events = newEvents;
		return true;
	}

	public int numberOfEvents() {
		return events.size();
	}

	public boolean hasEvents() {
		return events.size() > 0;
	}
	
	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<Reservation> getReservations() {
	    return reservations;
	}
	
	public boolean setReservations(List<Reservation> newReservations) {

		reservations = newReservations;
		return true;
	}
	
	public int numberOfReservations() {
	    return reservations.size();
	  }

	public boolean hasReservations() {
	    return reservations.size() > 0;
	}


	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<Creator> getCreators() {
		return creators;
	}
	
	public boolean setCreators(List<Creator> newCreators) {
		creators = newCreators;
		return true;
	}

	public int numberOfCreators() {
		return creators.size();
	}

	public boolean hasCreators() {
		return creators.size() > 0;
	}
	
	public boolean addUser(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		if (users.contains(user)) { 
			return false;
		}
		users.add(user);
		return true;
	}

	public boolean removeUser(User user) {
		if (users.contains(user)) {
			user.setLibraryApplicationSystem(null);
			users.remove(user);
			return true;
		}
		return false;
	}

	public boolean addItem(Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		if (items.contains(item)) { 
			return false;
		}
		items.add(item);
		return true;
	}

	public boolean removeItem(Item item) {
		if (items.contains(item)) {
			item.setLibraryApplicationSystem(null);
			items.remove(item);
			return true;
		}
		return false;
	}

	public TimeSlot addTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setEndTime(endTime);
		return timeSlot;
	}

	public boolean addTimeSlot(TimeSlot timeSlot) {
		if (timeSlots == null) {
			timeSlots = new ArrayList<TimeSlot>();
		}
		if (timeSlots.contains(timeSlot)) { 
			return false;
		}
		timeSlots.add(timeSlot);
		return true;
	}

	public boolean removeTimeSlot(TimeSlot timeSlot) {
		if (timeSlots.contains(timeSlot)) {
			timeSlot.setLibraryApplicationSystem(null);
			timeSlots.remove(timeSlot);
			return true;
		}
		return false;
	}

	public LibraryHour addLibraryHour(Time startTime, Time endTime, LibraryHour.Day day) {
		LibraryHour libraryHour = new LibraryHour();
		libraryHour.setStartTime(startTime);
		libraryHour.setEndTime(endTime);
		libraryHour.setDay(day);
		return libraryHour;
	}

	public boolean addLibraryHour(LibraryHour libraryHour) {
		if (libraryHours == null) {
			libraryHours = new ArrayList<LibraryHour>();
		}
		if (libraryHours.contains(libraryHour)) { 
			return false;
		}
		libraryHours.add(libraryHour);
		return true;
	}

	public boolean removeLibraryHour(LibraryHour libraryHour) {
		if (libraryHours.contains(libraryHour)) {
			libraryHour.setLibraryApplicationSystem(null);
			libraryHours.remove(libraryHour);
			return true;
		}
		return false;
	}

	public Event addEvent(String name, boolean isPrivate, TimeSlot timeSlot, User user) {
		Event event = new Event();
		event.setName(name);
		event.setIsPrivate(isPrivate);
		event.setTimeSlot(timeSlot);
		event.setUser(user);
		return event;
	}

	public boolean addEvent(Event event) {
		if (events == null) {
			events = new ArrayList<Event>();
		}
		if (events.contains(event)) { 
			return false; 
		}
		events.add(event);
		return true;
	}

	public boolean removeEvent(Event event) {
		if (events.contains(event)) {
			event.setLibraryApplicationSystem(null);
			events.remove(event);
			return true;
		}
		return false;
	}
	
	public Reservation addReservation(User user, TimeSlot timeSlot) {
		Reservation reservation = new Reservation();
		reservation.setUser(user);
		reservation.setTimeSlot(timeSlot);
		return reservation;
	}

	public boolean addReservation(Reservation reservation) {
		if (reservations == null) {
			reservations = new ArrayList<Reservation>();
		}
	    if (reservations.contains(reservation)) { 
	    	return false;
	    }
	    reservations.add(reservation);
	    return true;
	}

	public boolean removeReservation(Reservation reservation) {
	    if (reservations.contains(reservation)) {
	    	reservation.setLibraryApplicationSystem(null);
	    	reservations.remove(reservation);
	    	return true;
	    }
	    return false;
	}


	public Creator addCreator(Creator.CreatorType creatorType) {
		Creator creator = new Creator();
		creator.setCreatorType(creatorType);
		creator.setLibraryApplicationSystem(this);
		return creator;
	}

	public boolean addCreator(Creator creator) {
		if (creators == null) {
			creators = new ArrayList<Creator>();
		}
		if (creators.contains(creator)) { 
			return false;
		}
		creators.add(creator);
		return true;
	}

	public boolean removeCreator(Creator creator) {
		if (creators.contains(creator)) {
			creator.setLibraryApplicationSystem(null);
			creators.remove(creator);
			return true;
		}
		return false;
	}

	public void delete() {
		while (users.size() > 0) {
			User user = users.get(users.size() - 1);
			user.delete();
			users.remove(user);
		}

		while (items.size() > 0) {
			Item item = items.get(items.size() - 1);
			item.delete();
			items.remove(item);
		}

		while (timeSlots.size() > 0) {
			TimeSlot timeSlot = timeSlots.get(timeSlots.size() - 1);
			timeSlot.delete();
			timeSlots.remove(timeSlot);
		}

		while (libraryHours.size() > 0) {
			LibraryHour libraryHour = libraryHours.get(libraryHours.size() - 1);
			libraryHour.delete();
			libraryHours.remove(libraryHour);
		}

		while (events.size() > 0) {
			Event event = events.get(events.size() - 1);
			event.delete();
			events.remove(event);
		}
		while (creators.size() > 0) {
			Creator creator = creators.get(creators.size() - 1);
			creator.delete();
			creators.remove(creator);
		}
		
		while (reservations.size() > 0) {
			Reservation reservation = reservations.get(reservations.size() - 1);
			reservation.delete();
			reservations.remove(reservation);
		}
	}
	
	public String toString() {
		return "LibraryApplicationSystem " + this.applicationId;
	}

}
