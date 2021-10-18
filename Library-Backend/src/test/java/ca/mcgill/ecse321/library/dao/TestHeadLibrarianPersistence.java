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
		headLibrarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		String firstName = "Parker";
		String lastName = "Peter";
		String address = "123 Street";
		String username = "TestLibrarian";
		String password = "11454";
		String email = "Peter@gmail.com";
		boolean isLocal = true;
		HeadLibrarian headLibrarian = new HeadLibrarian();
		//Need to set userId? No method to set userId.
		headLibrarian.setFirstName(firstName);
		headLibrarian.setLastName(lastName);
		headLibrarian.setAddress(address);
		headLibrarian.setUsername(username);
		headLibrarian.setPassword(password);
		headLibrarian.setIsLocal(isLocal);
		headLibrarian.setEmail(email);
		
		headLibrarianRepository.save(headLibrarian);
		Long userId = headLibrarian.getUserId();
		
		headLibrarian = null;
		
		headLibrarian = headLibrarianRepository.findHeadLibrarianByUserId(userId);
		assertNotNull(headLibrarian);
		assertEquals(userId, headLibrarian.getUserId());
		assertEquals(firstName, headLibrarian.getFirstName());
		assertEquals(lastName, headLibrarian.getLastName());
		assertEquals(address, headLibrarian.getAddress());
		assertEquals(username, headLibrarian.getUsername());
		assertEquals(password, headLibrarian.getPassword());
		assertEquals(email, headLibrarian.getEmail());
		assertEquals(isLocal, headLibrarian.getIsLocal());
	}

}
