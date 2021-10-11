package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 42 "library.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextUserId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String firstName;
  private String lastName;
  private String address;
  private boolean isLocal;

  //Autounique Attributes
  private int userId;

  //User Associations
  private List<Event> events;
  private LibraryApplicationSystem libraryApplicationSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplicationSystem aLibraryApplicationSystem)
  {
    firstName = aFirstName;
    lastName = aLastName;
    address = aAddress;
    isLocal = aIsLocal;
    userId = nextUserId++;
    events = new ArrayList<Event>();
    boolean didAddLibraryApplicationSystem = setLibraryApplicationSystem(aLibraryApplicationSystem);
    if (!didAddLibraryApplicationSystem)
    {
      throw new RuntimeException("Unable to create user due to libraryApplicationSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsLocal(boolean aIsLocal)
  {
    boolean wasSet = false;
    isLocal = aIsLocal;
    wasSet = true;
    return wasSet;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getAddress()
  {
    return address;
  }

  public boolean getIsLocal()
  {
    return isLocal;
  }

  public int getUserId()
  {
    return userId;
  }
  /* Code from template association_GetMany */
  public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }

  public List<Event> getEvents()
  {
    List<Event> newEvents = Collections.unmodifiableList(events);
    return newEvents;
  }

  public int numberOfEvents()
  {
    int number = events.size();
    return number;
  }

  public boolean hasEvents()
  {
    boolean has = events.size() > 0;
    return has;
  }

  public int indexOfEvent(Event aEvent)
  {
    int index = events.indexOf(aEvent);
    return index;
  }
  /* Code from template association_GetOne */
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEvents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Event addEvent(String aName, boolean aIsPrivate, TimeSlot aTimeSlot)
  {
    return new Event(aName, aIsPrivate, aTimeSlot, this);
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
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEventAt(Event aEvent, int index)
  {  
    boolean wasAdded = false;
    if(addEvent(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(events.contains(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEventAt(aEvent, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem)
  {
    boolean wasSet = false;
    if (aLibraryApplicationSystem == null)
    {
      return wasSet;
    }

    LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
    libraryApplicationSystem = aLibraryApplicationSystem;
    if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem))
    {
      existingLibraryApplicationSystem.removeUser(this);
    }
    libraryApplicationSystem.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=events.size(); i > 0; i--)
    {
      Event aEvent = events.get(i - 1);
      aEvent.delete();
    }
    LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
    this.libraryApplicationSystem = null;
    if(placeholderLibraryApplicationSystem != null)
    {
      placeholderLibraryApplicationSystem.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "userId" + ":" + getUserId()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "isLocal" + ":" + getIsLocal()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
  }
}