package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Event
{

	private String name;
	private boolean isPrivate;
	private TimeSlot timeSlot;
	private User user;
	private LibraryApplicationSystem libraryApplicationSystem;

	
	public Event(String aName, boolean aIsPrivate, TimeSlot aTimeSlot, User aUser, LibraryApplicationSystem aLibraryApplicationSystem)
	{
		name = aName;
		isPrivate = aIsPrivate;
		boolean didAddTimeSlot = setTimeSlot(aTimeSlot);
		if (!didAddTimeSlot)
		{
			throw new RuntimeException("Unable to create event due to timeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		boolean didAddUser = setUser(aUser);
		if (!didAddUser)
		{
			throw new RuntimeException("Unable to create event due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		boolean didAddLibraryApplicationSystem = setLibraryApplicationSystem(aLibraryApplicationSystem);
		if (!didAddLibraryApplicationSystem)
		{
			throw new RuntimeException("Unable to create event due to libraryApplicationSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}


	public boolean setName(String aName)
	{
		name = aName;
	}

	public boolean setIsPrivate(boolean aIsPrivate)
	{
		isPrivate = aIsPrivate;
	}

	public String getName()
	{
		return name;
	}

	public boolean getIsPrivate()
	{
		return isPrivate;
	}
	
	public TimeSlot getTimeSlot()
	{
		return timeSlot;
	}
	
	public User getUser()
	{
		return user;
	}

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

		Event existingEvent = aNewTimeSlot.getEvent();
		if (existingEvent != null && !equals(existingEvent))
		{
			return false;
		}

		TimeSlot anOldTimeSlot = timeSlot;
		timeSlot = aNewTimeSlot;
		timeSlot.setEvent(this);

		if (anOldTimeSlot != null)
		{
			anOldTimeSlot.setEvent(null);
		}
		return true;
	}
	
	
	public boolean setUser(User aUser)
	{
		if (aUser == null)
		{
			return false;
		}

		User existingUser = user;
		user = aUser;
		if (existingUser != null && !existingUser.equals(aUser))
		{
			existingUser.removeEvent(this);
		}
		user.addEvent(this);
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
		TimeSlot existingTimeSlot = timeSlot;
		timeSlot = null;
		if (existingTimeSlot != null)
		{
			existingTimeSlot.setEvent(null);
		}
		User placeholderUser = user;
		this.user = null;
		if(placeholderUser != null)
		{
			placeholderUser.removeEvent(this);
		}
	}


	public String toString()
	{
		return super.toString() + "["+
				"name" + ":" + getName()+ "," +
				"isPrivate" + ":" + getIsPrivate()+ "]" + System.getProperties().getProperty("line.separator") +
				"  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
				"  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
	}
}