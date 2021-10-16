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
	private Library library;
	private List<Event> events;
	private List<Creator> creators;

	public LibraryApplicationSystem() {
		users = new ArrayList<User>();
		items = new ArrayList<Item>();
		timeSlots = new ArrayList<TimeSlot>();
		libraryHours = new ArrayList<LibraryHour>();
		library = new Library(this);
		events = new ArrayList<Event>();
		creators = new ArrayList<Creator>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getApplicationId() {
		return applicationId;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<User> getUsers() {
		return users;
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

	public int numberOfLibraryHours() {
		return libraryHours.size();
	}

	public boolean hasLibraryHours() {
		return libraryHours.size() > 0;
	}

	@OneToOne(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public Library getLibrary() {
		return library;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<Event> getEvents() {
		return events;
	}

	public int numberOfEvents() {
		return events.size();
	}

	public boolean hasEvents() {
		return events.size() > 0;
	}

	@OneToMany(mappedBy="libraryApplicationSystem", cascade={CascadeType.ALL})
	public List<Creator> getCreators() {
		return creators;
	}

	public int numberOfCreators() {
		return creators.size();
	}

	public boolean hasCreators() {
		return creators.size() > 0;
	}
	
	public boolean addUser(User user) {
		if (users.contains(user)) { 
			return false;
		}
		user.setLibraryApplicationSystem(this);
		users.add(user);
		return true;
	}

	public boolean removeUser(User user) {
		if (users.contains(user)) {
			users.remove(user);
			return true;
		}
		return false;
	}

	public boolean addItem(Item item) {
		if (items.contains(item)) { 
			return false;
		}
		item.setLibraryApplicationSystem(this);
		items.add(item);
		return true;
	}

	public boolean removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
			return true;
		}
		return false;
	}

	public TimeSlot addTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate) {
		return new TimeSlot(startTime, endTime, startDate, endDate, this);
	}

	public boolean addTimeSlot(TimeSlot timeSlot) {
		if (timeSlots.contains(timeSlot)) { 
			return false;
		}
		timeSlot.setLibraryApplicationSystem(this);
		timeSlots.add(timeSlot);
		return true;
	}

	public boolean removeTimeSlot(TimeSlot timeSlot) {
		if (timeSlots.contains(timeSlot)) {
			timeSlots.remove(timeSlot);
			return true;
		}
		return false;
	}

	public LibraryHour addLibraryHour(Time startTime, Time endTime, LibraryHour.Day day) {
		return new LibraryHour(startTime, endTime, day, this);
	}

	public boolean addLibraryHour(LibraryHour libraryHour) {
		if (libraryHours.contains(libraryHour)) { 
			return false;
		}
		libraryHour.setLibraryApplicationSystem(this);
		libraryHours.add(libraryHour);
		return true;
	}

	public boolean removeLibraryHour(LibraryHour libraryHour) {
		if (libraryHours.contains(libraryHour)) {
			libraryHours.remove(libraryHour);
			return true;
		}
		return false;
	}

	public Event addEvent(String name, boolean isPrivate, TimeSlot timeSlot, User user) {
		return new Event(name, isPrivate, timeSlot, user, this);
	}

	public boolean addEvent(Event event) {
		if (events.contains(event)) { 
			return false; 
		}
		event.setLibraryApplicationSystem(this);
		events.add(event);
		return true;
	}

	public boolean removeEvent(Event event) {
		if (events.contains(event)) {
			events.remove(event);
			return true;
		}
		return false;
	}

	public Creator addCreator(String firstName, String lastName, Creator.CreatorType creatorType) {
		return new Creator(firstName, lastName, creatorType, this);
	}

	public boolean addCreator(Creator creator) {
		if (creators.contains(creator)) { 
			return false;
		}
		creator.setLibraryApplicationSystem(this);
		creators.add(creator);
		return true;
	}

	public boolean removeCreator(Creator creator) {
		if (creators.contains(creator)) {
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

		Library existingLibrary = library;
		library = null;
		if (existingLibrary != null) {
			existingLibrary.delete();
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
	}
	
	public String toString() {
		return "LibraryApplicationSystem " + this.applicationId;
	}

}