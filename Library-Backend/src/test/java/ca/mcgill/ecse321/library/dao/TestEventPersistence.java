package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventPersistence {
	
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private LibrarianRepository librarianRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
		librarianRepository.deleteAll();
		timeSlotRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "Parker";
		String lastName = "Peter";
		String address = "123 Street";
		String username = "TestLibrarian";
		String password = "11454";
		String email = "Peter@gmail.com";
		boolean isLocal = true;
		Librarian librarian = new Librarian();
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
		librarian.setAddress(address);
		librarian.setUsername(username);
		librarian.setPassword(password);
		librarian.setIsLocal(isLocal);
		librarian.setEmail(email);
		librarian.setLibraryApplicationSystem(system);
		librarianRepository.save(librarian);
		
		String name = "ECSE321 Event";
		boolean isPrivate = true;
		
		TimeSlot timeSlot = new TimeSlot();
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("17:00:00");
		Date startDate = Date.valueOf("2021-10-31");
		Date endDate = Date.valueOf("2021-11-01");
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setLibraryApplicationSystem(system);
		timeSlotRepository.save(timeSlot);
		
		Event event = new Event();
		event.setName(name);
		event.setIsPrivate(isPrivate);
		event.setTimeSlot(timeSlot);
		event.setUser(librarian);
		event.setLibraryApplicationSystem(system);
		eventRepository.save(event);
		Long id = event.getEventId();
		
		event = null;
		event = eventRepository.findEventByEventId(id);
		
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(isPrivate, event.getIsPrivate());
		assertNotNull(event.getTimeSlot());
		assertNotNull(event.getUser());
		assertNotNull(event.getLibraryApplicationSystem());
		
	}

}
