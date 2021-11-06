package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.model.TimeSlot;

@Service
public class TimeSlotService {

	
	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	@Transactional
	public TimeSlot createTimeSlot(String startTime, String endTime, String startDate, String endDate) throws IllegalArgumentException {
		
		if (startTime == null || startTime.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty start time.");
		} 
		if (endTime == null || endTime.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty end time.");
		}
		if(startDate == null || startDate.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty start date.");
		}
		if (endDate == null || endDate.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty end date.");
		}
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}
	
	@Transactional
	public TimeSlot updateTimeSlot(Long timeSlotId, String startTime, String endTime, String startDate, String endDate) throws IllegalArgumentException{
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		if (timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		if (startTime == null || startTime.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty start time.");
		} 
		if (endTime == null || endTime.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty end time.");
		}
		if(startDate == null || startDate.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty start date.");
		}
		if (endDate == null || endDate.trim().length()==0) {
			throw new IllegalArgumentException("A TimeSlot cannot have an empty end date.");
		}
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setStartDate(Date.valueOf(startDate));
		timeSlot.setEndDate(Date.valueOf(endDate));
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}
	
	@Transactional
	public TimeSlot deleteTimeSlot(Long timeSlotId) throws IllegalArgumentException{
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		if (timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		timeSlotRepository.delete(timeSlot);
		return timeSlot;
		
	}
	
	@Transactional
	public TimeSlot getTimeSlot(Long timeSlotId) throws IllegalArgumentException{
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		if(timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		return timeSlot;
	}
	
	@Transactional
	public List<TimeSlot> getAllTimeSlots() {
		return toList(timeSlotRepository.findAll());
	}
	
	
	public List<TimeSlot> toList(Iterable<TimeSlot> iterable){
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		for (TimeSlot t:iterable) {
			timeSlots.add(t);
		}
		return timeSlots;
	}
	
}
