package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

import javax.persistence.*;

@Entity
public class Book extends Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum BMGenre { Fiction, Nonfiction, Classic, Horror, Fantasy, Mystery, Action }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextBookId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String author;
  private Date release;
  private int numPages;
  private BMGenre genre;

  //Autounique Attributes
  private int bookId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Book(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator, String aAuthor, Date aRelease, int aNumPages, BMGenre aGenre)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
    author = aAuthor;
    release = aRelease;
    numPages = aNumPages;
    genre = aGenre;
    bookId = nextBookId++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthor(String aAuthor)
  {
    boolean wasSet = false;
    author = aAuthor;
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

  public String getAuthor()
  {
    return author;
  }

  public Date getRelease()
  {
    return release;
  }

  public int getNumPages()
  {
    return numPages;
  }

  public BMGenre getGenre()
  {
    return genre;
  }

  public int getBookId()
  {
    return bookId;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "bookId" + ":" + getBookId()+ "," +
            "author" + ":" + getAuthor()+ "," +
            "numPages" + ":" + getNumPages()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "release" + "=" + (getRelease() != null ? !getRelease().equals(this)  ? getRelease().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "genre" + "=" + (getGenre() != null ? !getGenre().equals(this)  ? getGenre().toString().replaceAll("  ","    ") : "this" : "null");
  }
}