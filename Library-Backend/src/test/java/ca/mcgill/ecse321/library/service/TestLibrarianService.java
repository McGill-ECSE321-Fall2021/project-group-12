package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineUser;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private OnlineUserRepository onlineUserDao;
	
	@InjectMocks
	private LibrarianService librarianService;
	private static final Long LIBRARIAN_ID = 1L;
	private static final String LIBRARIAN_USERNAME = "username";
	private static final String LIBRARIAN_PASSWORD = "abCdeFg!";
	private static final String LIBRARIAN_FIRSTNAME = "First";
	private static final String LIBRARIAN_LASTNAME = "Last";
	private static final String LIBRARIAN_ADDRESS = "123 street";
	private static final String LIBRARIAN_EMAIL = "email@email.com";	
	
	@BeforeEach
	public void setMoctOutput() {
		lenient().when(librarianDao.findLibrarianByUserId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARIAN_ID)) {
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			
			return librarian;
		} else {
			return null;
		}
	});
		lenient().when(librarianDao.findLibrarianByUsername(anyString()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARIAN_USERNAME)) {
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			
			return librarian;
		} else {
			return null;
		}
	});
		lenient().when(onlineUserDao.findAll())
		.thenAnswer( (InvocationOnMock invocation) -> {
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			List<OnlineUser> allOnlineUser = new ArrayList<>();
			allOnlineUser.add(librarian);
			return allOnlineUser;
	
	});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(onlineUserDao.save(any(OnlineUser.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateHeadLibrarian() {
		assertEquals(0, librarianService.getAllLibrarians().size());
		
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(librarian);
		assertEquals(firstname, librarian.getFirstName());
		assertEquals(lastname, librarian.getLastName());
		assertEquals(address, librarian.getAddress());
		assertEquals(email, librarian.getEmail());
		assertEquals(username, librarian.getUsername());
		assertEquals(password, librarian.getPassword());
		assertEquals(true, librarian.getIsHead());
	}
	@Test
	public void testCreateHeadLibrarianNullFirstName() {
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(null, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty first name.", error);
	}
	@Test
	public void testCreateHeadLibrarianNullLastName() {
		String firstname = "First";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, null, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty last name.", error);
	}
	@Test
	public void testCreateHeadLibrarianNullAddress() {
		String firstname = "First";
		String lastname = "Last";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, null, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty address.", error);
	}
	@Test
	public void testCreateHeadLibrarianNullEmail() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, null, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty email.", error);
	}
	@Test
	public void testCreateHeadLibrarianNullUsername() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty username.", error);
	}
	@Test
	public void testCreateHeadLibrarianNullPasword() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, null, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Librarian cannot have an empty password.", error);
	}
	@Test
	public void testCreateHeadLibrarianPasswordSpace() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCde Fg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("A password cannot contain spaces.", error);
	}
	@Test
	public void testCreateHeadLibrarianPasswordLessThanEight() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("A password must be at least 8 characters.", error);
	}
	@Test
	public void testCreateHeadLibrarianPasswordLowerCase() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abcdefg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Password does not contain an upper case letter.", error);
	}
	@Test
	public void testCreateHeadLibrarianPasswordSpecialChar() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abcDeFgh";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Password does not contain a special character letter.", error);
	}
	@Test
	public void testCreateHeadLibrarianEmailMissingAmpersand() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "emailemail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Provided email is invalid.", error);
	}
	@Test
	public void testCreateHeadLibrarianEmailMissingDot() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@emailcom";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Provided email is invalid.", error);
	}
	@Test
	public void testCreateHeadLibrarianUsernameSpaces() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user name";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("A username cannot contain spaces.", error);
	}
	@Test
	public void testCreateHeadLibrarianDuplicateUsername() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "username";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarian);
		assertEquals("Username already exists.", error);
	}
	@Test
	public void testCreateHeadLibrarianDuplicateEmail() {
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@email.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		String error = null;
		
		try {
			librarian = librarianService.createHeadLibrarian(firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarian);
		assertEquals("Email already in use.", error);
	}
}
