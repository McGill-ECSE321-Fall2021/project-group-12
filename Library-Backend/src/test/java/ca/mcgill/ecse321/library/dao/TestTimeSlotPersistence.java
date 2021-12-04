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

import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.TimeSlot;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestTimeSlotPersistence {
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll(); //After methods clear database 
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		
		TimeSlot timeSlot = new TimeSlot(); //New timeslot object
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("17:00:00");
		Date startDate = Date.valueOf("2021-10-31");
		Date endDate = Date.valueOf("2021-11-01");
		
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setLibraryApplicationSystem(system);
		
		timeSlotRepository.save(timeSlot); //Save timeslot
		Long timeSlotId = timeSlot.getTimeSlotId(); //Get timeslot ID
		
		timeSlot = null;
		
		timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		
		//Verify proper creation in database
		assertNotNull(timeSlot);
		assertEquals(timeSlotId, timeSlot.getTimeSlotId());
		assertNotNull(timeSlot.getStartTime());
		assertNotNull(timeSlot.getEndTime());
		assertNotNull(timeSlot.getStartDate());
		assertNotNull(timeSlot.getEndDate());
		assertNotNull(timeSlot.getLibraryApplicationSystem());
		
		
		}
}
