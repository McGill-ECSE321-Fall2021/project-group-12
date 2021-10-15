package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 86 "library.ump"
public class Creator
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum CreatorType { Director, Author, Artist, Publisher }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Creator Attributes
	private CreatorType creatorType;

	//Creator Associations
	private List<Item> items;
	private LibraryApplicationSystem libraryApplicationSystem;


	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Creator(CreatorType aCreatorType, LibraryApplicationSystem aLibraryApplicationSystem)
	{
		creatorType = aCreatorType;
		items = new ArrayList<Item>();
		boolean didAddLibraryApplicationSystem = setLibraryApplicationSystem(aLibraryApplicationSystem);
		if (!didAddLibraryApplicationSystem)
		{
			throw new RuntimeException("Unable to create creator due to libraryApplicationSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setCreatorType(CreatorType aCreatorType)
	{
		boolean wasSet = false;
		creatorType = aCreatorType;
		wasSet = true;
		return wasSet;
	}

	public CreatorType getCreatorType()
	{
		return creatorType;
	}
	/* Code from template association_GetMany */
	public Item getItem(int index)
	{
		Item aItem = items.get(index);
		return aItem;
	}

	public List<Item> getItems()
	{
		List<Item> newItems = Collections.unmodifiableList(items);
		return newItems;
	}

	public int numberOfItems()
	{
		int number = items.size();
		return number;
	}

	public boolean hasItems()
	{
		boolean has = items.size() > 0;
		return has;
	}

	public int indexOfItem(Item aItem)
	{
		int index = items.indexOf(aItem);
		return index;
	}
	public LibraryApplicationSystem getLibraryApplicationSystem()
	{
		return libraryApplicationSystem;
	}
	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfItems()
	{
		return 0;
	}
	/* Code from template association_AddManyToOne */


	public boolean addItem(Item aItem)
	{
		boolean wasAdded = false;
		if (items.contains(aItem)) { return false; }
		Creator existingCreator = aItem.getCreator();
		boolean isNewCreator = existingCreator != null && !this.equals(existingCreator);
		if (isNewCreator)
		{
			aItem.setCreator(this);
		}
		else
		{
			items.add(aItem);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeItem(Item aItem)
	{
		boolean wasRemoved = false;
		//Unable to remove aItem, as it must always have a creator
		if (!this.equals(aItem.getCreator()))
		{
			items.remove(aItem);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	public boolean addItemAt(Item aItem, int index)
	{  
		boolean wasAdded = false;
		if(addItem(aItem))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfItems()) { index = numberOfItems() - 1; }
			items.remove(aItem);
			items.add(index, aItem);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveItemAt(Item aItem, int index)
	{
		boolean wasAdded = false;
		if(items.contains(aItem))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfItems()) { index = numberOfItems() - 1; }
			items.remove(aItem);
			items.add(index, aItem);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addItemAt(aItem, index);
		}
		return wasAdded;
	}

	public boolean setLibraryApplicationSystem(LibraryApplicationSystem aLibraryApplicationSystem)
	{
		boolean wasSet = false;
		if (aLibraryApplicationSystem == null)
		{
			return wasSet;
		}
		LibraryApplicationSystem existingLibraryApplicationSystem = libraryApplicationSystem;
		libraryApplicationSystem = aLibraryApplicationSystem;
		if (existingLibraryApplicationSystem != null && !existingLibraryApplicationSystem.equals(aLibraryApplicationSystem))
		{
			existingLibraryApplicationSystem.removeCreator(this);
		}
		libraryApplicationSystem.addCreator(this);
		wasSet = true;
		return wasSet;
	}

	public void delete()
	{
		for(int i=items.size(); i > 0; i--)
		{
			Item aItem = items.get(i - 1);
			aItem.delete();
		}
	}


	public String toString()
	{
		return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
				"  " + "creatorType" + "=" + (getCreatorType() != null ? !getCreatorType().equals(this)  ? getCreatorType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "libraryApplicationSystem = "+(getLibraryApplicationSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryApplicationSystem())):"null");
	}
}