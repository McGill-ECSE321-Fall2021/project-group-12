package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 37 "library.ump"
import javax.persistence.*;

@Entity
public class Newspaper extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Newspaper(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator)
  {
    super(aTitle, aIsArchive, aIsReservable, aReleaseDate, aQuantityAvailable, aQuantity, aLibraryApplicationSystem, aCreator);
  }

  public Newspaper() {
      super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}
