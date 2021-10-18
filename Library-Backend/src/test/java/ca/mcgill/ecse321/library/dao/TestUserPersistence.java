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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUserPersistence {
	
	@Autowired
	private OfflineUserRepository offlineUserRepository;
	@Autowired
	private OnlineUserRepository onlineUserRepository;
	
	private LibraryApplicationSystem system = new LibraryApplicationSystem();
	
	@AfterEach
	public void clearDatabase() {
		offlineUserRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadOfflineUser() {
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		offlineUser.setFirstName(firstName);
		offlineUser.setLastName(lastName);
		offlineUser.setAddress(address);
		offlineUser.setIsLocal(isLocal);
		offlineUser.setLibraryApplicationSystem(system);
		offlineUserRepository.save(offlineUser);
		Long id = offlineUser.getUserId();
		
		offlineUser = null;
		
		offlineUser = offlineUserRepository.findOfflineUserById(id);
		
		assertNotNull(offlineUser);
		assertEquals(firstName, offlineUser.getFirstName());
		assertEquals(lastName, offlineUser.getLastName());
		assertEquals(address, offlineUser.getAddress());
		assertEquals(isLocal, offlineUser.getIsLocal());
		assertEquals(system, offlineUser.getLibraryApplicationSystem());
		assertEquals(id, offlineUser.getUserId());
		
	}
	
	@Test
	public void testPersistAndLoadOnlineUser() {
		String firstName = "First";
		String lastName = "Last";
		String address = "123 address street";
		boolean isLocal = true;
		String username = "username";
		String password = "password";
		String email = "email@email.email";
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setFirstName(firstName);
		onlineUser.setLastName(lastName);
		onlineUser.setAddress(address);
		onlineUser.setIsLocal(isLocal);
		onlineUser.setLibraryApplicationSystem(system);
		onlineUser.setUsername(username);
		onlineUser.setPassword(password);
		onlineUser.setEmail(email);
		onlineUserRepository.save(onlineUser);
		Long id = onlineUser.getUserId();
		
		onlineUser = null;
		
		onlineUser = onlineUserRepository.findOnlineUserById(id);
		
		assertNotNull(onlineUser);
		assertEquals(firstName, onlineUser.getFirstName());
		assertEquals(lastName, onlineUser.getLastName());
		assertEquals(address, onlineUser.getAddress());
		assertEquals(isLocal, onlineUser.getIsLocal());
		assertEquals(system, onlineUser.getLibraryApplicationSystem());
		assertEquals(id, onlineUser.getUserId());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(password, onlineUser.getPassword());
		assertEquals(email, onlineUser.getEmail());
	}
}
