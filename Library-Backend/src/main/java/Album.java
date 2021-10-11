/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 23 "library.ump"
public class Album extends Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MusicGenre { Rock, Country, Jazz, Pop, Classical, Mixed, EDM, HipHop }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private int numSongs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplication aLibraryApplication, Creator aCreator, int aNumSongs)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplication, aCreator);
    numSongs = aNumSongs;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumSongs(int aNumSongs)
  {
    boolean wasSet = false;
    numSongs = aNumSongs;
    wasSet = true;
    return wasSet;
  }

  public int getNumSongs()
  {
    return numSongs;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "numSongs" + ":" + getNumSongs()+ "]";
  }
}