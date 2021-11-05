package ca.mcgill.ecse321.library.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.library.model.Creator.CreatorType;

public class CreatorDto {
	
	private String firstName;
	private String lastName;
	private Long creatorId;
	private CreatorType creatorType;
	
	public CreatorDto() {}
	
	public CreatorDto(String firstName, String lastName, CreatorType creatorType, Long creatorId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creatorType = creatorType;
		this.creatorId = creatorId;
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
	
	public CreatorType getCreatorType() {
		return this.creatorType;
	}
	
	public void setCreatorType(CreatorType creatorType) {
		this.creatorType = creatorType;
	}
}
