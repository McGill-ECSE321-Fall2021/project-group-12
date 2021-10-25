package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianPersistence {
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		librarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		//initializing attributes for librarian 
		String firstName = "Parker";
		String lastName = "Peter";
		String address = "123 Street";
		String username = "TestLibrarian";
		String password = "11454";
		String email = "Peter@gmail.com";
		boolean isLocal = true;
		boolean isHead = true;
		//instantiating librarian 
		Librarian librarian = new Librarian();
		//setting attributes
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
		librarian.setAddress(address);
		librarian.setUsername(username);
		librarian.setPassword(password);
		librarian.setIsLocal(isLocal);
		librarian.setEmail(email);
		librarian.setLibraryApplicationSystem(system);
		librarian.setIsHead(isHead);
		
		//saving object
		librarianRepository.save(librarian);
		Long userId = librarian.getUserId();
		
		librarian = null;
		//finding librarian in persistence
		librarian = librarianRepository.findLibrarianByUserId(userId);
		//testing if librarian persisted
		assertNotNull(librarian);
		assertEquals(userId, librarian.getUserId());
		assertEquals(firstName, librarian.getFirstName());
		assertEquals(lastName, librarian.getLastName());
		assertEquals(address, librarian.getAddress());
		assertEquals(username, librarian.getUsername());
		assertEquals(password, librarian.getPassword());
		assertEquals(email, librarian.getEmail());
		assertEquals(isLocal, librarian.getIsLocal());
		assertEquals(isHead, librarian.getIsHead());
		assertNotNull(librarian.getLibraryApplicationSystem());
	}

}
