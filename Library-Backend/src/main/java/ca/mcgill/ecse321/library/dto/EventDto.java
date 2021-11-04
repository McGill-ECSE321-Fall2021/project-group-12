package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

public class EventDto { 
	
	private String name;
	private Boolean isPrivate;
	private Boolean isAccepted;
	private TimeSlotDto timeSlot;
	private UserDto user;
	private Long eventId;
	
	
	public EventDto() {
	}
	
	
	public EventDto(String name, Boolean isPrivate, Boolean isAccepted, TimeSlotDto timeSlotDto, UserDto user, Long eventId) {
		this.name = name;
		this.isPrivate = isPrivate;
		this.isAccepted = isAccepted;
		this.timeSlot= timeSlot;
		this.user = user;
		this.eventId = eventId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String eventName) {
		this.name= eventName;
	}
	
	public Long getEventId() {
		return this.eventId;
	}
	
	
	public void setEventId(Long eventId) {
		this.eventId= eventId;
	}
	
	public Boolean getIsPrivate() {
		return this.isPrivate;
	}
	
	
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public Boolean getIsAccepted() {
		return this.isAccepted;
	}
	
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public TimeSlotDto getTimeSlot() {
		return this.timeSlot;
	}
	
	
	public void setTimeSlot(TimeSlotDto timeSlot) {
		this.timeSlot= timeSlot;
	}
	
	public UserDto getUser() {
		return this.user;
	}
	
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	

}
