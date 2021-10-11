package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 95 "library.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private String name;
  private boolean isPrivate;

  //Event Associations
  private TimeSlot timeSlot;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(String aName, boolean aIsPrivate, TimeSlot aTimeSlot, User aUser)
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
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsPrivate(boolean aIsPrivate)
  {
    boolean wasSet = false;
    isPrivate = aIsPrivate;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getIsPrivate()
  {
    return isPrivate;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot == null)
    {
      //Unable to setTimeSlot to null, as event must always be associated to a timeSlot
      return wasSet;
    }
    
    Event existingEvent = aNewTimeSlot.getEvent();
    if (existingEvent != null && !equals(existingEvent))
    {
      //Unable to setTimeSlot, the current timeSlot already has a event, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    TimeSlot anOldTimeSlot = timeSlot;
    timeSlot = aNewTimeSlot;
    timeSlot.setEvent(this);

    if (anOldTimeSlot != null)
    {
      anOldTimeSlot.setEvent(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeEvent(this);
    }
    user.addEvent(this);
    wasSet = true;
    return wasSet;
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
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}