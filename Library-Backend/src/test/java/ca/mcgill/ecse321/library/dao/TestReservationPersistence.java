package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.LibraryApplication;
import ca.mcgill.ecse321.library.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestReservationPersistence {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	private LibraryApplicationSystem system = new LibraryApplicationSystem();

	@AfterEach
	public void clearDatabase() {
		
		reservationRepository.deleteAll();
		system = new LibraryApplicationSystem();
	}
	
	@Test
	public void testPersistAndLoadReservation() {
		Reservation reservation = new Reservation();
		
		reservation.setLibraryApplicationSystem(system);
	
		reservationRepository.save(reservation);
		Long id = reservation.getReservationId();
		
		reservation = null;
		
		
		reservation = reservationRepository.findReservationByReservationId(id);
		
		assertNotNull(reservation);
		assertEquals(id, reservation.getReservationId());
		assertEquals(system, reservation.getLibraryApplicationSystem());
	}

}
