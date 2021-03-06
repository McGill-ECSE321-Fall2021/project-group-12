package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Book extends Item
{

  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  private int numPages;
  private BMGenre genre;

  public boolean setNumPages(int aNumPages)
  {
    boolean wasSet = false;
    numPages = aNumPages;
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

  public int getNumPages()
  {
    return numPages;
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
            "numPages" + ":" + getNumPages()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
