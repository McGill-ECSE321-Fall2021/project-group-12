package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Movie extends Item
{


  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  private Long movieId;
  private int duration;
  private BMGenre genre;

  public boolean setMovieId(Long aMovieId)
  {
    boolean wasSet = false;
    movieId = aMovieId;
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

  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getMovieId()
  {
    return movieId;
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
            "movieId" + ":" + getMovieId()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}