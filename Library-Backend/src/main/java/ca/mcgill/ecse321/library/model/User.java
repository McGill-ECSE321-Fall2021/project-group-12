package ca.mcgill.ecse321.library.model;

import javax.persistence.*;


import java.util.*;

@Entity
@Table(name = "Accounts")
public abstract class User{

	private String firstName;
	private String lastName;
	private String address;
	private boolean isLocal;

	private Long userId;
	private List<Event> events;
	private List<Reservation> reservations;
	private LibraryApplicationSystem libraryApplicationSystem;

	public void setFirstName(String aFirstName) {	
		firstName = aFirstName;
	}

	public void setLastName(String aLastName) {
		lastName = aLastName;
	}

	public void setAddress(String aAddress) {
		address = aAddress;
	}

	public void setIsLocal(boolean aIsLocal) {
		isLocal = aIsLocal;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public boolean getIsLocal() {
		return isLocal;
	}
	
	public boolean setUserId(Long id) {
		userId = id;
		return true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<Event> getEvents() {
		return events;
	}

	public int numberOfEvents() {
		int number = events.size();
		return number;
	}

	public boolean hasEvents() {
		boolean has = events.size() > 0;
		return has;
	}
	
	public boolean setEvents(List<Event> newEvents) {
		events = newEvents;
		return true;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem()
	{
		return libraryApplicationSystem;
	}

	public boolean addEvent(Event aEvent)
	{
		if (events == null) {
			events = new ArrayList<Event>();
		}
		boolean wasAdded = false;
		if (events.contains(aEvent)) { return false; }
		User existingUser = aEvent.getUser();
		boolean isNewUser = existingUser != null && !this.equals(existingUser);
		if (isNewUser)
		{
			aEvent.setUser(this);
		}
		else
		{
			events.add(aEvent);
		}
		wasAdded = true;
		return wasAdded;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Reservation> getReservations()
	{
		return reservations;
	}
	
	public boolean setReservations(List<Reservation> newReservations) {
		reservations = newReservations;
		return true;
	}

	public int numberOfReservations()
	{
		int number = reservations.size();
		return number;
	}
	public boolean hasReservations()
	{
		boolean has = reservations.size() > 0;
		return has;
	}


	public boolean removeEvent(Event aEvent)
	{
		boolean wasRemoved = false;
		//Unable to remove aEvent, as it must always have a user
		if (!this.equals(aEvent.getUser()))
		{
			events.remove(aEvent);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem) {
		boolean wasSet = false;
		if (aLibraryApplicationSystem == null) {
			return wasSet;
		}
		LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
		libraryApplicationSystem = aLibraryApplicationSystem;
		if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem)) {
			existingLibraryApplicationSystem.removeUser(this);
		}
		libraryApplicationSystem.addUser(this);
		wasSet = true;
		return wasSet;
	}

	
	public Reservation addReservation(TimeSlot aTimeSlot, LibraryApplicationSystem aLibraryApplicationSystem)
	{
		Reservation reservation = new Reservation();
		reservation.setUser(this);
		reservation.setTimeSlot(aTimeSlot);
		reservation.setLibraryApplicationSystem(aLibraryApplicationSystem);
		return reservation;
	}
	public boolean addReservation(Reservation aReservation)
	{
		boolean wasAdded = false;
		if (reservations.contains(aReservation)) { return false; }
		User existingUser = aReservation.getUser();
		boolean isNewUser = existingUser != null && !this.equals(existingUser);
		if (isNewUser)
		{
			aReservation.setUser(this);
		}
		else
		{
			reservations.add(aReservation);
		}
		wasAdded = true;
		return wasAdded;
	}
	public boolean removeReservation(Reservation aReservation)
	{
		boolean wasRemoved = false;
		if (!this.equals(aReservation.getUser()))
		{
			reservations.remove(aReservation);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	
	public void delete() {
		for(int i=events.size(); i > 0; i--) {
			Event aEvent = events.get(i - 1);
			aEvent.delete();
		}
		LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
		this.libraryApplicationSystem = null;
		if(placeholderLibraryApplicationSystem != null) {
			placeholderLibraryApplicationSystem.removeUser(this);
		}
	}
	
	public String toString() {
		return "Id: " + userId + " Name: " + firstName + " " + lastName + " Address: " + address + " Local: " + isLocal;
	}
}
