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
@SpringBootTest
public class TestUserPersistence {
	
	@Autowired
	private OfflineUserRepository offlineUserRepository;
	@Autowired
	private OnlineUserRepository onlineUserRepository;
	
	@AfterEach
	public void clearDatabase() {
		offlineUserRepository.deleteAll(); //After each method clear database
	}
	
	@Test
	public void testPersistAndLoadOfflineUser() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser(); //New offline user object
		offlineUser.setFirstName(firstName);
		offlineUser.setLastName(lastName);
		offlineUser.setAddress(address);
		offlineUser.setIsLocal(isLocal);
		offlineUser.setLibraryApplicationSystem(system);
		offlineUserRepository.save(offlineUser); //Save offline user object
		Long id = offlineUser.getUserId();
		
		offlineUser = null;
		
		offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
		
		//Verify proper creation of object in database
		assertNotNull(offlineUser);
		assertEquals(firstName, offlineUser.getFirstName());
		assertEquals(lastName, offlineUser.getLastName());
		assertEquals(address, offlineUser.getAddress());
		assertEquals(isLocal, offlineUser.getIsLocal());
		assertNotNull(offlineUser.getLibraryApplicationSystem());
		assertEquals(id, offlineUser.getUserId());
		
	}
	
	@Test
	public void testPersistAndLoadOnlineUser() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		String username = "username";
		String password = "password";
		String email = "email@email.email";
		OnlineUser onlineUser = new OnlineUser(); //Create new online user object
		onlineUser.setFirstName(firstName);
		onlineUser.setLastName(lastName);
		onlineUser.setAddress(address);
		onlineUser.setIsLocal(isLocal);
		onlineUser.setLibraryApplicationSystem(system);
		onlineUser.setUsername(username);
		onlineUser.setPassword(password);
		onlineUser.setEmail(email);
		onlineUserRepository.save(onlineUser); //Save online user object
		Long id = onlineUser.getUserId(); //Get online ser ID
		
		onlineUser = null;
		
		onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
		
		//Verify proper creation of object in database
		assertNotNull(onlineUser);
		assertEquals(firstName, onlineUser.getFirstName());
		assertEquals(lastName, onlineUser.getLastName());
		assertEquals(address, onlineUser.getAddress());
		assertEquals(isLocal, onlineUser.getIsLocal());
		assertNotNull(onlineUser.getLibraryApplicationSystem());
		assertEquals(id, onlineUser.getUserId());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(password, onlineUser.getPassword());
		assertEquals(email, onlineUser.getEmail());
	}
	
	@Test
	public void testPersistAndLoadOnlineUserByUsername() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		String username = "username2";
		String password = "password";
		String email = "email2@email.email";
		OnlineUser onlineUser = new OnlineUser(); //Create new online user object
		onlineUser.setFirstName(firstName);
		onlineUser.setLastName(lastName);
		onlineUser.setAddress(address);
		onlineUser.setIsLocal(isLocal);
		onlineUser.setLibraryApplicationSystem(system);
		onlineUser.setUsername(username);
		onlineUser.setPassword(password);
		onlineUser.setEmail(email);
		onlineUserRepository.save(onlineUser); //Save online user object
		Long id = onlineUser.getUserId(); //Get online ser ID
		
		onlineUser = null;
		
		onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
		
		//Verify proper creation of object in database
		assertNotNull(onlineUser);
		assertEquals(firstName, onlineUser.getFirstName());
		assertEquals(lastName, onlineUser.getLastName());
		assertEquals(address, onlineUser.getAddress());
		assertEquals(isLocal, onlineUser.getIsLocal());
		assertNotNull(onlineUser.getLibraryApplicationSystem());
		assertEquals(id, onlineUser.getUserId());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(password, onlineUser.getPassword());
		assertEquals(email, onlineUser.getEmail());
	}
	
	@Test
	public void testPersistAndLoadOnlineUserByEmail() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		String username = "username3";
		String password = "password";
		String email = "email3@email.email";
		OnlineUser onlineUser = new OnlineUser(); //Create new online user object
		onlineUser.setFirstName(firstName);
		onlineUser.setLastName(lastName);
		onlineUser.setAddress(address);
		onlineUser.setIsLocal(isLocal);
		onlineUser.setLibraryApplicationSystem(system);
		onlineUser.setUsername(username);
		onlineUser.setPassword(password);
		onlineUser.setEmail(email);
		onlineUserRepository.save(onlineUser); //Save online user object
		Long id = onlineUser.getUserId(); //Get online ser ID
		
		onlineUser = null;
		
		onlineUser = onlineUserRepository.findOnlineUserByEmail(email);
		
		//Verify proper creation of object in database
		assertNotNull(onlineUser);
		assertEquals(firstName, onlineUser.getFirstName());
		assertEquals(lastName, onlineUser.getLastName());
		assertEquals(address, onlineUser.getAddress());
		assertEquals(isLocal, onlineUser.getIsLocal());
		assertNotNull(onlineUser.getLibraryApplicationSystem());
		assertEquals(id, onlineUser.getUserId());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(password, onlineUser.getPassword());
		assertEquals(email, onlineUser.getEmail());
	}
}
