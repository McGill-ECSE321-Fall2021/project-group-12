package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.Event;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private OnlineUserRepository onlineUserDao;
	@Mock
	private OfflineUserRepository offlineUserDao;
	@Mock
	private LibraryHourRepository libraryHourDao;
	@Mock
	private ReservationRepository reservationDao;
	@Mock
	private EventRepository eventDao;
	
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
	
	private static final Long ONLINE_ID = 7L;
	private static final String ONLINE_USERNAME = "onlineuser";
	private static final String ONLINE_PASSWORD = "ONlinePWD!";
	private static final Long RESERVATION_ID = 6L;
	private static final Long EVENT_ID = 5L;
	
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
		} else if (invocation.getArgument(0).equals(LIBRARIAN_USERNAME_HEAD)) {
		    LibraryHour libraryHour = new LibraryHour();
			libraryHour.setLibraryHourId(4L);
			libraryHour.setStartTime(Time.valueOf("08:00:00"));
			libraryHour.setEndTime(Time.valueOf("20:00:00"));
			libraryHour.setDay(Day.Tuesday);
			List<LibraryHour> libraryHours = new ArrayList<>();
			libraryHours.add(libraryHour);
			
			Librarian librarian = new Librarian();
			librarian.setUserId(LIBRARIAN_ID_HEAD);
			librarian.setUsername(LIBRARIAN_USERNAME_HEAD);
			librarian.setPassword(LIBRARIAN_PASSWORD);
			librarian.setFirstName(LIBRARIAN_FIRSTNAME);
			librarian.setLastName(LIBRARIAN_LASTNAME);
			librarian.setAddress(LIBRARIAN_ADDRESS);
			librarian.setEmail(LIBRARIAN_EMAIL_HEAD);
			librarian.setIsHead(true);
			librarian.setLibraryHours(libraryHours);
			
			return librarian;
		} else {
			return null;
		}
	});
		//already existing librarians
		lenient().when(librarianDao.findAll())
		.thenAnswer( (InvocationOnMock invocation) -> {
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

			
		    LibraryHour headLibraryHour = new LibraryHour();
		    headLibraryHour.setLibraryHourId(4L);
			headLibraryHour.setStartTime(Time.valueOf("08:00:00"));
			headLibraryHour.setEndTime(Time.valueOf("20:00:00"));
			headLibraryHour.setDay(Day.Tuesday);
			List<LibraryHour> headLibraryHours = new ArrayList<>();
			headLibraryHours.add(headLibraryHour);
			
			Librarian headLibrarian = new Librarian();
			headLibrarian.setUserId(LIBRARIAN_ID_HEAD);
			headLibrarian.setUsername(LIBRARIAN_USERNAME_HEAD);
			headLibrarian.setPassword(LIBRARIAN_PASSWORD);
			headLibrarian.setFirstName(LIBRARIAN_FIRSTNAME);
			headLibrarian.setLastName(LIBRARIAN_LASTNAME);
			headLibrarian.setAddress(LIBRARIAN_ADDRESS);
			headLibrarian.setEmail(LIBRARIAN_EMAIL_HEAD);
			headLibrarian.setIsHead(true);
			headLibrarian.setLibraryHours(headLibraryHours);
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
		lenient().when(onlineUserDao.findOnlineUserByUsername(anyString()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ONLINE_USERNAME)) {
		    OnlineUser onlineUser = new OnlineUser();
			onlineUser.setUserId(ONLINE_ID);
			onlineUser.setUsername(ONLINE_USERNAME);
			onlineUser.setPassword(ONLINE_PASSWORD);
			
			return onlineUser;
		} else {
			return null;
		}
	});
		lenient().when(onlineUserDao.findOnlineUserByUserId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ONLINE_ID)) {
		    OnlineUser onlineUser = new OnlineUser();
			onlineUser.setUserId(ONLINE_ID);
			onlineUser.setUsername(ONLINE_USERNAME);
			onlineUser.setPassword(ONLINE_PASSWORD);
			
			return onlineUser;
		} else {
			return null;
		}
	});
		lenient().when(offlineUserDao.findOfflineUserByUserId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ONLINE_ID)) {
		    OfflineUser offlineUser = new OfflineUser();
			offlineUser.setUserId(ONLINE_ID);
			
			return offlineUser;
		} else {
			return null;
		}
	});
		lenient().when(reservationDao.findReservationByReservationId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(RESERVATION_ID)) {
		    Reservation reservation = new Reservation();
		    reservation.setReservationId(RESERVATION_ID);
		    OfflineUser offlineUser = new OfflineUser();
		    offlineUser.setUserId(ONLINE_ID);
			reservation.setUser(offlineUser);
			return reservation;
		} else {
			return null;
		}
	});
		lenient().when(reservationDao.findByUser(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ONLINE_ID)) {
		    Reservation reservation = new Reservation();
		    reservation.setReservationId(RESERVATION_ID);
		    OfflineUser offlineUser = new OfflineUser();
		    offlineUser.setUserId(ONLINE_ID);
			reservation.setUser(offlineUser);
			List<Reservation> reservations = new ArrayList<>();
			reservations.add(reservation);
			return reservations;
		} else {
			return null;
		}
	});
		lenient().when(eventDao.findAll())
		.thenAnswer( (InvocationOnMock invocation) -> {
				Event event = new Event();
				event.setName("event");
				event.setIsPrivate(true);
				event.setIsAccepted(true);
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(Time.valueOf("08:00:00"));
				timeSlot.setEndTime(Time.valueOf("20:00:00"));
				timeSlot.setStartDate(Date.valueOf("2021-12-12"));
				timeSlot.setEndDate(Date.valueOf("2021-12-13"));
				timeSlot.setTimeSlotId(100L);
				event.setTimeSlot(timeSlot);
				OfflineUser user = new OfflineUser();
				user.setUserId(ONLINE_ID);
				event.setUser(user);
				List<Event> events = new ArrayList<>();
				events.add(event);
				return events;
		});		
		lenient().when(eventDao.findEventByEventId(anyLong()))
		.thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EVENT_ID)) {
				Event event = new Event();
				event.setName("event");
				event.setIsPrivate(true);
				event.setIsAccepted(false);
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(Time.valueOf("08:00:00"));
				timeSlot.setEndTime(Time.valueOf("20:00:00"));
				timeSlot.setStartDate(Date.valueOf("2021-12-12"));
				timeSlot.setEndDate(Date.valueOf("2021-12-13"));
				timeSlot.setTimeSlotId(100L);
				event.setTimeSlot(timeSlot);
				OfflineUser user = new OfflineUser();
				user.setUserId(ONLINE_ID);
				event.setUser(user);
				return event;
			}else {
				return null;
			}
		});
			
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(onlineUserDao.save(any(OnlineUser.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(offlineUserDao.save(any(OfflineUser.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(libraryHourDao.save(any(LibraryHour.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(reservationDao.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);

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
		assertEquals("User cannot have an empty first name.", error);
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
		assertEquals("User cannot have an empty last name.", error);
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
		assertEquals("User cannot have an empty address.", error);
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
		assertEquals("User cannot have an empty email.", error);
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
		assertEquals("User cannot have an empty username.", error);
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
		assertEquals("User cannot have an empty password.", error);
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

		try {
			librarian = librarianService.getLibrarian(LIBRARIAN_ID);
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
	
	@Test
	public void testGetLibraryHourById() {
		List<LibraryHour> libraryHours = new ArrayList<>();
		
		try {
			libraryHours = librarianService.getLibraryHourByLibrarianId(LIBRARIAN_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(libraryHours);
		assertEquals(3L, libraryHours.get(0).getLibraryHourId());
		assertEquals(Time.valueOf("08:00:00"), libraryHours.get(0).getStartTime());
		assertEquals(Time.valueOf("20:00:00"), libraryHours.get(0).getEndTime());
		assertEquals(Day.Monday, libraryHours.get(0).getDay());		
	}
	@Test
	public void testGetLibraryHourDoesNotExist() {
		List<LibraryHour> libraryHours = new ArrayList<>();
		String error = null;
		
		try {
			libraryHours = librarianService.getLibraryHourByLibrarianId(2L);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(0, libraryHours.size());
		assertEquals("Librarian does not exist.", error);
	}
	
	@Test
	public void testCreateLibraryHour() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Tuesday;
		
		try {
			libraryHour = librarianService.createLibraryHour(LIBRARIAN_USERNAME_HEAD, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(libraryHour);
		assertEquals(startTime, libraryHour.getStartTime());
		assertEquals(endTime, libraryHour.getEndTime());
		assertEquals(day, libraryHour.getDay());
	}
	@Test
	public void testCreateLibraryHourNotHead() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Tuesday;
		String error = null;
		
		try {
			libraryHour = librarianService.createLibraryHour(LIBRARIAN_USERNAME, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Only the Head Librarian can create a schedule.", error);	
	}
	@Test
	public void testCreateLibraryHourNoHead() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Tuesday;
		String error = null;
		
		try {
			libraryHour = librarianService.createLibraryHour("nothead", LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Head Librarian username does not exist.", error);	
	}
	@Test
	public void testCreateLibraryHourNoLibrarian() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Tuesday;
		String error = null;
		
		try {
			libraryHour = librarianService.createLibraryHour(LIBRARIAN_USERNAME_HEAD, "nouser", startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Librarian username does not exist.", error);	
	}
	@Test
	public void testCreateLibraryHourDuplicateDay() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Monday;
		String error = null;
		
		try {
			libraryHour = librarianService.createLibraryHour(LIBRARIAN_USERNAME_HEAD, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Library hour for selected day already created.", error);	
	}
	
	@Test
	public void testUpdateLibraryHour() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Monday;
		
		try {
			libraryHour = librarianService.updateLibraryHour(LIBRARIAN_USERNAME_HEAD, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(libraryHour);
		assertEquals(startTime, libraryHour.getStartTime());
		assertEquals(endTime, libraryHour.getEndTime());
		assertEquals(day, libraryHour.getDay());
	}
	@Test
	public void testUpdateLibraryHourNotHead() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Monday;
		String error = null;
		
		try {
			libraryHour = librarianService.updateLibraryHour(LIBRARIAN_USERNAME, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Only the Head Librarian can create a schedule.", error);	
	}
	@Test
	public void testUpdateLibraryHourNoHead() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Monday;
		String error = null;
		
		try {
			libraryHour = librarianService.updateLibraryHour("nohead", LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Head Librarian username does not exist.", error);	
	}
	@Test
	public void testUpdateLibraryHourNoLibrarian() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Monday;
		String error = null;
		
		try {
			libraryHour = librarianService.updateLibraryHour(LIBRARIAN_USERNAME_HEAD, "nouser", startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Librarian username does not exist.", error);	
	}
	@Test
	public void testUpdateLibraryHourNoLibraryHour() {
		LibraryHour libraryHour = new LibraryHour();
		Time startTime = Time.valueOf("08:30:00");
		Time endTime = Time.valueOf("20:30:00");
		Day day = Day.Wednesday;
		String error = null;
		
		try {
			libraryHour = librarianService.updateLibraryHour(LIBRARIAN_USERNAME_HEAD, LIBRARIAN_USERNAME, startTime, endTime, day);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(null, libraryHour.getStartTime());
		assertEquals(null, libraryHour.getEndTime());
		assertEquals(null, libraryHour.getDay());
		assertEquals("Library hour does not exist.", error);	
	}
	
	@Test
	public void testCreateOfflineUser() {
		String firstname = "name";
		String lastname = "namelast";
		String address = "125 street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		
		try {
			offlineUser = librarianService.createOfflineUser(LIBRARIAN_USERNAME_HEAD, firstname, lastname, address, isLocal);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(firstname, offlineUser.getFirstName());
		assertEquals(lastname, offlineUser.getLastName());
		assertEquals(address, offlineUser.getAddress());
		assertEquals(isLocal, offlineUser.getIsLocal());
	}
	@Test
	public void testCreateOfflineUserNotLibrarian() {
		String firstname = "name";
		String lastname = "namelast";
		String address = "125 street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		String error = null;
		
		try {
			offlineUser = librarianService.createOfflineUser("name", firstname, lastname, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(null, offlineUser.getFirstName());
		assertEquals(null, offlineUser.getLastName());
		assertEquals(null, offlineUser.getAddress());
		assertEquals("Librarian does not exist.", error);
	}
	@Test
	public void testCreateOfflineUserNullFirstName() {
		String firstname = null;
		String lastname = "namelast";
		String address = "125 street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		String error = null;
		
		try {
			offlineUser = librarianService.createOfflineUser(LIBRARIAN_USERNAME_HEAD, firstname, lastname, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, offlineUser.getFirstName());
		assertEquals(null, offlineUser.getLastName());
		assertEquals(null, offlineUser.getAddress());
		assertEquals("User cannot have an empty first name.", error);
	}
	@Test
	public void testCreateOfflineUserNullLastName() {
		String firstname = "name";
		String lastname = null;
		String address = "125 street";
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		String error = null;
		
		try {
			offlineUser = librarianService.createOfflineUser(LIBRARIAN_USERNAME_HEAD, firstname, lastname, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, offlineUser.getFirstName());
		assertEquals(null, offlineUser.getLastName());
		assertEquals(null, offlineUser.getAddress());
		assertEquals("User cannot have an empty last name.", error);
	}
	@Test
	public void testCreateOfflineUserNullAddress() {
		String firstname = "name";
		String lastname = "namelast";
		String address = null;
		boolean isLocal = true;
		OfflineUser offlineUser = new OfflineUser();
		String error = null;
		
		try {
			offlineUser = librarianService.createOfflineUser(LIBRARIAN_USERNAME_HEAD, firstname, lastname, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, offlineUser.getFirstName());
		assertEquals(null, offlineUser.getLastName());
		assertEquals(null, offlineUser.getAddress());
		assertEquals("User cannot have an empty address.", error);
	}
	
	@Test
	public void testCreateOnlineUser() {
		String firstname = "firstname";
		String lastname = "lastname";
		String address = "126 street";
		boolean isLocal = true;
		String username = "onlineUsername";
		String password = "abCdeFg!";
		String email = "online@online.com";
		OnlineUser onlineUser = new OnlineUser();
		
		try {
			onlineUser = librarianService.createOnlineUser(firstname, lastname, address, isLocal, username, password, email);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(firstname, onlineUser.getFirstName());
		assertEquals(lastname, onlineUser.getLastName());
		assertEquals(address, onlineUser.getAddress());
		assertEquals(isLocal, onlineUser.getIsLocal());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(password, onlineUser.getPassword());
		assertEquals(email, onlineUser.getEmail());
	}
	
	@Test
	public void testChangePassword() {
		String username = ONLINE_USERNAME;
		String oldPassword = ONLINE_PASSWORD;
		String newPassword = "nEWonLINE!PWD";
		OnlineUser onlineUser = new OnlineUser();
		
		try {
			onlineUser = librarianService.changePassword(username, oldPassword, newPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(newPassword, onlineUser.getPassword());
	}
	@Test
	public void testChangePasswordNoUser() {
		String username = "nouser";
		String oldPassword = ONLINE_PASSWORD;
		String newPassword = "nEWonLINE!PWD";
		OnlineUser onlineUser = new OnlineUser();
		String error = null;
		
		try {
			onlineUser = librarianService.changePassword(username, oldPassword, newPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, onlineUser.getPassword());
		assertEquals("User does not exist.", error);
	}
	@Test
	public void testChangePasswordIncorrectPassword() {
		String username = ONLINE_USERNAME;
		String oldPassword = "somepassword";
		String newPassword = "nEWonLINE!PWD";
		OnlineUser onlineUser = new OnlineUser();
		String error = null;
		
		try {
			onlineUser = librarianService.changePassword(username, oldPassword, newPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, onlineUser.getPassword());
		assertEquals("Incorrect password.", error);
	}
	
	@Test
	public void testUpdateOfflineUser() {
		Long userId = ONLINE_ID;
		String address = "321 street";
		boolean isLocal = false;
		OfflineUser offlineUser = new OfflineUser();
		
		try {
			offlineUser = librarianService.updateOfflineUserInformation(userId, address, isLocal);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(address, offlineUser.getAddress());
		assertEquals(isLocal, offlineUser.getIsLocal());
	}
	@Test
	public void testUpdateOfflineUserNoUser() {
		Long userId = 9L;
		String address = "321 street";
		boolean isLocal = false;
		OfflineUser offlineUser = new OfflineUser();
		String error = null;
		
		try {
			offlineUser = librarianService.updateOfflineUserInformation(userId, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, offlineUser.getAddress());
		assertEquals("User does not exist.", error);
	}
	
	@Test
	public void testUpdateOnlineUser() {
		Long userId = ONLINE_ID;
		String username = "newusername";
		String email = "new@email.com";
		String address = "321 street";
		boolean isLocal = false;
		OnlineUser onlineUser = new OnlineUser();
		
		try {
			onlineUser = librarianService.updateOnlineUserInformation(userId, username, email, address, isLocal);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(address, onlineUser.getAddress());
		assertEquals(username, onlineUser.getUsername());
		assertEquals(email, onlineUser.getEmail());
		assertEquals(isLocal, onlineUser.getIsLocal());
	}
	@Test
	public void testUpdateOnlineUserNoUser() {
		Long userId = 9L;
		String username = "newusername";
		String email = "new@email.com";
		String address = "321 street";
		boolean isLocal = false;
		OnlineUser onlineUser = new OnlineUser();
		String error = null;
		
		try {
			onlineUser = librarianService.updateOnlineUserInformation(userId, username, email, address, isLocal);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(null, onlineUser.getAddress());
		assertEquals("User does not exist.", error);
	}
	
	@Test
	public void testDeleteReservation() {
		boolean removed = false;
		removed = librarianService.removeReservation(6L);
		
		assertTrue(removed);
	}
	
	@Test
	public void testGetReservatioByUserId() {
		List<Reservation> reservations = null;
		reservations = librarianService.getReservationByUserId(ONLINE_ID);
		
		assertNotNull(reservations);
	}
	@Test
	public void testGetReservatioByUserIdNoUser() {
		List<Reservation> reservations = null;
		String error = null;
		
		try{
			reservations = librarianService.getReservationByUserId(10L);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(reservations);
		assertEquals("User does not exist.", error);
	}
	
	@Test
	public void testGetTimeSlotWithEvent() {
		List<TimeSlot> timeSlotWithEvent = null;
		timeSlotWithEvent = librarianService.getTimeSlotsWithEvent();
		
		assertNotNull(timeSlotWithEvent);
		assertEquals(Time.valueOf("08:00:00"), timeSlotWithEvent.get(0).getStartTime());
		assertEquals(Time.valueOf("20:00:00"), timeSlotWithEvent.get(0).getEndTime());
		assertEquals(Date.valueOf("2021-12-12"), timeSlotWithEvent.get(0).getStartDate());
		assertEquals(Date.valueOf("2021-12-13"), timeSlotWithEvent.get(0).getEndDate());
	}
	
	@Test
	public void testGetEventsByUser() {
		List<Event> events = null;
		events = librarianService.getEventsByUser(ONLINE_ID);
		
		assertNotNull(events);
		assertEquals("event", events.get(0).getName());
		assertEquals(true, events.get(0).getIsPrivate());
		assertEquals(true, events.get(0).getIsAccepted());
	}
	
	@Test
	public void testAcceptEvent() {
		Event event = null;
		event = librarianService.acceptEvent(EVENT_ID);
		
		assertNotNull(event);
		assertTrue(event.getIsAccepted());
	}
	
	@Test
	public void testRejectEvent() {
		Event event = null;
		event = librarianService.rejectEvent(EVENT_ID);
		
		assertNotNull(event);
		assertFalse(event.getIsAccepted());
	}
	
}
