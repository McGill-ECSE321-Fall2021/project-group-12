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
	@Autowired
	private CreatorRepository creatorRepository;

	@AfterEach
	public void clearDatabase() {
		newspaperRepository.deleteAll(); //After methods clear database
		creatorRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadNewspaper() {
		LibraryApplicationSystem system = new LibraryApplicationSystem(); //Create new library system
        String title = "title";
        boolean isArchive = true;
        boolean isReservable = true;
        Date releaseDate = Date.valueOf(LocalDate.of(2021, 10, 17));
        int quantityAvailable = 1;
        int quantity = 1;
        Creator creator = new Creator();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		creatorRepository.save(creator); //Save newspaper creator
		
        Newspaper newspaper = new Newspaper(); //Create newspaper object
        newspaper.setTitle(title);
        newspaper.setIsArchive(isArchive);
        newspaper.setIsReservable(isReservable);
        newspaper.setReleaseDate(releaseDate);
        newspaper.setQuantityAvailable(quantityAvailable);
        newspaper.setQuantity(quantity);
        newspaper.setLibraryApplicationSystem(system);
        newspaper.setCreator(creator);
        newspaper.setLibraryApplicationSystem(system);
		newspaperRepository.save(newspaper); //Save newspaper object
		Long id = newspaper.getItemId(); //Newspaper ID
		
		newspaper = null;
		
		newspaper = newspaperRepository.findByItemId(id);
		
		//Verify proper creation of object in database
		assertNotNull(newspaper);
		assertEquals(id, newspaper.getItemId());
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(isReservable, newspaper.getIsReservable());
		assertNotNull(newspaper.getReleaseDate());
		assertEquals(quantityAvailable, newspaper.getQuantityAvailable());
		assertEquals(quantity, newspaper.getQuantity());
		assertNotNull(newspaper.getCreator());
		assertNotNull(newspaper.getLibraryApplicationSystem());
	}
}
