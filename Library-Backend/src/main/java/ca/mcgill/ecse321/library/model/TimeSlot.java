package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;

// line 102 "library.ump"
import javax.persistence.*;

@Entity
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;

  //TimeSlot Associations
  private LibraryApplicationSystem libraryApplicationSystem;
  private Event event;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long timeSlotId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, LibraryApplicationSystem aLibraryApplicationSystem)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    startDate = aStartDate;
    endDate = aEndDate;
    boolean didAddLibraryApplicationSystem = setLibraryApplicationSystem(aLibraryApplicationSystem);
    if (!didAddLibraryApplicationSystem)
    {
      throw new RuntimeException("Unable to create timeSlot due to libraryApplicationSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public TimeSlot() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }
  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }

  public boolean hasEvent()
  {
    boolean has = event != null;
    return has;
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
      existingLibraryApplicationSystem.removeTimeSlot(this);
    }
    libraryApplicationSystem.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setEvent(Event aNewEvent)
  {
    boolean wasSet = false;
    if (event != null && !event.equals(aNewEvent) && equals(event.getTimeSlot()))
    {
      //Unable to setEvent, as existing event would become an orphan
      return wasSet;
    }

    event = aNewEvent;
    TimeSlot anOldTimeSlot = aNewEvent != null ? aNewEvent.getTimeSlot() : null;

    if (!this.equals(anOldTimeSlot))
    {
      if (anOldTimeSlot != null)
      {
        anOldTimeSlot.event = null;
      }
      if (event != null)
      {
        event.setTimeSlot(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
    this.libraryApplicationSystem = null;
    if(placeholderLibraryApplicationSystem != null)
    {
      placeholderLibraryApplicationSystem.removeTimeSlot(this);
    }
    Event existingEvent = event;
    event = null;
    if (existingEvent != null)
    {
      existingEvent.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}
