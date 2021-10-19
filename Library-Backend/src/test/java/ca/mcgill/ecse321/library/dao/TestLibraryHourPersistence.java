package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryHourPersistence {
	
	@Autowired
	private LibraryHourRepository libraryHourRepository;
	
	@AfterEach
	public void clearDatabase() {
		libraryHourRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadLibraryHour() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		//initializing attributes for timeslot
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("12:00:00");
		Day day = Day.Monday;
		//instantiating library hour
		LibraryHour libraryHour = new LibraryHour();
		//setting attributes
		libraryHour.setStartTime(startTime);
		libraryHour.setEndTime(endTime);
		libraryHour.setDay(day);
		libraryHour.setLibraryApplicationSystem(system);
		//save libraryhour in persistence
		libraryHourRepository.save(libraryHour);
		Long libraryHourId = libraryHour.getLibraryHourId();
		
		libraryHour = null;
		
		//finding libraryhour in persistence
		libraryHour = libraryHourRepository.findLibraryHourByLibraryHourId(libraryHourId);
		//testing if object persisted
		assertNotNull(libraryHour);
		assertNotNull(libraryHour.getStartTime());
		assertNotNull(libraryHour.getEndTime());
		assertNotNull(libraryHour.getDay());
		assertNotNull(libraryHour.getLibraryApplicationSystem());
	}
}
