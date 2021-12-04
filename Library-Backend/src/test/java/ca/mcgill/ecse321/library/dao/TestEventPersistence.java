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
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;

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
		
		// Create Event object
		Event event = new Event(); 
		
		// Define attributes
		String name = "Party";
		boolean isPrivate = false;
		
		// Set attributes
		event.setName(name);
		event.setIsPrivate(isPrivate);
		event.setLibraryApplicationSystem(system);
		
		// Save object
		eventRepository.save(event);
		
		// Save Id
		Long id = event.getEventId();
		
		// Set object to null
		event = null; 
		
		// Retrieve object using Id.
		event = eventRepository.findEventByEventId(id);
	
		// Verify the object has been retrieved
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(isPrivate, event.getIsPrivate());
		assertNotNull(event.getLibraryApplicationSystem());
		
	}

}
