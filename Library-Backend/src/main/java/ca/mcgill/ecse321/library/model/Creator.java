package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Creator {


	public enum CreatorType { Director, Author, Artist, Publisher }	

	private CreatorType creatorType;

	private Long creatorId;
	private String firstName;
	private String lastName;
	private LibraryApplicationSystem libraryApplicationSystem;
	
	public boolean setFirstName(String first) {
		firstName = first;
		return true;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public boolean setLastName(String last) {
		lastName = last;
		return true;
	}
	
	public String getLastName() {
		return lastName;
	}

	public boolean setCreatorType(CreatorType type) {
		creatorType = type;
		return true;
	}

	public CreatorType getCreatorType() {
		return creatorType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCreatorId() {
		return creatorId;
	}
	
	public boolean setCreatorId(Long id) {
		creatorId = id;
		return true;
	}

	@ManyToOne(cascade= {CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem() {
		return libraryApplicationSystem;
	}

	public boolean setLibraryApplicationSystem(LibraryApplicationSystem system) {
		if (system == null) {
			return false;
		}
		system.addCreator(this);
		libraryApplicationSystem = system;
		return true;
	}

	public void delete() {
		LibraryApplicationSystem placeholderLibraryApplicationSystem = libraryApplicationSystem;
		this.libraryApplicationSystem = null;
		if(placeholderLibraryApplicationSystem != null) {
			placeholderLibraryApplicationSystem.removeCreator(this);
		}
	}


	public String toString()
	{
		return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
				"  " + "creatorType" + "=" + (getCreatorType() != null ? !getCreatorType().equals(this)  ? getCreatorType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
	}
}