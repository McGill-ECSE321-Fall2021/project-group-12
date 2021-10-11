/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;

// line 110 "library.ump"
public class LibraryHour
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Day { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibraryHour Attributes
  private Time startTime;
  private Time endTime;
  private Day day;

  //LibraryHour Associations
  private LibraryApplication libraryApplication;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibraryHour(Time aStartTime, Time aEndTime, Day aDay, LibraryApplication aLibraryApplication)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    day = aDay;
    boolean didAddLibraryApplication = setLibraryApplication(aLibraryApplication);
    if (!didAddLibraryApplication)
    {
      throw new RuntimeException("Unable to create libraryHour due to libraryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

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

  public boolean setDay(Day aDay)
  {
    boolean wasSet = false;
    day = aDay;
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

  public Day getDay()
  {
    return day;
  }
  /* Code from template association_GetOne */
  public LibraryApplication getLibraryApplication()
  {
    return libraryApplication;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibraryApplication(LibraryApplication aLibraryApplication)
  {
    boolean wasSet = false;
    if (aLibraryApplication == null)
    {
      return wasSet;
    }

    LibraryApplication existingLibraryApplication = libraryApplication;
    libraryApplication = aLibraryApplication;
    if (existingLibraryApplication != null && !existingLibraryApplication.equals(aLibraryApplication))
    {
      existingLibraryApplication.removeLibraryHour(this);
    }
    libraryApplication.addLibraryHour(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    LibraryApplication placeholderLibraryApplication = libraryApplication;
    this.libraryApplication = null;
    if(placeholderLibraryApplication != null)
    {
      placeholderLibraryApplication.removeLibraryHour(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "day" + "=" + (getDay() != null ? !getDay().equals(this)  ? getDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "libraryApplication = "+(getLibraryApplication()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplication())):"null");
  }
}