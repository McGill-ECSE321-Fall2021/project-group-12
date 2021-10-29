package ca.mcgill.ecse321.library.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class Creator {


	public enum CreatorType { Director, Author, Artist, Publisher }	

	private CreatorType creatorType;
	
	private String firstName;
	private String lastName;
	private String creatorName;
	private List<Item> items;
	private LibraryApplicationSystem libraryApplicationSystem;
	private Long creatorId;
	
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
	
	
	public String getCreatorName() {
		return creatorName;
	}
	
	public boolean setCreatorName() {
		if (this.getFirstName() == null || this.getLastName() == null || this.getCreatorType() == null) {
			return false;
		}
		creatorName = this.getLastName() + this.getFirstName() + this.getCreatorType().toString();
		return true;
	}
	
	public boolean setCreatorName(String creatorName) {
		String currentName = lastName + firstName + creatorType;
		if (creatorName == currentName) {
			this.creatorName = currentName;
			return true;
		}
		return false;
	}
	
	public boolean setCreatorName(String firstName, String lastName, CreatorType creatorType) {
		if (firstName == null || lastName == null || creatorType == null) {
			return false;
		}
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setCreatorType(creatorType);
		creatorName = this.getLastName() + this.getFirstName() + this.getCreatorType().toString();
		return true;
	}

	@OneToMany(orphanRemoval = true)
	public List<Item> getItems() {
		return items;
	}
	
	public boolean setItems(List<Item> newItems) {
		items = newItems;
		return true;
	}

	public int numberOfItems() {
		return items.size();
	}

	public boolean hasItems() {
		return items.size() > 0;
	}

	@ManyToOne(cascade= {CascadeType.ALL})
	public LibraryApplicationSystem getLibraryApplicationSystem() {
		return libraryApplicationSystem;
	}

	public boolean addItem(Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		if (items.contains(item)) { 
			return false;
		}
		if(!item.getCreator().equals(this)) {
			item.setCreator(this);
		}
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
		if (system == null) {
			return false;
		}
		system.addCreator(this);
		libraryApplicationSystem = system;
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
