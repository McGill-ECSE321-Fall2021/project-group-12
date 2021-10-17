package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class Item
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextItemId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String title;
  private boolean isArchive;
  private boolean isReservable;
  private Date releaseDate;
  private int quantityAvailable;
  private int quantity;

  //Autounique Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer itemId;

  //Item Associations
  private LibraryApplicationSystem libraryApplicationSystem;
  private Creator creator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplicationSystem aLibraryApplicationSystem, Creator aCreator)
  {
    title = aTitle;
    isArchive = aIsArchive;
    isReservable = aIsReservable;
    releaseDate = aReleaseDate;
    quantityAvailable = aQuantityAvailable;
    quantity = aQuantity;
    itemId = nextItemId++;
    boolean didAddLibraryApplicationSystem = setLibraryApplicationSystem(aLibraryApplicationSystem);
    if (!didAddLibraryApplicationSystem)
    {
      throw new RuntimeException("Unable to create item due to libraryApplicationSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCreator = setCreator(aCreator);
    if (!didAddCreator)
    {
      throw new RuntimeException("Unable to create item due to creator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Item() {}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsArchive(boolean aIsArchive)
  {
    boolean wasSet = false;
    isArchive = aIsArchive;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsReservable(boolean aIsReservable)
  {
    boolean wasSet = false;
    isReservable = aIsReservable;
    wasSet = true;
    return wasSet;
  }

  public boolean setReleaseDate(Date aReleaseDate)
  {
    boolean wasSet = false;
    releaseDate = aReleaseDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantityAvailable(int aQuantityAvailable)
  {
    boolean wasSet = false;
    quantityAvailable = aQuantityAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public boolean getIsArchive()
  {
    return isArchive;
  }

  public boolean getIsReservable()
  {
    return isReservable;
  }

  public Date getReleaseDate()
  {
    return releaseDate;
  }

  public int getQuantityAvailable()
  {
    return quantityAvailable;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public int getItemId()
  {
    return itemId;
  }
  /* Code from template association_GetOne */
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }
  /* Code from template association_GetOne */
  public Creator getCreator()
  {
    return creator;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem)
  {
    boolean wasSet = false;
    if (aLibraryApplicationSystem == null)
    {
      return wasSet;
    }

    LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
    libraryApplicationSystem = aLibraryApplicationSystem;
    if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem))
    {
      existingLibraryApplicationSystem.removeItem(this);
    }
    libraryApplicationSystem.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCreator(Creator aCreator)
  {
    boolean wasSet = false;
    if (aCreator == null)
    {
      return wasSet;
    }

    Creator existingCreator = creator;
    creator = aCreator;
    if (existingCreator != null && !existingCreator.equals(aCreator))
    {
      existingCreator.removeItem(this);
    }
    creator.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
    this.libraryApplicationSystem = null;
    if(placeholderLibraryApplicationSystem != null)
    {
      placeholderLibraryApplicationSystem.removeItem(this);
    }
    Creator placeholderCreator = creator;
    this.creator = null;
    if(placeholderCreator != null)
    {
      placeholderCreator.removeItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "itemId" + ":" + getItemId()+ "," +
            "title" + ":" + getTitle()+ "," +
            "isArchive" + ":" + getIsArchive()+ "," +
            "isReservable" + ":" + getIsReservable()+ "," +
            "quantityAvailable" + ":" + getQuantityAvailable()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "creator = "+(getCreator()!=null?Integer.toHexString(System.identityHashCode(getCreator())):"null");
  }
}
