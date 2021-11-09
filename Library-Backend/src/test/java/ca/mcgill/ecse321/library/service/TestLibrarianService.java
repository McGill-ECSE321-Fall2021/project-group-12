package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.OnlineUser;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private OnlineUserRepository onlineUserDao;
	@Mock
	private LibraryHourRepository libraryHourDao;
	
	@InjectMocks
	private LibrarianService librarianService;
	private static final Long LIBRARIAN_ID = 1L;
	private static final String LIBRARIAN_USERNAME = "username";
	private static final String LIBRARIAN_PASSWORD = "abCdeFg!";
	private static final String LIBRARIAN_FIRSTNAME = "First";
	private static final String LIBRARIAN_LASTNAME = "Last";
	private static final String LIBRARIAN_ADDRESS = "123 street";
	private static final String LIBRARIAN_EMAIL = "email@email.com";
	private static final boolean LIBRARIAN_ISHEAD = false; 
	
	private static final String LIBRARIAN_USERNAME_HEAD = "head";
	private static final Long LIBRARIAN_ID_HEAD = 2L;
	private static final String LIBRARIAN_EMAIL_HEAD = "heademail@email.com";
	
	@BeforeEach
	public void setMoctOutput() {
		lenient().when(librarianDao.findLibrarianByUserId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARIAN_ID)) {
		    LibraryHour libraryHour = new LibraryHour();
			libraryHour.setLibraryHourId(3L);
			libraryHour.setStartTime(Time.valueOf("08:00:00"));
			libraryHour.setEndTime(Time.valueOf("20:00:00"));
			libraryHour.setDay(Day.Monday);
			List<LibraryHour> libraryHours = new ArrayList<>();
			libraryHours.add(libraryHour);
			
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			librarian.setIsHead(LIBRARIAN_ISHEAD);
			librarian.setLibraryHours(libraryHours);
			
			return librarian;
		} else {
			return null;
		}
	});
		lenient().when(librarianDao.findLibrarianByUsername(anyString()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARIAN_USERNAME)) {
//			LibraryHour libraryHour = new LibraryHour();
//			libraryHour.setLibraryHourId(LIBRARYHOUR_ID);
//			libraryHour.setStartTime(LIBRARYHOUR_STARTTIME);
//			libraryHour.setEndTime(LIBRARYHOUR_ENDTIME);
//			libraryHour.setDay(LIBRARYHOUR_DAY);
//			List<LibraryHour> libraryHours = new ArrayList<>();
//			libraryHours.add(libraryHour);

			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			librarian.setIsHead(LIBRARIAN_ISHEAD);
//			librarian.setLibraryHours(libraryHours);
			
			return librarian;
		} else if (invocation.getArgument(0).equals(LIBRARIAN_USERNAME_HEAD)) {
//			LibraryHour libraryHour = new LibraryHour();
//			libraryHour.setLibraryHourId(LIBRARYHOUR_ID_HEAD);
//			libraryHour.setStartTime(LIBRARYHOUR_STARTTIME_HEAD);
//			libraryHour.setEndTime(LIBRARYHOUR_ENDTIME_HEAD);
//			libraryHour.setDay(LIBRARYHOUR_DAY_HEAD);
//			List<LibraryHour> libraryHours = new ArrayList<>();
//			libraryHours.add(libraryHour);
			
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID_HEAD);
			librarian.setUsername(LIBRARIAN_USERNAME_HEAD);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL_HEAD);
			librarian.setIsHead(true);
//			librarian.setLibraryHours(libraryHours);
			
			return librarian;
		} else {
			return null;
		}
	});
		//already existing librarians
		lenient().when(librarianDao.findAll())
		.thenAnswer( (InvocationOnMock invocation) -> {
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID);
			librarian.setUsername(LIBRARIAN_USERNAME);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL);
			librarian.setIsHead(LIBRARIAN_ISHEAD);
			Librarian headLibrarian = new Librarian();
			headLibrarian.setUserId(LIBRARIAN_ID_HEAD);
			headLibrarian.setUsername(LIBRARIAN_USERNAME_HEAD);
			headLibrarian.setPassword(LIBRARIAN_PASSWORD);
			headLibrarian.setFirstName(LIBRARIAN_FIRSTNAME);
			headLibrarian.setLastName(LIBRARIAN_LASTNAME);
			headLibrarian.setAddress(LIBRARIAN_ADDRESS);
			headLibrarian.setEmail(LIBRARIAN_EMAIL_HEAD);
			headLibrarian.setIsHead(true);
