package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.LibraryApplication;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

import java.sql.Date;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class)
public class TestNewspaperPersistence {
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	private LibraryApplicationSystem system = new LibraryApplicationSystem();

	@AfterEach
	public void clearDatabase() {
		// Ordering matters here. Be careful.
		newspaperRepository.deleteAll();
		// make new system.
		system = new LibraryApplicationSystem();
	}

	@Test
	public void testPersistAndLoadNewspaper() {
        String title = "title";
        boolean isArchive = true;
        boolean isReservable = true;
        Date releaseDate = Date.valueOf(LocalDate.of(2021, 10, 17));
        int quantityAvailable = 1;
        int quantity = 1;
        Creator creator = null;
        Newspaper newspaper = new Newspaper();
        newspaper.setTitle(title);
        newspaper.setIsArchive(isArchive);
        newspaper.setIsReservable(isReservable);
        newspaper.setReleaseDate(releaseDate);
        newspaper.setQuantityAvailable(quantityAvailable);
        newspaper.setQuantity(quantity);
        newspaper.setLibraryApplicationSystem(system);
        newspaper.setCreator(new Creator());
		newspaperRepository.save(newspaper);
		Long id = newspaper.getNewspaperId();
		
		newspaper = null;
		
		
		newspaper = newspaperRepository.findByNewspaperId(id);
		
		assertNotNull(newspaper);
		assertEquals(id, newspaper.getNewspaperId());
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(isReservable, newspaper.getIsReservable());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertEquals(quantityAvailable, newspaper.getQuantityAvailable());
		assertEquals(quantity, newspaper.getQuantity());
		assertEquals(system, newspaper.getLibraryApplicationSystem());
		assertEquals(creator, newspaper.getCreator());
	}
}
