package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

public class EventDto { 
	
	private String name;
	private Boolean isPrivate;
	private TimeSlotDto timeSlot;
	private UserDto user;
	private Long eventId;
	
	
	public EventDto() {
	}
	
	
	public EventDto(String name, Boolean isPrivate, TimeSlotDto timeSlotDto, UserDto user, Long eventId) {
		this.name = name;
		this.isPrivate = isPrivate;
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
