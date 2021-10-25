package ca.mcgill.ecse321.library.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Librarian extends OnlineUser {
	
	private List<LibraryHour> libraryHours;
	private boolean isHead = false;
	
	public LibraryHour getLibraryHours(int index) {
		LibraryHour aLibraryHour = libraryHours.get(index);
		return aLibraryHour;
	}
	
	public boolean setLibraryHours(List<LibraryHour> newLibraryHours) {
		libraryHours = newLibraryHours;
		return true;
	}
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true)
	public List<LibraryHour> getLibraryHours() {
	    return libraryHours;
	}

	public int numberOfLibraryHours() {
		return libraryHours.size();
	}

	public boolean hasLibraryHours() {
		boolean has = libraryHours.size() > 0;
	    return has;
	}
	
	public boolean getIsHead() {
	    return isHead;
	}
	
	public boolean setIsHead(boolean aIsHead) {
		
		if(aIsHead) {
			
			List<User> users = this.getLibraryApplicationSystem().getUsers();
			
			for(User u:users) {
				
				if(u instanceof Librarian) {
					
					if(((Librarian)u).getIsHead()) {
						
						if(this.equals(u)) {
							
							return true;
							
						}
						
						isHead=false;
						return false;
						
						
					}
					
				}
				
			}
			
		}
		
		isHead = aIsHead;
		return true;
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
