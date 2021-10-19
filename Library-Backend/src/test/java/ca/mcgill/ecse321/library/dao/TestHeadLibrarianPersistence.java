package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHeadLibrarianPersistence {
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		//clearing database after each test
		headLibrarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		//initializing attributes for head librarian 
		String firstName = "Parker";
		String lastName = "Peter";
		String address = "123 Street";
		String username = "TestLibrarian";
		String password = "11454";
		String email = "Peter@gmail.com";
		boolean isLocal = true;
		//instantiating head librarian 
		HeadLibrarian headLibrarian = new HeadLibrarian();
		//setting attributes
		headLibrarian.setFirstName(firstName);
		headLibrarian.setLastName(lastName);
		headLibrarian.setAddress(address);
		headLibrarian.setUsername(username);
		headLibrarian.setPassword(password);
		headLibrarian.setIsLocal(isLocal);
		headLibrarian.setEmail(email);
		headLibrarian.setLibraryApplicationSystem(system);
		
		//saving object headLibrarian
		headLibrarianRepository.save(headLibrarian);
		Long userId = headLibrarian.getUserId();
		
		headLibrarian = null;
		
		//finding head librarian in persistence
		headLibrarian = headLibrarianRepository.findHeadLibrarianByUserId(userId);
		//testing if head librarian persisted
		assertNotNull(headLibrarian);
		assertEquals(userId, headLibrarian.getUserId());
		assertEquals(firstName, headLibrarian.getFirstName());
		assertEquals(lastName, headLibrarian.getLastName());
		assertEquals(address, headLibrarian.getAddress());
		assertEquals(username, headLibrarian.getUsername());
		assertEquals(password, headLibrarian.getPassword());
		assertEquals(email, headLibrarian.getEmail());
		assertEquals(isLocal, headLibrarian.getIsLocal());
		assertNotNull(headLibrarian.getLibraryApplicationSystem());
	}

}
