package ca.mcgill.ecse321.library.dto;

import java.util.List;

public class ReservationDto {
	
	private Long reservationId;
	private List<ItemDto> items;
	private UserDto user;
	private TimeSlotDto timeSlot;
	
	public ReservationDto() {
		
	}
	
	public ReservationDto(List<ItemDto> items, UserDto user, TimeSlotDto timeSlot, Long reservationId) {
		this.items = items;
		this.user = user;
		this.timeSlot = timeSlot;
		this.reservationId = reservationId;
	}
	
	public List<ItemDto> getItems() {
		return this.items;
	}
	
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	
	public TimeSlotDto getTimeSlot() {
		return this.timeSlot;
	}
	
	public void setTimeSlot(TimeSlotDto timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public Long getReservationId() {
		return this.reservationId;
	}
	
	public void setReservationId(Long Id) {
		this.reservationId = Id;
	}
	
	public UserDto getUser() {
		return this.user;
	}
	
	public void setUserDto(UserDto user) {
		this.user = user;
	}
}
