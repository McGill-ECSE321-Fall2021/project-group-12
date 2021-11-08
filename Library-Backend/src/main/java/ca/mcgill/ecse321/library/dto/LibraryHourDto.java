package ca.mcgill.ecse321.library.dto;

import java.sql.Time;

import ca.mcgill.ecse321.library.model.LibraryHour.Day;

public class LibraryHourDto {
	
	private Long libraryHourId;
	private Time startTime;
	private Time endTime;
	private Day day;
	
	public LibraryHourDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public LibraryHourDto(Time startTime, Time endTime, Day day, Long libraryHourId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.libraryHourId = libraryHourId;
	}
	
	public Long getLibraryHourId() {
		return this.libraryHourId;
	}
	
	public void setLibraryHourId(Long Id) {
		this.libraryHourId = Id;
	}
	
	public void setStartTime(Time startTime) {
	    this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setDay(Day day) {
	    this.day = day;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public Day getDay() {
		return this.day;
	}
	
}
