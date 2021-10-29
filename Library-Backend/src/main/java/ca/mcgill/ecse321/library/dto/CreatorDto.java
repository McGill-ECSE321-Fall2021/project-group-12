package ca.mcgill.ecse321.library.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.library.model.Creator.CreatorType;

public class CreatorDto {
	
	private String firstName;
	private String lastName;
	private String creatorName;
	private List<ItemDto> items;
	private CreatorType creatorType;
	
	public CreatorDto() {}
	
	@SuppressWarnings("unchecked")
	public CreatorDto(String firstName, String lastName, CreatorType creatorType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorType = creatorType;
		this.creatorName = lastName + firstName + creatorType;
		this.items = Collections.EMPTY_LIST;
	}
	
	public CreatorDto(String firstName, String lastName, CreatorType creatorType, List<ItemDto> items) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorName = lastName + firstName + creatorType;
		this.creatorType = creatorType;
		this.items = items;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCreatorName() {
		return this.creatorName;
	}
	
	public void setCreatorName(String firstName, String lastName, CreatorType creatorType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorType = creatorType;
		this.creatorName = lastName + firstName + creatorType;
	}
	
	public List<ItemDto> getItems(){
		return this.items;
	}
	
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	
	public CreatorType getCreatorType() {
		return this.creatorType;
	}
	
	public void setCreatorType(CreatorType creatorType) {
		this.creatorType = creatorType;
	}
	
	

}
