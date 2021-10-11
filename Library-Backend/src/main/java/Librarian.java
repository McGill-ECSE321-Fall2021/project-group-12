/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 66 "library.ump"
public class Librarian extends OnlineUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private List<LibraryHour> libraryHour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplication aLibraryApplication, String aUsername, String aPassword, String aEmail)
  {
    super(aFirstName, aLastName, aAddress, aIsLocal, aLibraryApplication, aUsername, aPassword, aEmail);
    libraryHour = new ArrayList<LibraryHour>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public LibraryHour getLibraryHour(int index)
  {
    LibraryHour aLibraryHour = libraryHour.get(index);
    return aLibraryHour;
  }

  public List<LibraryHour> getLibraryHour()
  {
    List<LibraryHour> newLibraryHour = Collections.unmodifiableList(libraryHour);
    return newLibraryHour;
  }

  public int numberOfLibraryHour()
  {
    int number = libraryHour.size();
    return number;
  }

  public boolean hasLibraryHour()
  {
    boolean has = libraryHour.size() > 0;
    return has;
  }

  public int indexOfLibraryHour(LibraryHour aLibraryHour)
  {
    int index = libraryHour.indexOf(aLibraryHour);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryHour()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasAdded = false;
    if (libraryHour.contains(aLibraryHour)) { return false; }
    libraryHour.add(aLibraryHour);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryHour(LibraryHour aLibraryHour)
  {
    boolean wasRemoved = false;
    if (libraryHour.contains(aLibraryHour))
    {
      libraryHour.remove(aLibraryHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryHourAt(LibraryHour aLibraryHour, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryHour(aLibraryHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryHour()) { index = numberOfLibraryHour() - 1; }
      libraryHour.remove(aLibraryHour);
      libraryHour.add(index, aLibraryHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryHourAt(LibraryHour aLibraryHour, int index)
  {
    boolean wasAdded = false;
    if(libraryHour.contains(aLibraryHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryHour()) { index = numberOfLibraryHour() - 1; }
      libraryHour.remove(aLibraryHour);
      libraryHour.add(index, aLibraryHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryHourAt(aLibraryHour, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    libraryHour.clear();
    super.delete();
  }

}