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
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestTimeSlotPersistence {
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		
		TimeSlot timeSlot = new TimeSlot();
		Long tId = timeSlot.getTimeSlotId();
		LibraryApplicationSystem lAS = new LibraryApplicationSystem();
		Event event = new Event();
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("17:00:00");
		Date startDate = Date.valueOf("2021-10-31");
		Date endDate = Date.valueOf("2021-11-01");
		
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setLibraryApplicationSystem(lAS);
		timeSlot.setEvent(event);
		
		timeSlotRepository.save(timeSlot);
		
		timeSlot = null;
		
		timeSlot = timeSlotRepository.findTimeSlotById(tId);
		
		
		assertNotNull(timeSlot);
		assertEquals(tId, timeSlot.getTimeSlotId());
		assertEquals(startTime, timeSlot.getStartTime());
		assertEquals(endTime, timeSlot.getEndTime());
		assertEquals(startDate, timeSlot.getStartDate());
		assertEquals(endDate, timeSlot.getEndDate());
		assertEquals(event, timeSlot.getEvent());
		assertEquals(lAS, timeSlot.getLibraryApplicationSystem());
		
		
		}
}
