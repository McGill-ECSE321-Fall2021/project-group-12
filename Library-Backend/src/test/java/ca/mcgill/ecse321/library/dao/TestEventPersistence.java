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
	
	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		Event event = new Event();
		
		String name = "Party";
		boolean isPrivate = false;
		
		event.setName(name);
		event.setIsPrivate(isPrivate);
		event.setLibraryApplicationSystem(system);
		
		eventRepository.save(event);
		Long id = event.getEventId();
		
		event = null;
		event = eventRepository.findEventByEventId(id);
		
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(isPrivate, event.getIsPrivate());
		assertNotNull(event.getLibraryApplicationSystem());
		
	}

}
