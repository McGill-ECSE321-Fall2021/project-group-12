package ca.mcgill.ecse321.library.dto;

import java.sql.Date;
import java.sql.Time;


public class TimeSlotDto {
	
	private Time startTime;
	private Time endTime;
	private Date startDate;
	private Date endDate;
	private Long timeSlotId;
	
	public TimeSlotDto() {
		
	}
	
	public TimeSlotDto(Time startTime, Time endTime, Date startDate,Date endDate, Long timeSlotId) {
		this.startTime=startTime;
		this.endTime = endTime;
		this.startDate=startDate;
		this.endDate=endDate;
		this.timeSlotId = timeSlotId;
	}
	
	public Time getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Time getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate= startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Long getTimeSlotId() {
		return this.timeSlotId;
	}
	
	public void setTimeSlotId(Long timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
	

}