;			List<Librarian> allLibrarian = new ArrayList<>();
			allLibrarian.add(librarian);
			allLibrarian.add(headLibrarian);
			return allLibrarian;
	
	});
		//already existing users
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
		lenient().when(libraryHourDao.save(any(LibraryHour.class))).thenAnswer(returnParameterAsAnswer);

	}
	
	@Test
	public void testCreateHeadLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
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
	
	@Test
	public void testCreateLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
		String hlUsername = "head";
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		
		try {
			librarian = librarianService.createLibrarian(hlUsername, firstname, lastname, address, email, password, username);
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
		assertEquals(false, librarian.getIsHead());
	}
	@Test
	public void testCreateLibrarianNoHeadLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
		String hlUsername = "username";
		String firstname = "First";
		String lastname = "Last";
		String address = "123 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		Librarian librarian = null;
		String error = null;
		
		try {
			librarian = librarianService.createLibrarian(hlUsername, firstname, lastname, address, email, password, username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(librarian);
		assertEquals("Only the Head Librarian can create a librarian account.", error);
	}
	
	@Test
	public void testUpdateLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
		String firstname = "FirstName";
		String lastname = "LastName";
		String address = "124 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		boolean isHead = true;
		Librarian librarian = null;
		
		try {
			librarian = librarianService.updateLibrarian(LIBRARIAN_USERNAME, firstname, lastname, address, email, password, username, isHead);
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
		assertEquals(isHead, librarian.getIsHead());
	}
	@Test
	public void testUpdateLibrarianDoesNotExist() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
		String firstname = "FirstName";
		String lastname = "LastName";
		String address = "124 street";
		String email = "email@mail.com";
		String username = "user";
		String password = "abCdeFg!";
		boolean isHead = true;
		Librarian librarian = null;
		String error = null;
		
		try {
			librarian = librarianService.updateLibrarian("usname", firstname, lastname, address, email, password, username, isHead);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(librarian);
		assertEquals("Librarian does not exist.", error);
	}
	
	@Test
	public void testGetLibrarianById() {
		assertEquals(2, librarianService.getAllLibrarians().size());

		Librarian librarian = null;
		
		librarian = librarianService.getLibrarian(LIBRARIAN_ID);

//		try {
//			librarian = librarianService.getLibrarian(LIBRARIAN_ID);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
		
		assertNotNull(librarian);
		assertEquals(LIBRARIAN_FIRSTNAME, librarian.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, librarian.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, librarian.getAddress());
		assertEquals(LIBRARIAN_EMAIL, librarian.getEmail());
		assertEquals(LIBRARIAN_USERNAME, librarian.getUsername());
		assertEquals(LIBRARIAN_PASSWORD, librarian.getPassword());
		assertEquals(LIBRARIAN_ISHEAD, librarian.getIsHead());
	}
	@Test
	public void testGetLibrarianByUsername() {
		assertEquals(2, librarianService.getAllLibrarians().size());

		Librarian librarian = null;
		
		try {
			librarian = librarianService.getLibrarian(LIBRARIAN_USERNAME);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(librarian);
		assertEquals(LIBRARIAN_FIRSTNAME, librarian.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, librarian.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, librarian.getAddress());
		assertEquals(LIBRARIAN_EMAIL, librarian.getEmail());
		assertEquals(LIBRARIAN_USERNAME, librarian.getUsername());
		assertEquals(LIBRARIAN_PASSWORD, librarian.getPassword());
		assertEquals(LIBRARIAN_ISHEAD, librarian.getIsHead());
	}
	@Test
	public void testGetHeadLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());

		Librarian librarian = null;
		
		try {
			librarian = librarianService.getHeadLibrarian();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(librarian);
		assertEquals(LIBRARIAN_FIRSTNAME, librarian.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, librarian.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, librarian.getAddress());
		assertEquals(LIBRARIAN_EMAIL_HEAD, librarian.getEmail());
		assertEquals(LIBRARIAN_USERNAME_HEAD, librarian.getUsername());
		assertEquals(LIBRARIAN_PASSWORD, librarian.getPassword());
		assertEquals(true, librarian.getIsHead());
	}
	@Test
	public void testDeleteLibrarian() {
		assertEquals(2, librarianService.getAllLibrarians().size());
		
		Librarian headLibrarian = null;
		
		try {
			headLibrarian = librarianService.removeLibrarian(LIBRARIAN_USERNAME_HEAD, LIBRARIAN_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(true, headLibrarian.getIsHead());
	}
}
