package ca.mcgill.ecse321.library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.service.EventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.library.dto.EventDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.dto.UserDto;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

@CrossOrigin(origins = "*")
@RestController
public class EventRestController {

	
	@Autowired
	private EventService service;
	
	
	@GetMapping(value = { "/events", "/events/" })
	public List<EventDto> getAllEvents(){
		return service.getAllEvents().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	
	@GetMapping(value = { "/event/{eventId}", "/event/{eventId}/" })
	public EventDto getEvent(@PathVariable("eventId") Long eventId) throws IllegalArgumentException {
		return convertToDto(service.getEvent(eventId));
	}
	
	
	@PostMapping(value = { "/event/create", "/event/create/" })
	public EventDto createEvent(@RequestParam(value="name") String name, @RequestParam(value="timeSlot") TimeSlot timeSlot, @RequestParam(value="isPrivate") Boolean isPrivate, @RequestParam(value="isAccepted") Boolean isAccepted, @RequestParam(value="user") User user) throws IllegalArgumentException {
		Event event = service.createEvent(name, timeSlot, isPrivate, isAccepted, user);
		return convertToDto(event);
	}
	
	
	@PutMapping(value = {"/event/update/{Id}", "/newspaper/update/{Id}/"})
	public EventDto updateEvent(@PathVariable("Id") Long Id, @RequestParam(value="name") String name, @RequestParam(value="timeSlot") TimeSlot timeSlot, @RequestParam(value="isPrivate") Boolean isPrivate, @RequestParam(value="isAccepted") Boolean isAccepted, @RequestParam(value="user") User user) throws IllegalArgumentException {
		return convertToDto(service.updateEvent(Id, name, timeSlot, isPrivate, isAccepted, user));
	}
	
	
	@DeleteMapping(value = {"/newspaper/delete/{Id}", "/newspaper/delete/{Id}/"})
	public EventDto deleteEvent(@PathVariable("Id") Long Id) throws IllegalArgumentException {
		Event event = service.getEvent(Id);
		EventDto  eventDto = convertToDto(event);
		service.deleteEvent(Id);
		return eventDto;
	}
	
	
	private EventDto convertToDto(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist");
		}
		EventDto eventDto = new EventDto(event.getName(), event.getIsPrivate() , event.getIsAccepted(), convertToDto(event.getTimeSlot()), convertToDto(event.getUser()), event.getEventId());
		return eventDto;
	}
	
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		return timeSlotDto;
	}
	
	private UserDto convertToDto(User user) {
		if (user == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		UserDto userDto = new UserDto();
		return userDto;
	}

}