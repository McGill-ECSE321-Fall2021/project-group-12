package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Event
{

	private String name;
	private boolean isPrivate;
	private boolean isAccepted;
	private TimeSlot timeSlot;
	private User user;
	private LibraryApplicationSystem libraryApplicationSystem;
	private Long eventId;
	
	public boolean setEventId(Long id) {
		eventId = id;
		return true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getEventId() {
		return eventId;
	}
	
	public boolean setName(String aName) {
		name = aName;
		return true;
	}

	public boolean setIsPrivate(boolean aIsPrivate) {
		isPrivate = aIsPrivate;
		return true;
	}

	public boolean setIsAccepted(boolean aIsAccepted) {
		isAccepted = aIsAccepted;
		return true;
	}
	
	public String getName() {
		return name;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}
	
	public boolean getIsAccepted() {
		return isAccepted;
	}
	
	@OneToOne
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	
	@OneToOne
	public User getUser()
	{
		return user;
	}

	@ManyToOne(cascade= {CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem()
	{
		return libraryApplicationSystem;
	}

	public boolean setTimeSlot(TimeSlot aNewTimeSlot)
	{
		if (aNewTimeSlot == null)
		{
			return false;
		}
		timeSlot = aNewTimeSlot;
		return true;
	}
	
	
	public boolean setUser(User aUser)
	{
		if (aUser == null)
		{
			return false;
		}

		user = aUser;
		return true;
	}

	public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem)
	{
		
		if (aLibraryApplicationSystem == null)
		{
			return false;
		}
		LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
		libraryApplicationSystem = aLibraryApplicationSystem;
		if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem))
		{
			existingLibraryApplicationSystem.removeEvent(this);
		}
		libraryApplicationSystem.addEvent(this);
		
		return true;
	}

	public void delete()
	{
		timeSlot = null;
		this.user = null;
	}


	public String toString()
	{
		return super.toString() + "["+
				"name" + ":" + getName()+ "," +
				"isPrivate" + ":" + getIsPrivate()+  "," + "isAccepted" + ":" + getIsAccepted()+"]" + System.getProperties().getProperty("line.separator") +
				"  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
	}
}
