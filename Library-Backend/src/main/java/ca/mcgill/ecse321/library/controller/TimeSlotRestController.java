package ca.mcgill.ecse321.library.controller;

import java.sql.Date;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.service.TimeSlotService;


@CrossOrigin(origins = "*")
@RestController
public class TimeSlotRestController {

	
@Autowired
private TimeSlotService timeSlotService;
	


@GetMapping(value = { "/timeSlots", "/timeSlots/" })
public List<TimeSlotDto> getAllTimeSlots(){
	return timeSlotService.getAllTimeSlots().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
}


@GetMapping(value = { "/timeSlot/{timeSlotId}", "/timeSlot/{timeSlotId}/" })
public TimeSlotDto getTimeSlot(@PathVariable("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
	return convertToDto(timeSlotService.getTimeSlot(timeSlotId));
}


@PostMapping(value = { "/timeSlot/create", "/timeSlot/create/" })
public TimeSlotDto createTimeSlot(@RequestParam(value = "startTime") String startTime,@RequestParam(value="endTime") String endTime, @RequestParam(value="startDate") String startDate, @RequestParam(value="endDate") String endDate) throws IllegalArgumentException{
	TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime, endTime, startDate, endDate);
	return convertToDto(timeSlot);
}


@PutMapping(value = { "/timeSlot/update/{timeSlotId}", "/timeSlot/update/{timeSlotId}/" })
public TimeSlotDto updateTimeSlot(@PathVariable("timeSlotId") Long timeSlotId,@RequestParam(value = "startTime") String startTime,@RequestParam(value="endTime") String endTime, @RequestParam(value="startDate") String startDate, @RequestParam(value="endDate") String endDate) throws IllegalArgumentException{
		TimeSlot timeSlot = timeSlotService.updateTimeSlot(timeSlotId, startTime, endTime, startDate, endDate);
		return convertToDto(timeSlot);
}

@DeleteMapping(value = { "/timeSlot/delete/{timeSlotId}", "/timeSlot/delete/{timeSlotId}/" })
public TimeSlotDto deleteTimeSlot(@PathVariable("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
	TimeSlot timeSlot = timeSlotService.getTimeSlot(timeSlotId);
	TimeSlotDto timeSlotDto = convertToDto(timeSlot);
	timeSlotService.deleteTimeSlot(timeSlotId);
	return timeSlotDto;
}


private TimeSlotDto convertToDto(TimeSlot timeSlot) {
	
	if (timeSlot == null) {
		throw new IllegalArgumentException("Timeslot does not exist");
	}
	
	TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime().toString(), timeSlot.getEndTime().toString(), timeSlot.getStartDate().toString(), timeSlot.getEndDate().toString(), timeSlot.getTimeSlotId());
	return timeSlotDto;
}
	
}
