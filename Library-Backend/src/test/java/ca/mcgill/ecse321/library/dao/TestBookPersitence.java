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
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Book.BMGenre;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestBookPersitence {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CreatorRepository creatorRepository;

	@AfterEach
	public void clearDatabase() {
		
		bookRepository.deleteAll();
		creatorRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadBook() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		
		// Define Book attributes
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int numPages = 400;
		boolean available = true;
		BMGenre genre = Book.BMGenre.Action;
		
		// Create Creator object 
		Creator creator = new Creator();
		
		// Define Creator attributes
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		
		// Save Creator object
		creatorRepository.save(creator);
		
		// Create Book object
		Book book = new Book();
		
		// Set book attributes
		book.setTitle(title);
		book.setIsArchive(isArchive);
		book.setIsReservable(isReservable);
		book.setReleaseDate(releaseDate);
		book.setIsAvailable(available);
		book.setNumPages(numPages);
		book.setCreator(creator);
		book.setGenre(genre);
		book.setLibraryApplicationSystem(system);
	
		// Save Book object
		bookRepository.save(book);
		
		// Save Id
		Long id = book.getItemId();
		
		// Set object to null
		book = null;
		
		// Retrieve object using Id
		book = bookRepository.findBookByItemId(id);
		
		// Verify the object has been retrieved
		assertNotNull(book);
		assertEquals(id, book.getItemId());
		assertEquals(title, book.getTitle());
		assertEquals(isArchive, book.getIsArchive());
		assertEquals(isReservable,book.getIsReservable());
		assertNotNull(book.getReleaseDate());
		assertEquals(available, book.getIsAvailable());
		assertEquals(numPages, book.getNumPages());
		assertNotNull(book.getCreator());
		assertEquals(genre, book.getGenre());
		assertNotNull(book.getLibraryApplicationSystem());
	}

}
