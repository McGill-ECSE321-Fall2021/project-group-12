package ca.mcgill.ecse321.library.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Creator {


	public enum CreatorType { Director, Author, Artist, Publisher }	

	private CreatorType creatorType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long creatorId;
	private String firstName;
	private String lastName;
	private List<Item> items;
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

	public Long getCreatorId() {
		return creatorId;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	public List<Item> getItems() {
		return items;
	}

	public int numberOfItems() {
		return items.size();
	}

	public boolean hasItems() {
		return items.size() > 0;
	}

	@ManyToOne(cascade = {CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem() {
		return libraryApplicationSystem;
	}

	public boolean addItem(Item item) {
		if (items.contains(item)) { 
			return false;
		}
		item.setCreator(this);
		items.add(item);
		return true;
	}

	public boolean removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
			return true;
		}
		return false;
	}

	public boolean setLibraryApplicationSystem(LibraryApplicationSystem system) {
		if (libraryApplicationSystem == null) {
			return false;
		}
		libraryApplicationSystem = system;
		libraryApplicationSystem.addCreator(this);
		return true;
	}

	public void delete() {
		for(int i=items.size(); i > 0; i--)
		{
			Item aItem = items.get(i - 1);
			aItem.delete();
		}
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
