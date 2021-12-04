package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestReservationPersistence {
	
	@Autowired
	private ReservationRepository reservationRepository;

	@AfterEach
	public void clearDatabase() {
		reservationRepository.deleteAll(); //After methods clear database
	}
	
	@Test
	public void testPersistAndLoadReservation() {
		LibraryApplicationSystem system = new LibraryApplicationSystem(); //Create new library system
		Reservation reservation = new Reservation(); //Create new reservation object
		
		reservation.setLibraryApplicationSystem(system);
	
		reservationRepository.save(reservation); //Save reservation object
		Long id = reservation.getReservationId(); //Get reservation object ID
		
		reservation = null;
		
		reservation = reservationRepository.findReservationByReservationId(id); //Find reservation in database
		
		//Verify proper creation of object in database
		assertNotNull(reservation);
		assertEquals(id, reservation.getReservationId());
		assertNotNull(reservation.getLibraryApplicationSystem());
	}

}
