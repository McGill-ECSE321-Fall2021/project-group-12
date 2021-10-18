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
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Book.BMGenre;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestBookPersitence {
	
	@Autowired
	private BookRepository bookRepository;
	
	private LibraryApplicationSystem system = new LibraryApplicationSystem();

	@AfterEach
	public void clearDatabase() {
		
		bookRepository.deleteAll();
		system = new LibraryApplicationSystem();
	}
	
	@Test
	public void testPersistAndLoadBook() {
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int quantityAvailable = 5;
		int quantity = 12;
		int numPages = 400;
		BMGenre genre = Book.BMGenre.Action;
		Creator creator = new Creator();
		
		Book book = new Book();
		
		book.setTitle(title);
		book.setIsArchive(isArchive);
		book.setIsReservable(isReservable);
		book.setReleaseDate(releaseDate);
		book.setQuantity(quantityAvailable);
		book.setQuantity(quantity);
		book.setNumPages(numPages);
		book.setCreator(creator);
		book.setGenre(genre);
		book.setLibraryApplicationSystem(system);
	
		bookRepository.save(book);
		Long id = book.getItemId();
		
		book = null;
		
		
		book = bookRepository.findBookByBookId(id);
		
		assertNotNull(creator);
		assertEquals(id, book.getItemId());
		
		
		assertEquals(title, book.getTitle());
		assertEquals(isArchive, book.getIsArchive());
		assertEquals(isReservable,book.getIsReservable());
		assertEquals(releaseDate, book.getReleaseDate());
		assertEquals(quantityAvailable, book.getQuantityAvailable());
		assertEquals(quantity, book.getQuantity());
		assertEquals(numPages, book.getNumPages());
		assertEquals(creator, book.getCreator());
		assertEquals(genre, book.getGenre());
		assertEquals(book.toString(), book.toString());
		assertEquals(system, book.getLibraryApplicationSystem());
	}

}
