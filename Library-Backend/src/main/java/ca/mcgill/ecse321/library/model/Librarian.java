package ca.mcgill.ecse321.library.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Librarian extends OnlineUser {
	
	private List<LibraryHour> libraryHours;
	
	public LibraryHour getLibraryHours(int index) {
		LibraryHour aLibraryHour = libraryHours.get(index);
		return aLibraryHour;
	}
	
	public boolean setLibraryHours(List<LibraryHour> newLibraryHours) {
		libraryHours = newLibraryHours;
		return true;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<LibraryHour> getLibraryHours() {
	    return libraryHours;
	}

	public int numberOfLibraryHours() {
		int number = libraryHours.size();
	    return number;
	}

	public boolean hasLibraryHours() {
		boolean has = libraryHours.size() > 0;
	    return has;
	}
	
	public void delete() {
  		libraryHours.clear();
  	    super.delete();
  	}
	
	public int indexOfLibraryHour(LibraryHour aLibraryHour) {
		int index = libraryHours.indexOf(aLibraryHour);
	    return index;
	}
	

}
