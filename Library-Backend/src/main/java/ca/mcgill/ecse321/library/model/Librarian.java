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
	

}
