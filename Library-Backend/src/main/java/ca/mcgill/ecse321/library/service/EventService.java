package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.EventRepository;

import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
@Service
public class EventService {
	
	
	@Autowired 
	EventRepository eventRepository;
	
	@Transactional
	public Event createEvent(String name, TimeSlot timeSlot, Boolean isPrivate, User user) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("An Event cannot have an empty name.");
		}
		if (timeSlot == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty timeSlot.");
		}
		if (user == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty user.");
		}
		Event event = new Event();
		event.setName(name);
		event.setTimeSlot(timeSlot);
		event.setIsPrivate(isPrivate);
		event.setUser(user);
		eventRepository.save(event);
		return event;
	}
	
	@Transactional
	public Event getEvent(Long id) {
		Event event = eventRepository.findEventByEventId(id);
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist.");
		}
		return event;
	}
	
	@Transactional
	public Event updateEvent(Long id, String name, TimeSlot timeSlot, Boolean isPrivate, User user) {
		Event event = eventRepository.findEventByEventId(id);
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist.");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("An Event cannot have an empty name.");
		}
		if (timeSlot == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty timeSlot.");
		}
		if (user == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty user.");
		}
		
		event.setName(name);
		event.setTimeSlot(timeSlot);
		event.setIsPrivate(isPrivate);
		event.setUser(user);
		
		return event;
	}
	
	@Transactional
	public Boolean deleteEvent(Long id) {
		Event event = eventRepository.findEventByEventId(id);
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist.");
		}
		eventRepository.delete(event);
		return true;
	}
	
	@Transactional
	public List<Event> getAllEvents(){
		return toList(eventRepository.findAll());
	}

	
	private List<Event> toList(Iterable<Event> iterable){
		List<Event> events = new ArrayList<Event>();
		for (Event e:iterable) {
			events.add(e);
		}
		return events;
	}
}
