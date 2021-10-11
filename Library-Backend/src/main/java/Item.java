/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 1 "library.ump"
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
  private int itemId;

  //Item Associations
  private LibraryApplication libraryApplication;
  private Creator creator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aTitle, boolean aIsArchive, boolean aIsReservable, Date aReleaseDate, int aQuantityAvailable, int aQuantity, LibraryApplication aLibraryApplication, Creator aCreator)
  {
    title = aTitle;
    isArchive = aIsArchive;
    isReservable = aIsReservable;
    releaseDate = aReleaseDate;
    quantityAvailable = aQuantityAvailable;
    quantity = aQuantity;
    itemId = nextItemId++;
    boolean didAddLibraryApplication = setLibraryApplication(aLibraryApplication);
    if (!didAddLibraryApplication)
    {
      throw new RuntimeException("Unable to create item due to libraryApplication. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCreator = setCreator(aCreator);
    if (!didAddCreator)
    {
      throw new RuntimeException("Unable to create item due to creator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

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
  public LibraryApplication getLibraryApplication()
  {
    return libraryApplication;
  }
  /* Code from template association_GetOne */
  public Creator getCreator()
  {
    return creator;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibraryApplication(LibraryApplication aLibraryApplication)
  {
    boolean wasSet = false;
    if (aLibraryApplication == null)
    {
      return wasSet;
    }

    LibraryApplication existingLibraryApplication = libraryApplication;
    libraryApplication = aLibraryApplication;
    if (existingLibraryApplication != null && !existingLibraryApplication.equals(aLibraryApplication))
    {
      existingLibraryApplication.removeItem(this);
    }
    libraryApplication.addItem(this);
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
    LibraryApplication placeholderLibraryApplication = libraryApplication;
    this.libraryApplication = null;
    if(placeholderLibraryApplication != null)
    {
      placeholderLibraryApplication.removeItem(this);
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
            "  " + "libraryApplication = "+(getLibraryApplication()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "creator = "+(getCreator()!=null?Integer.toHexString(System.identityHashCode(getCreator())):"null");
  }
}