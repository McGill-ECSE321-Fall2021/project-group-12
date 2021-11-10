package ca.mcgill.ecse321.library.model;


import java.util.*;

import javax.persistence.*;

@Entity
public class Reservation
{

  private Long reservationId;
  private List<Item> items;
  private User user;
  private TimeSlot timeSlot;
  private LibraryApplicationSystem libraryApplicationSystem;
  
  
  public boolean setReservationId(Long id) {
	  reservationId = id;
	  return true;
  }
  
  public void removeReservation() {
	  this.items = null;
	  this.user = null;
	  this.timeSlot = null;
	  this.reservationId = null;
	  this.libraryApplicationSystem = null;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getReservationId() {
	  return reservationId;
  }
  
  @OneToMany (orphanRemoval = true)
  public List<Item> getItems()
  {
    return items;
  }
  
  public boolean setItems(List<Item> newItems) {
	  	items = newItems;
		return true;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  @ManyToOne
  public User getUser()
  {
    return user;
  }
 
  @ManyToOne
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }

  @ManyToOne(cascade = {CascadeType.ALL})
  public LibraryApplicationSystem getLibraryApplicationSystem()
  {
    return libraryApplicationSystem;
  }


  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Reservation existingReservation = aItem.getReservation();
    boolean isNewReservation = existingReservation != null && !this.equals(existingReservation);
    if (isNewReservation)
    {
      aItem.setReservation(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a reservation
    if (!this.equals(aItem.getReservation()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }
    user = aUser;
    wasSet = true;
    return wasSet;
  }
  
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
  }
  
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
      existingLibraryApplicationSystem.removeReservation(this);
    }
    libraryApplicationSystem.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
    timeSlot = null;
    LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
    this.libraryApplicationSystem = null;
    if(placeholderLibraryApplicationSystem != null)
    {
      placeholderLibraryApplicationSystem.removeReservation(this);
    }
  }

}
