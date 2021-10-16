package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

import javax.persistence.*;

@Entity
public class Movie extends Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextMovieId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movie Attributes
  private String director;
  private Date release;
  private int duration;
  private BMGenre genre;

  //Autounique Attributes
  private int movieId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Movie(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator, String aDirector, Date aRelease, int aDuration, BMGenre aGenre)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
    director = aDirector;
    release = aRelease;
    duration = aDuration;
    genre = aGenre;
    movieId = nextMovieId++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDirector(String aDirector)
  {
    boolean wasSet = false;
    director = aDirector;
    wasSet = true;
    return wasSet;
  }

  public boolean setRelease(Date aRelease)
  {
    boolean wasSet = false;
    release = aRelease;
    wasSet = true;
    return wasSet;
  }

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

  public String getDirector()
  {
    return director;
  }

  public Date getRelease()
  {
    return release;
  }

  public int getDuration()
  {
    return duration;
  }

  public BMGenre getGenre()
  {
    return genre;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "movieId" + ":" + getMovieId()+ "," +
            "director" + ":" + getDirector()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "release" + "=" + (getRelease() != null ? !getRelease().equals(this)  ? getRelease().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}