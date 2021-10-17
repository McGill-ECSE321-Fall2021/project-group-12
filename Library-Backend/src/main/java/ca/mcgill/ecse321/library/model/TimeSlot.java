package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;

@Entity
public class TimeSlot
{


  private Time startTime;
  private Time endTime;
  private Date startDate;
  private Date endDate;
  private LibraryApplicationSystem libraryApplicationSystem;
  private Event event;


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


  public boolean setStartTime(Time aStartTime)
  {
    startTime = aStartTime;
  }

  public boolean setEndTime(Time aEndTime)
  {
    endTime = aEndTime;
  }

  public boolean setStartDate(Date aStartDate)
  {
    startDate = aStartDate;
  }

  public boolean setEndDate(Date aEndDate)
  {
    endDate = aEndDate;
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

  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }

  public Event getEvent()
  {
    return event;
  }

  public boolean hasEvent()
  {
    boolean has = event != null;
    return has;
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
      existingLibraryApplicationSystem.removeTimeSlot(this);
    }

    return true;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setEvent(Event aNewEvent)
  {
    if (event != null && !event.equals(aNewEvent) && equals(event.getTimeSlot()))
    {
      //Unable to setEvent, as existing event would become an orphan
      return false;
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
    return true;
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