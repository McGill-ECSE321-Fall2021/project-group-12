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
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int quantityAvailable = 5;
		int quantity = 12;
		int numPages = 400;
		BMGenre genre = Book.BMGenre.Action;
		Creator creator = new Creator();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		creatorRepository.save(creator);
		
		Book book = new Book();
		
		book.setTitle(title);
		book.setIsArchive(isArchive);
		book.setIsReservable(isReservable);
		book.setReleaseDate(releaseDate);
		book.setQuantityAvailable(quantityAvailable);
		book.setQuantity(quantity);
		book.setNumPages(numPages);
		book.setCreator(creator);
		book.setGenre(genre);
		book.setLibraryApplicationSystem(system);
	
		bookRepository.save(book);
		Long id = book.getItemId();
		
		book = null;
		
		
		book = bookRepository.findBookByItemId(id);
		
		assertNotNull(book);
		assertEquals(id, book.getItemId());
		
		
		assertEquals(title, book.getTitle());
		assertEquals(isArchive, book.getIsArchive());
		assertEquals(isReservable,book.getIsReservable());
		assertNotNull(book.getReleaseDate());
		assertEquals(quantityAvailable, book.getQuantityAvailable());
		assertEquals(quantity, book.getQuantity());
		assertEquals(numPages, book.getNumPages());
		assertNotNull(book.getCreator());
		assertEquals(genre, book.getGenre());
		assertNotNull(book.getLibraryApplicationSystem());
	}

}
