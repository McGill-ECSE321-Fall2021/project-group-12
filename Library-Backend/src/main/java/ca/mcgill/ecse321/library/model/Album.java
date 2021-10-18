package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

import ca.mcgill.ecse321.library.model.Book.BMGenre;

@Entity
public class Album extends Item
{

  public enum MusicGenre { Rock, Country, Jazz, Pop, Classical, Mixed, EDM, HipHop }

  private int numSongs;
  private MusicGenre genre;
  
  public boolean setNumSongs(int aNumSongs)
  {
    boolean wasSet = false;
    numSongs = aNumSongs;
    wasSet = true;
    return wasSet;
  }
  
  public boolean setGenre(MusicGenre aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  @Id
  public Long getItemId()
  {
    return super.getItemId();
  }

  public int getNumSongs()
  {
    return numSongs;
  }
  
  public MusicGenre getGenre()
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
            "numSongs" + ":" + getNumSongs()+ "]" + System.getProperties().getProperty("line.separator");
  }
}
