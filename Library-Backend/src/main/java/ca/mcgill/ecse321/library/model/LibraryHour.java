package ca.mcgill.ecse321.library.model;

import java.sql.Time;

import javax.persistence.*;

@Entity
public class LibraryHour {
	
	public enum Day { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	
	private Time startTime;
	private Time endTime;
	private Day day;
	private Long libraryHourId;
  
	private LibraryApplicationSystem libraryApplicationSystem;

	public void setLibraryHourId(Long id) {
		libraryHourId = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getLibraryHourId() {
		return libraryHourId;
	}
	
	public void setStartTime(Time aStartTime) {
	    this.startTime = aStartTime;
	}

	public void setEndTime(Time aEndTime) {
		this.endTime = aEndTime;
	}

	public void setDay(Day aDay) {
	    this.day = aDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Day getDay() {
		return day;
	}
	
	@ManyToOne(cascade= {CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem() {
		return libraryApplicationSystem;
	}
  
	public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem) {
		boolean wasSet = false;
	    if (aLibraryApplicationSystem == null)
	    {
	      return wasSet;
	    }

	    LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
	    libraryApplicationSystem = aLibraryApplicationSystem;
	    if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem))
	    {
	      existingLibraryApplicationSystem.removeLibraryHour(this);
	    }
	    libraryApplicationSystem.addLibraryHour(this);
	    wasSet = true;
	    return wasSet;
	}

	public void delete() {
		LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
	    this.libraryApplicationSystem = null;
	    if(placeholderLibraryApplicationSystem != null)
	    {
	      placeholderLibraryApplicationSystem.removeLibraryHour(this);
	    }
	}

	public String toString() {
		return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
	            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
	            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
	            "  " + "day" + "=" + (getDay() != null ? !getDay().equals(this)  ? getDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
	            "  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
  }
  
}
