package ca.mcgill.ecse321.library.model;

import java.util.*;

import javax.persistence.*;
import javax.persistence.OneToMany;

@Entity
public class Librarian extends OnlineUser {
	
	private List<LibraryHour> libraryHour;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long librarianId;

	public Librarian(String aFirstName, String aLastName, String aAddress, boolean aIsLocal, LibraryApplicationSystem aLibraryApplicationSystem, String aUsername, String aPassword, String aEmail) {
		super(aFirstName, aLastName, aAddress, aIsLocal, aLibraryApplicationSystem, aUsername, aPassword, aEmail);
		libraryHour = new ArrayList<LibraryHour>();
	}

    public Librarian() {
        super();
    }
	
	public LibraryHour getLibraryHour(int index) {
		LibraryHour aLibraryHour = libraryHour.get(index);
		return aLibraryHour;
	}
	
	@OneToMany
	public List<LibraryHour> getLibraryHour() {
		List<LibraryHour> newLibraryHour = Collections.unmodifiableList(libraryHour);
	    return newLibraryHour;
	}

	public int numberOfLibraryHour() {
		int number = libraryHour.size();
	    return number;
	}

	public boolean hasLibraryHour() {
		boolean has = libraryHour.size() > 0;
	    return has;
	}
	
	public void delete() {
  		libraryHour.clear();
  	    super.delete();
  	}
	
	public int indexOfLibraryHour(LibraryHour aLibraryHour) {
		int index = libraryHour.indexOf(aLibraryHour);
	    return index;
	}
	
	//Normal Librarian are not able modify LibraryHour but they can view the Library
	//How does this show the correctness of the model? Is association assigned correctly?
	/*
	public boolean addLibraryHour(LibraryHour aLibraryHour) {
		boolean wasAdded = false;
	    if (libraryHour.contains(aLibraryHour)) { return false; }
	    libraryHour.add(aLibraryHour);
	    wasAdded = true;
	    return wasAdded;
	}

	public boolean removeLibraryHour(LibraryHour aLibraryHour) {
		boolean wasRemoved = false;
	    if (libraryHour.contains(aLibraryHour))
	    {
	      libraryHour.remove(aLibraryHour);
	      wasRemoved = true;
	    }
	    return wasRemoved;
	}

	public boolean addLibraryHourAt(LibraryHour aLibraryHour, int index) {
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

	public boolean addOrMoveLibraryHourAt(LibraryHour aLibraryHour, int index) {
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
	*/
	
}
