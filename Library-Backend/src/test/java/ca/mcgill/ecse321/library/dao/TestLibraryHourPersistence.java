package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("12:00:00");
		Day day = Day.Monday;
		LibraryHour libraryHour = new LibraryHour();
		libraryHour.setStartTime(startTime);
		libraryHour.setEndTime(endTime);
		libraryHour.setDay(day);
		libraryHourRepository.save(libraryHour);
		Long libraryHourId = libraryHour.getLibraryHourId();
		
		libraryHour = null;
		
		libraryHour = libraryHourRepository.findLibraryHourByLibraryHourId(libraryHourId);
		assertNotNull(libraryHour);
		assertEquals(startTime, libraryHour.getStartTime());
		assertEquals(endTime, libraryHour.getEndTime());
		assertEquals(day, libraryHour.getDay());
	}
}
