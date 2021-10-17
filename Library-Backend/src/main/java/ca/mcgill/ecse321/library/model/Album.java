package ca.mcgill.ecse321.library.model;


import javax.persistence.*;

@Entity
public class Album extends Item
{

  public enum MusicGenre { Rock, Country, Jazz, Pop, Classical, Mixed, EDM, HipHop }

  private Long albumId;
  private int numSongs;

  public boolean setAlbumId(Long aAlbumId)
  {
    boolean wasSet = false;
    albumId = aAlbumId;
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
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getAlbumId()
  {
    return albumId;
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
            "numSongs" + ":" + getNumSongs()+ "]" + System.getProperties().getProperty("line.separator");
  }
}