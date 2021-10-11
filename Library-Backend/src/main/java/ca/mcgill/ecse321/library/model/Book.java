package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 15 "library.ump"
public class Book extends Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String isbn;
  private int numPages;
  private BMGenre genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Book(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator, String aIsbn, int aNumPages, BMGenre aGenre)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
    isbn = aIsbn;
    numPages = aNumPages;
    genre = aGenre;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsbn(String aIsbn)
  {
    boolean wasSet = false;
    isbn = aIsbn;
    wasSet = true;
    return wasSet;
  }

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

  public String getIsbn()
  {
    return isbn;
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
            "isbn" + ":" + getIsbn()+ "," +
            "numPages" + ":" + getNumPages()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}