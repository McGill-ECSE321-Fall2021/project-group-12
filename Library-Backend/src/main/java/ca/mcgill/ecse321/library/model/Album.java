package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

import javax.persistence.*;

@Entity
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
  private Long albumId;
  private String albumName;
  private String artist;
  private Date release;
  private int numSongs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator, Long aAlbumId, String aAlbumName, String aArtist, Date aRelease, int aNumSongs)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
    albumId = aAlbumId;
    albumName = aAlbumName;
    artist = aArtist;
    release = aRelease;
    numSongs = aNumSongs;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAlbumId(Long aAlbumId)
  {
    boolean wasSet = false;
    albumId = aAlbumId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAlbumName(String aAlbumName)
  {
    boolean wasSet = false;
    albumName = aAlbumName;
    wasSet = true;
    return wasSet;
  }

  public boolean setArtist(String aArtist)
  {
    boolean wasSet = false;
    artist = aArtist;
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

  public boolean setNumSongs(int aNumSongs)
  {
    boolean wasSet = false;
    numSongs = aNumSongs;
    wasSet = true;
    return wasSet;
  }

  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getAlbumId()
  {
    return albumId;
  }

  public String getAlbumName()
  {
    return albumName;
  }

  public String getArtist()
  {
    return artist;
  }

  public Date getRelease()
  {
    return release;
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
            "albumId" + ":" + getAlbumId()+ "," +
            "albumName" + ":" + getAlbumName()+ "," +
            "artist" + ":" + getArtist()+ "," +
            "numSongs" + ":" + getNumSongs()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "release" + "=" + (getRelease() != null ? !getRelease().equals(this)  ? getRelease().toString().replaceAll("  ","    ") : "this" : "null");
  }
}