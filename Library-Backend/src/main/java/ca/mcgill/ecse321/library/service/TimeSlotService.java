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
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date startDate, Date endDate) throws IllegalArgumentException {
		if (startTime == null || endTime == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException("Cannot create TimeSlot without specified start and end Time and Date.");
		}
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}
	
	@Transactional
	public TimeSlot updateTimeSlot(Long timeSlotId, Time startTime, Time endTime, Date startDate, Date endDate) throws IllegalArgumentException{
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		if (timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
			
		}
		if (startTime == null || endTime == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException("Cannot update TimeSlot without specified start and end Time and Date");
		}
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
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
