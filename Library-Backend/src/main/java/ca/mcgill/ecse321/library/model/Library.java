/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 118 "library.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Associations
  private List<LibraryHour> libraryHours;
  private List<TimeSlot> timeSlots;
  private LibraryApplication libraryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(LibraryApplication aLibraryApplication)
  {
    libraryHours = new ArrayList<LibraryHour>();
    timeSlots = new ArrayList<TimeSlot>();
    if (aLibraryApplication == null || aLibraryApplication.getLibrary() != null)
    {
      throw new RuntimeException("Unable to create Library due to aLibraryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    libraryApplication = aLibraryApplication;
  }

  public Library()
  {
    libraryHours = new ArrayList<LibraryHour>();
    timeSlots = new ArrayList<TimeSlot>();
    libraryApplication = new LibraryApplication(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public LibraryApplication getLibraryApplication()
  {
    return libraryApplication;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryHours()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasAdded = false;
    if (libraryHours.contains(aLibraryHour)) { return false; }
    libraryHours.add(aLibraryHour);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasRemoved = false;
    if (libraryHours.contains(aLibraryHour))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    timeSlots.add(aTimeSlot);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    if (timeSlots.contains(aTimeSlot))
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

  public void delete()
  {
    libraryHours.clear();
    timeSlots.clear();
    LibraryApplication existingLibraryApplication = libraryApplication;
    libraryApplication = null;
    if (existingLibraryApplication != null)
    {
      existingLibraryApplication.delete();
    }
  }

}