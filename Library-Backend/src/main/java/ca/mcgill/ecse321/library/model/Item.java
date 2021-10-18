package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class Item
{

  private String title;
  private boolean isArchive;
  private boolean isReservable;
  private Date releaseDate;
  private int quantityAvailable;
  private int quantity;
  private Reservation reservation;


  private Long itemId;


  //Item Associations
  private LibraryApplicationSystem libraryApplicationSystem;
  private Creator creator;

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

  @OneToOne
  public Reservation getReservation()
  {
    return reservation;
  }
  
  public boolean setReservation(Reservation aReservation)
  {
    boolean wasSet = false;
    if (aReservation == null)
    {
      return wasSet;
    }

    Reservation existingReservation = reservation;
    reservation = aReservation;
    if (existingReservation != null && !existingReservation.equals(aReservation))
    {
      existingReservation.removeItem(this);
    }
    reservation.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public int getQuantityAvailable()
  {
    return quantityAvailable;
  }

  public int getQuantity()
  {
    return quantity;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getItemId()
  {
    return itemId;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional=false)
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }
  /* Code from template association_GetOne */
  @ManyToOne(cascade = {CascadeType.ALL})
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
