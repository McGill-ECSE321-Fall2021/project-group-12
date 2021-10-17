package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;

@Entity
public class HeadLibrarian extends Librarian {

	public void delete() {
		super.delete();
	}
	
	public boolean addLibraryHour(LibraryHour aLibraryHour, Librarian aLibrarian) {
		boolean wasAdded = false;
	    if (aLibrarian.getLibraryHour().contains(aLibraryHour)) { return false; }
	    aLibrarian.getLibraryHour().add(aLibraryHour);
	    wasAdded = true;
	    return wasAdded;
	}

	public boolean removeLibraryHour(LibraryHour aLibraryHour, Librarian aLibrarian) {
		boolean wasRemoved = false;
	    if (aLibrarian.getLibraryHour().contains(aLibraryHour))
	    {
	      aLibrarian.getLibraryHour().remove(aLibraryHour);
	      wasRemoved = true;
	    }
	    return wasRemoved;
	}

	public boolean addLibraryHourAt(LibraryHour aLibraryHour, int index, Librarian aLibrarian) {
		boolean wasAdded = false;
	    if(addLibraryHour(aLibraryHour, aLibrarian))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfLibraryHour()) { index = numberOfLibraryHour() - 1; }
	      aLibrarian.getLibraryHour().remove(aLibraryHour);
	      aLibrarian.getLibraryHour().add(index, aLibraryHour);
	      wasAdded = true;
	    }
	    return wasAdded;
	}

	public boolean addOrMoveLibraryHourAt(LibraryHour aLibraryHour, int index, Librarian aLibrarian) {
		boolean wasAdded = false;
	    if(aLibrarian.getLibraryHour().contains(aLibraryHour))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfLibraryHour()) { index = numberOfLibraryHour() - 1; }
	      aLibrarian.getLibraryHour().remove(aLibraryHour);
	      aLibrarian.getLibraryHour().add(index, aLibraryHour);
	      wasAdded = true;
	    } 
	    else 
	    {
	      wasAdded = addLibraryHourAt(aLibraryHour, index, aLibrarian);
	    }
	    return wasAdded;
	}

}