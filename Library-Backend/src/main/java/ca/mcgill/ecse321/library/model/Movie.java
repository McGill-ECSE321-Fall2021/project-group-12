package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 30 "library.ump"
public class Movie extends Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movie Attributes
  private int duration;
  private BMGenre genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Movie(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator, int aDuration, BMGenre aGenre)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
    duration = aDuration;
    genre = aGenre;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setGenre(BMGenre aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public int getDuration()
  {
    return duration;
  }

  public BMGenre getGenre()
  {
    return genre;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}