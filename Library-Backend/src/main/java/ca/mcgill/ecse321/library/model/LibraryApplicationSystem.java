package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 77 "library.ump"
public class LibraryApplicationSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibraryApplicationSystem Associations
  private List<User> users;
  private List<Item> items;
  private List<TimeSlot> timeSlots;
  private List<LibraryHour> libraryHours;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibraryApplicationSystem(Library aLibrary)
  {
    users = new ArrayList<User>();
    items = new ArrayList<Item>();
    timeSlots = new ArrayList<TimeSlot>();
    libraryHours = new ArrayList<LibraryHour>();
    if (aLibrary == null || aLibrary.getLibraryApplicationSystem() != null)
    {
      throw new RuntimeException("Unable to create LibraryApplicationSystem due to aLibrary. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    library = aLibrary;
  }

  public LibraryApplicationSystem()
  {
    users = new ArrayList<User>();
    items = new ArrayList<Item>();
    timeSlots = new ArrayList<TimeSlot>();
    libraryHours = new ArrayList<LibraryHour>();
    library = new Library(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public LibraryHour getLibraryHour(int index)
  {
    LibraryHour aLibraryHour = libraryHours.get(index);
    return aLibraryHour;
  }

  public List<LibraryHour> getLibraryHours()
  {
    List<LibraryHour> newLibraryHours = Collections.unmodifiableList(libraryHours);
    return newLibraryHours;
  }

  public int numberOfLibraryHours()
  {
    int number = libraryHours.size();
    return number;
  }

  public boolean hasLibraryHours()
  {
    boolean has = libraryHours.size() > 0;
    return has;
  }

  public int indexOfLibraryHour(LibraryHour aLibraryHour)
  {
    int index = libraryHours.indexOf(aLibraryHour);
    return index;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    LibraryApplicationSystem existingLibraryApplicationSystem = aUser.getLibraryApplicationSystem();
    boolean isNewLibraryApplicationSystem = existingLibraryApplicationSystem != null && !this.equals(existingLibraryApplicationSystem);
    if (isNewLibraryApplicationSystem)
    {
      aUser.setLibraryApplicationSystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a libraryApplicationSystem
    if (!this.equals(aUser.getLibraryApplicationSystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    LibraryApplicationSystem existingLibraryApplicationSystem = aItem.getLibraryApplicationSystem();
    boolean isNewLibraryApplicationSystem = existingLibraryApplicationSystem != null && !this.equals(existingLibraryApplicationSystem);
    if (isNewLibraryApplicationSystem)
    {
      aItem.setLibraryApplicationSystem(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a libraryApplicationSystem
    if (!this.equals(aItem.getLibraryApplicationSystem()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate)
  {
    return new TimeSlot(aStartTime, aEndTime, aStartDate, aEndDate, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    LibraryApplicationSystem existingLibraryApplicationSystem = aTimeSlot.getLibraryApplicationSystem();
    boolean isNewLibraryApplicationSystem = existingLibraryApplicationSystem != null && !this.equals(existingLibraryApplicationSystem);
    if (isNewLibraryApplicationSystem)
    {
      aTimeSlot.setLibraryApplicationSystem(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a libraryApplicationSystem
    if (!this.equals(aTimeSlot.getLibraryApplicationSystem()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LibraryHour addLibraryHour(Time aStartTime, Time aEndTime, LibraryHour.Day aDay)
  {
    return new LibraryHour(aStartTime, aEndTime, aDay, this);
  }

  public boolean addLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasAdded = false;
    if (libraryHours.contains(aLibraryHour)) { return false; }
    LibraryApplicationSystem existingLibraryApplicationSystem = aLibraryHour.getLibraryApplicationSystem();
    boolean isNewLibraryApplicationSystem = existingLibraryApplicationSystem != null && !this.equals(existingLibraryApplicationSystem);
    if (isNewLibraryApplicationSystem)
    {
      aLibraryHour.setLibraryApplicationSystem(this);
    }
    else
    {
      libraryHours.add(aLibraryHour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibraryHour, as it must always have a libraryApplicationSystem
    if (!this.equals(aLibraryHour.getLibraryApplicationSystem()))
    {
      libraryHours.remove(aLibraryHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryHourAt(LibraryHour aLibraryHour, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryHour(aLibraryHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryHours()) { index = numberOfLibraryHours() - 1; }
      libraryHours.remove(aLibraryHour);
      libraryHours.add(index, aLibraryHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryHourAt(LibraryHour aLibraryHour, int index)
  {
    boolean wasAdded = false;
    if(libraryHours.contains(aLibraryHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryHours()) { index = numberOfLibraryHours() - 1; }
      libraryHours.remove(aLibraryHour);
      libraryHours.add(index, aLibraryHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryHourAt(aLibraryHour, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (items.size() > 0)
    {
      Item aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (libraryHours.size() > 0)
    {
      LibraryHour aLibraryHour = libraryHours.get(libraryHours.size() - 1);
      aLibraryHour.delete();
      libraryHours.remove(aLibraryHour);
    }
    
    Library existingLibrary = library;
    library = null;
    if (existingLibrary != null)
    {
      existingLibrary.delete();
    }
  }

}