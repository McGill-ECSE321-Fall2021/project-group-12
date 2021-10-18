package ca.mcgill.ecse321.library.model;

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
  private Reservation reservation;
  private Long timeSlotId;
  
  public boolean setTimeSlotId(Long id) {
	  timeSlotId = id;
	  return true;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getTimeSlotId() {
	  return timeSlotId;
  }

  public boolean setStartTime(Time aStartTime)
  {
    startTime = aStartTime;
    return true;
  }

  public boolean setEndTime(Time aEndTime)
  {
    endTime = aEndTime;
    return true;
  }

  public boolean setStartDate(Date aStartDate)
  {
    startDate = aStartDate;
    return true;
  }

  public boolean setEndDate(Date aEndDate)
  {
    endDate = aEndDate;
    return true;
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

  @ManyToOne(optional=false)
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }
  
  @OneToOne(optional=true, cascade={CascadeType.ALL})
  public Reservation getReservation() {
	  return reservation;
  }

  @OneToOne(optional=true, cascade={CascadeType.ALL})
  public Event getEvent()
  {
    return event;
  }

  public boolean hasReservation() {
	  return reservation != null;
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
  
  public boolean setReservation(Reservation newReservation) {
	  newReservation.setTimeSlot(this);
	  reservation = newReservation;
	  return true;
  }

  public boolean setEvent(Event aNewEvent)
  {
    if (event != null && !event.equals(aNewEvent) && equals(event.getTimeSlot()))
    {
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
    
    Reservation existingReservation = reservation;
    reservation = null;
    if (existingReservation != null) {
    	existingReservation.delete();
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
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}
