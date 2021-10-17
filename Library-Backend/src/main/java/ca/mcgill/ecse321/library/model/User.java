package ca.mcgill.ecse321.library.model;

import javax.persistence.*;


import java.util.*;

@MappedSuperclass
public abstract class User{

	private String firstName;
	private String lastName;
	private String address;
	private boolean isLocal;

	private Long userId;

	//User Associations
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	//any other way to find the event?
	public Event getEvent(int index) {
		Event aEvent = events.get(index);
		return aEvent;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user creator")
	public List<Event> getEvents() {
		List<Event> newEvents = Collections.unmodifiableList(events);
		return newEvents;
	}

	public int numberOfEvents() {
		int number = events.size();
		return number;
	}

	public boolean hasEvents() {
		boolean has = events.size() > 0;
		return has;
	}

	@ManyToOne(optional=false)
	public LibraryApplicationSystem getLibraryApplicationSystem()
	{
		return libraryApplicationSystem;
	}

	public boolean addEvent(Event aEvent)
	{
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

	public List<Reservation> getReservation()
	{
		List<Reservation> newReservation = Collections.unmodifiableList(reservations);
		return newReservation;
	}

	public int numberOfReservation()
	{
		int number = reservations.size();
		return number;
	}
	public boolean hasReservation()
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
	/* Code from template association_SetOneToMany */
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

	public static int minimumNumberOfReservation()
	{
		return 0;
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
	//deletion of the user
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
}
