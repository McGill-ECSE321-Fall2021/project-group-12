package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import ca.mcgill.ecse321.library.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventPersistence {
	
	@Autowired
	private EventRepository eventRepository;
	
	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		String name = "ECSE321 Event";
		boolean isPrivate = true;
		TimeSlot timeSlot = new TimeSlot();
		Librarian user = new Librarian();
		LibraryApplicationSystem lAS = new LibraryApplicationSystem();
		
		Event event = new Event();
		event.setName(name);
		event.setIsPrivate(isPrivate);
		event.setTimeSlot(timeSlot);
		event.setUser((User)user);
		event.setLibraryApplicationSystem(lAS);
		eventRepository.save(event);
		Long id = event.getEventId();
		
		event = null;
		event = eventRepository.findEventByEventId(id);
		
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(timeSlot, event.getTimeSlot());
		assertEquals((User)user, event.getUser());
		assertEquals(lAS, event.getLibraryApplicationSystem());
		
	}

}
