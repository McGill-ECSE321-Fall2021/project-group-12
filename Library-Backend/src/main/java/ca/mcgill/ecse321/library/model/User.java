package ca.mcgill.ecse321.library.model;

import javax.persistence.*;


import java.util.*;

@Entity
@Table(name = "Users")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User{

	private String firstName;
	private String lastName;
	private String address;
	private boolean isLocal;

	private Long userId;
	private LibraryApplicationSystem libraryApplicationSystem;

	public void setFirstName(String aFirstName) {	
		firstName = aFirstName;
	}

	public void setLastName(String aLastName) {
		lastName = aLastName;
	}

	public void setAddress(String aAddress) {
		address = aAddress;
	}

	public void setIsLocal(boolean aIsLocal) {
		isLocal = aIsLocal;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public boolean getIsLocal() {
		return isLocal;
	}
	
	public boolean setUserId(Long id) {
		userId = id;
		return true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem()
	{
		return libraryApplicationSystem;
	}
	public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem) {
		boolean wasSet = false;
		if (aLibraryApplicationSystem == null) {
			return wasSet;
		}
		LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
		libraryApplicationSystem = aLibraryApplicationSystem;
		if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem)) {
			existingLibraryApplicationSystem.removeUser(this);
		}
		libraryApplicationSystem.addUser(this);
		wasSet = true;
		return wasSet;
	}
	public void delete() {
		LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
		this.libraryApplicationSystem = null;
		if(placeholderLibraryApplicationSystem != null) {
			placeholderLibraryApplicationSystem.removeUser(this);
		}
	}
	
	public String toString() {
		return "Id: " + userId + " Name: " + firstName + " " + lastName + " Address: " + address + " Local: " + isLocal;
	}
}
