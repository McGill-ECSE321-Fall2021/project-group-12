package ca.mcgill.ecse321.library.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.library.model.Creator.CreatorType;

public class CreatorDto {
	
	private String firstName;
	private String lastName;
	private Long creatorId;
	private List<ItemDto> items;
	private CreatorType creatorType;
	
	public CreatorDto() {}
	
	@SuppressWarnings("unchecked")
	public CreatorDto(String firstName, String lastName, CreatorType creatorType, Long creatorId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorType = creatorType;
		this.creatorId = creatorId;
		this.items = Collections.EMPTY_LIST;
	}
	
	public CreatorDto(String firstName, String lastName, CreatorType creatorType, Long creatorId, List<ItemDto> items) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorId = creatorId;
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
	
	public Long getCreatorId() {
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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