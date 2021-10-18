package ca.mcgill.ecse321.library.model;

import javax.persistence.*;


@Entity
public class HeadLibrarian extends Librarian {
	
	public void delete() {
		super.delete();
	}
	
	public boolean addLibraryHour(LibraryHour aLibraryHour, Librarian aLibrarian) {
		boolean wasAdded = false;
	    if (aLibrarian.getLibraryHours().contains(aLibraryHour)) { return false; }
	    aLibrarian.getLibraryHours().add(aLibraryHour);
	    wasAdded = true;
	    return wasAdded;
	}

	public boolean removeLibraryHour(LibraryHour aLibraryHour, Librarian aLibrarian) {
		boolean wasRemoved = false;
	    if (aLibrarian.getLibraryHours().contains(aLibraryHour))
	    {
	      aLibrarian.getLibraryHours().remove(aLibraryHour);
	      wasRemoved = true;
	    }
	    return wasRemoved;
	}

}
