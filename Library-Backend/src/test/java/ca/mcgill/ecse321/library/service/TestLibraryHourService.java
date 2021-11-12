package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

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
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;

@ExtendWith(MockitoExtension.class)
public class TestLibraryHourService {
	
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private LibraryHourRepository libraryHourDao;
	
	@InjectMocks
	private LibraryHourService service;
	
	private static final Long LIBRARIAN_A_ID = 500L;
	private static final Long LIBRARYHOUR_MONDAY_ID = 10L;
	private static final Long LIBRARYHOUR_TUESDAY_ID = 11L;
	private static final Long LIBRARYHOUR_WEDNESDAY_ID = 12L;
	private static final Long LIBRARYHOUR_THURSDAY_ID = 13L;
	private static final Long LIBRARYHOUR_FRIDAY_ID = 14L;
	private static final Long LIBRARYHOUR_SATURDAY_ID = 15L;
	private static final Long LIBRARYHOUR_SUNDAY_ID = 16L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(libraryHourDao.findLibraryHourByLibraryHourId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARYHOUR_MONDAY_ID)) {
				LibraryHour libraryHour1 = new LibraryHour();
				libraryHour1.setDay(Day.Monday);
				libraryHour1.setStartTime(Time.valueOf("08:00:00"));
				libraryHour1.setEndTime(Time.valueOf("18:00:00"));
				libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
				return libraryHour1;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_TUESDAY_ID)) {
				LibraryHour libraryHour2 = new LibraryHour();
				libraryHour2.setDay(Day.Tuesday);
				libraryHour2.setStartTime(Time.valueOf("09:00:00"));
				libraryHour2.setEndTime(Time.valueOf("19:00:00"));
				libraryHour2.setLibraryHourId(LIBRARYHOUR_TUESDAY_ID);
				return libraryHour2;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_WEDNESDAY_ID)) {
				LibraryHour libraryHour3 = new LibraryHour();
				libraryHour3.setDay(Day.Wednesday);
				libraryHour3.setStartTime(Time.valueOf("10:00:00"));
				libraryHour3.setEndTime(Time.valueOf("20:00:00"));
				libraryHour3.setLibraryHourId(LIBRARYHOUR_WEDNESDAY_ID);
				return libraryHour3;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_THURSDAY_ID)) {
				LibraryHour libraryHour4 = new LibraryHour();
				libraryHour4.setDay(Day.Thursday);
				libraryHour4.setStartTime(Time.valueOf("11:00:00"));
				libraryHour4.setEndTime(Time.valueOf("21:00:00"));
				libraryHour4.setLibraryHourId(LIBRARYHOUR_THURSDAY_ID);
				return libraryHour4;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_FRIDAY_ID)) {
				LibraryHour libraryHour5 = new LibraryHour();
				libraryHour5.setDay(Day.Friday);
				libraryHour5.setStartTime(Time.valueOf("12:00:00"));
				libraryHour5.setEndTime(Time.valueOf("22:00:00"));
				libraryHour5.setLibraryHourId(LIBRARYHOUR_FRIDAY_ID);
				return libraryHour5;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_SATURDAY_ID)) {
				LibraryHour libraryHour6 = new LibraryHour();
				libraryHour6.setDay(Day.Saturday);
				libraryHour6.setStartTime(Time.valueOf("09:00:00"));
				libraryHour6.setEndTime(Time.valueOf("12:00:00"));
				libraryHour6.setLibraryHourId(LIBRARYHOUR_SATURDAY_ID);
				return libraryHour6;
			} else if(invocation.getArgument(0).equals(LIBRARYHOUR_SUNDAY_ID)) {
				LibraryHour libraryHour7 = new LibraryHour();
				libraryHour7.setDay(Day.Sunday);
				libraryHour7.setStartTime(Time.valueOf("00:00:00"));
				libraryHour7.setEndTime(Time.valueOf("00:00:00"));
				libraryHour7.setLibraryHourId(LIBRARYHOUR_SUNDAY_ID);
				return libraryHour7;
			} else {
				return null;
			}
		});
		
		lenient().when(librarianDao.findLibrarianByUserId(LIBRARIAN_A_ID)).thenAnswer( (InvocationOnMock invocation) -> {
			LibraryHour libraryHour1 = new LibraryHour();
			libraryHour1.setDay(Day.Monday);
			libraryHour1.setStartTime(Time.valueOf("08:00:00"));
			libraryHour1.setEndTime(Time.valueOf("18:00:00"));
			libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
			
			LibraryHour libraryHour2 = new LibraryHour();
			libraryHour2.setDay(Day.Tuesday);
			libraryHour2.setStartTime(Time.valueOf("09:00:00"));
			libraryHour2.setEndTime(Time.valueOf("19:00:00"));
			libraryHour2.setLibraryHourId(LIBRARYHOUR_TUESDAY_ID);
			
			LibraryHour libraryHour3 = new LibraryHour();
			libraryHour3.setDay(Day.Wednesday);
			libraryHour3.setStartTime(Time.valueOf("10:00:00"));
			libraryHour3.setEndTime(Time.valueOf("20:00:00"));
			libraryHour3.setLibraryHourId(LIBRARYHOUR_WEDNESDAY_ID);
			
			LibraryHour libraryHour4 = new LibraryHour();
			libraryHour4.setDay(Day.Thursday);
			libraryHour4.setStartTime(Time.valueOf("11:00:00"));
			libraryHour4.setEndTime(Time.valueOf("21:00:00"));
			libraryHour4.setLibraryHourId(LIBRARYHOUR_THURSDAY_ID);
			
			LibraryHour libraryHour5 = new LibraryHour();
			libraryHour5.setDay(Day.Friday);
			libraryHour5.setStartTime(Time.valueOf("12:00:00"));
			libraryHour5.setEndTime(Time.valueOf("22:00:00"));
			libraryHour5.setLibraryHourId(LIBRARYHOUR_FRIDAY_ID);
			
			LibraryHour libraryHour6 = new LibraryHour();
			libraryHour6.setDay(Day.Saturday);
			libraryHour6.setStartTime(Time.valueOf("09:00:00"));
			libraryHour6.setEndTime(Time.valueOf("12:00:00"));
			libraryHour6.setLibraryHourId(LIBRARYHOUR_SATURDAY_ID);
			
			LibraryHour libraryHour7 = new LibraryHour();
			libraryHour7.setDay(Day.Sunday);
			libraryHour7.setStartTime(Time.valueOf("00:00:00"));
			libraryHour7.setEndTime(Time.valueOf("00:00:00"));
			libraryHour7.setLibraryHourId(LIBRARYHOUR_SUNDAY_ID);
			
			Librarian librarian = new Librarian();
			List<LibraryHour> libraryHours = new ArrayList<>();
			libraryHours.add(libraryHour7);
			libraryHours.add(libraryHour6);
			libraryHours.add(libraryHour5);
			libraryHours.add(libraryHour4);
			libraryHours.add(libraryHour3);
			libraryHours.add(libraryHour2);
			libraryHours.add(libraryHour1);
			librarian.setAddress("3445 Durocher");
			librarian.setEmail("123@gmail.com");
			librarian.setFirstName("Peter");
			librarian.setIsHead(false);
			librarian.setIsLocal(true);
			librarian.setLastName("Parker");
			librarian.setLibraryHours(libraryHours);
			librarian.setPassword("123#qwER");
			librarian.setUsername("Spiderman");
			librarian.setUserId(LIBRARIAN_A_ID);
			
			return librarian;
		});
		
		lenient().when(libraryHourDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			List<LibraryHour> libraryHours = new ArrayList<>();
			
			LibraryHour libraryHour1 = new LibraryHour();
			libraryHour1.setDay(Day.Monday);
			libraryHour1.setStartTime(Time.valueOf("08:00:00"));
			libraryHour1.setEndTime(Time.valueOf("18:00:00"));
			libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
			
			LibraryHour libraryHour2 = new LibraryHour();
			libraryHour2.setDay(Day.Tuesday);
			libraryHour2.setStartTime(Time.valueOf("09:00:00"));
			libraryHour2.setEndTime(Time.valueOf("19:00:00"));
			libraryHour2.setLibraryHourId(LIBRARYHOUR_TUESDAY_ID);
			
			LibraryHour libraryHour3 = new LibraryHour();
			libraryHour3.setDay(Day.Wednesday);
			libraryHour3.setStartTime(Time.valueOf("10:00:00"));
			libraryHour3.setEndTime(Time.valueOf("20:00:00"));
			libraryHour3.setLibraryHourId(LIBRARYHOUR_WEDNESDAY_ID);
			
			LibraryHour libraryHour4 = new LibraryHour();
			libraryHour4.setDay(Day.Thursday);
			libraryHour4.setStartTime(Time.valueOf("11:00:00"));
			libraryHour4.setEndTime(Time.valueOf("21:00:00"));
			libraryHour4.setLibraryHourId(LIBRARYHOUR_THURSDAY_ID);
			
			LibraryHour libraryHour5 = new LibraryHour();
			libraryHour5.setDay(Day.Friday);
			libraryHour5.setStartTime(Time.valueOf("12:00:00"));
			libraryHour5.setEndTime(Time.valueOf("22:00:00"));
			libraryHour5.setLibraryHourId(LIBRARYHOUR_FRIDAY_ID);
			
			LibraryHour libraryHour6 = new LibraryHour();
			libraryHour6.setDay(Day.Saturday);
			libraryHour6.setStartTime(Time.valueOf("09:00:00"));
			libraryHour6.setEndTime(Time.valueOf("12:00:00"));
			libraryHour6.setLibraryHourId(LIBRARYHOUR_SATURDAY_ID);
			
			LibraryHour libraryHour7 = new LibraryHour();
			libraryHour7.setDay(Day.Sunday);
			libraryHour7.setStartTime(Time.valueOf("00:00:00"));
			libraryHour7.setEndTime(Time.valueOf("00:00:00"));
			libraryHour7.setLibraryHourId(LIBRARYHOUR_SUNDAY_ID);
			
			libraryHours.add(libraryHour7);
			libraryHours.add(libraryHour6);
			libraryHours.add(libraryHour5);
			libraryHours.add(libraryHour4);
			libraryHours.add(libraryHour3);
			libraryHours.add(libraryHour2);
			libraryHours.add(libraryHour1);
			return libraryHours;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(libraryHourDao.save(any(LibraryHour.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testGetLibraryHourById() {
		LibraryHour libraryHour = null;
		try {
			libraryHour = service.getLibraryHour(LIBRARYHOUR_MONDAY_ID);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(libraryHour);
		assertEquals(LIBRARYHOUR_MONDAY_ID, libraryHour.getLibraryHourId());
	}
	
	@Test
	public void testGetLibraryHourByNull() {
		LibraryHour libraryHour = null;
		String error = "";
		try {
			libraryHour = service.getLibraryHour(null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(libraryHour);
		assertEquals("Argument cannot be null.", error);
	}
	
	@Test
	public void testGetLibraryHourNotExist() {
		LibraryHour libraryHour = null;
		String error = "";
		try {
			libraryHour = service.getLibraryHour(1000L);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(libraryHour);
		assertEquals("Cannot find such libraryHour.", error);
	}
	
	@Test
	public void testGetLibraryHourOfLibrarian() {
		Librarian librarian = librarianDao.findLibrarianByUserId(LIBRARIAN_A_ID);
		List<LibraryHour> libraryHours = librarian.getLibraryHours();
		List<LibraryHour> foundLibraryHours = null;
		try {
			foundLibraryHours = service.getLibraryHoursOfLibrarian(librarian);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(foundLibraryHours);
		assertEquals(libraryHours.size(), foundLibraryHours.size());
		List<Long> libraryHourIds = new ArrayList<>();
		libraryHourIds.add(LIBRARYHOUR_MONDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_TUESDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_WEDNESDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_THURSDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_FRIDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_SATURDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_SUNDAY_ID);
		Boolean checked = true;
		for(LibraryHour i : foundLibraryHours) {
			if(!(libraryHourIds.contains(i.getLibraryHourId()))) {
				checked = false;
			}
		}
		assertEquals(true, checked);
	}
	
	@Test
	public void testGetLibraryHourOfLibrarianNull() {
		Librarian librarian = null;
		List<LibraryHour> foundLibraryHours = null;
		String error = "";
		try {
			foundLibraryHours = service.getLibraryHoursOfLibrarian(librarian);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(foundLibraryHours);
		assertEquals("Argument cannot be null.", error);
	}
	
	@Test
	public void testGetLibraryHourOfLibrarianNotExist() {
		Librarian librarian = new Librarian();
		librarian.setUserId(233L);
		List<LibraryHour> foundLibraryHours = null;
		String error = "";
		try {
			foundLibraryHours = service.getLibraryHoursOfLibrarian(librarian);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(foundLibraryHours);
		assertEquals("Such librarian does not exist.", error);
	}
	
	@Test
	public void testGetAlLibraryHours() {
		List<LibraryHour> libraryHours = null;
		try {
			libraryHours = service.getAllLibraryHours();
		} catch (Exception e) {
			fail();
		}
		assertEquals(7, libraryHours.size());
		List<Long> libraryHourIds = new ArrayList<>();
		libraryHourIds.add(LIBRARYHOUR_MONDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_TUESDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_WEDNESDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_THURSDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_FRIDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_SATURDAY_ID);
		libraryHourIds.add(LIBRARYHOUR_SUNDAY_ID);
		Boolean checked = true;
		for(LibraryHour libraryHour: libraryHours) {
			if(!(libraryHourIds.contains(libraryHour.getLibraryHourId()))) {
				checked = false;
			}
		}
		assertEquals(true, checked);
	}
	
	@Test
	public void testCreateLibraryHour() {
		Time startTime = Time.valueOf("08:00:00");
		Time endTime = Time.valueOf("18:00:00");
		LibraryHour newLibraryHour = null;
		try {
			newLibraryHour = service.createLibraryHour(startTime, endTime, Day.Monday);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(newLibraryHour);
	}
	
	@Test
	public void testCreateLibraryHourNullInputs() {
		Time startTime = Time.valueOf("08:00:00");
		Time endTime = Time.valueOf("18:00:00");
		Time empty = null;
		LibraryHour newLibraryHour = null;
		String error = "";
		try {
			newLibraryHour = service.createLibraryHour(empty, endTime, Day.Monday);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(newLibraryHour);
		assertEquals("Cannot create LibraryHour with empty arguments.", error);
		try {
			newLibraryHour = service.createLibraryHour(startTime, empty, Day.Monday);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(newLibraryHour);
		assertEquals("Cannot create LibraryHour with empty arguments.", error);
		try {
			newLibraryHour = service.createLibraryHour(startTime, endTime, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(newLibraryHour);
		assertEquals("Cannot create LibraryHour with empty arguments.", error);
		
		try {
			newLibraryHour = service.createLibraryHour(endTime, startTime, Day.Monday);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(newLibraryHour);
		assertEquals("Invalid time input, startTime cannot be after endTime", error);
	}
	
	@Test
	public void deleteLibraryHour() {
		LibraryHour libraryHour1 = new LibraryHour();
		libraryHour1.setDay(Day.Monday);
		libraryHour1.setStartTime(Time.valueOf("08:00:00"));
		libraryHour1.setEndTime(Time.valueOf("18:00:00"));
		libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
		
		LibraryHour libraryHour2 = new LibraryHour();
		libraryHour2.setDay(Day.Tuesday);
		libraryHour2.setStartTime(Time.valueOf("09:00:00"));
		libraryHour2.setEndTime(Time.valueOf("19:00:00"));
		libraryHour2.setLibraryHourId(LIBRARYHOUR_TUESDAY_ID);
		
		LibraryHour libraryHour3 = new LibraryHour();
		libraryHour3.setDay(Day.Wednesday);
		libraryHour3.setStartTime(Time.valueOf("10:00:00"));
		libraryHour3.setEndTime(Time.valueOf("20:00:00"));
		libraryHour3.setLibraryHourId(LIBRARYHOUR_WEDNESDAY_ID);
		
		LibraryHour libraryHour4 = new LibraryHour();
		libraryHour4.setDay(Day.Thursday);
		libraryHour4.setStartTime(Time.valueOf("11:00:00"));
		libraryHour4.setEndTime(Time.valueOf("21:00:00"));
		libraryHour4.setLibraryHourId(LIBRARYHOUR_THURSDAY_ID);
		
		LibraryHour libraryHour5 = new LibraryHour();
		libraryHour5.setDay(Day.Friday);
		libraryHour5.setStartTime(Time.valueOf("12:00:00"));
		libraryHour5.setEndTime(Time.valueOf("22:00:00"));
		libraryHour5.setLibraryHourId(LIBRARYHOUR_FRIDAY_ID);
		
		LibraryHour libraryHour6 = new LibraryHour();
		libraryHour6.setDay(Day.Saturday);
		libraryHour6.setStartTime(Time.valueOf("09:00:00"));
		libraryHour6.setEndTime(Time.valueOf("12:00:00"));
		libraryHour6.setLibraryHourId(LIBRARYHOUR_SATURDAY_ID);
		
		LibraryHour libraryHour7 = new LibraryHour();
		libraryHour7.setDay(Day.Sunday);
		libraryHour7.setStartTime(Time.valueOf("00:00:00"));
		libraryHour7.setEndTime(Time.valueOf("00:00:00"));
		libraryHour7.setLibraryHourId(LIBRARYHOUR_SUNDAY_ID);
		
		Librarian librarian = new Librarian();
		List<LibraryHour> libraryHours = new ArrayList<>();
		libraryHours.add(libraryHour7);
		libraryHours.add(libraryHour6);
		libraryHours.add(libraryHour5);
		libraryHours.add(libraryHour4);
		libraryHours.add(libraryHour3);
		libraryHours.add(libraryHour2);
		libraryHours.add(libraryHour1);
		librarian.setAddress("3445 Durocher");
		librarian.setEmail("123@gmail.com");
		librarian.setFirstName("Peter");
		librarian.setIsHead(false);
		librarian.setIsLocal(true);
		librarian.setLastName("Parker");
		librarian.setLibraryHours(libraryHours);
		librarian.setPassword("123#qwER");
		librarian.setUsername("Spiderman");
		librarian.setUserId(LIBRARIAN_A_ID);
		
		LibraryHour deletedLibraryHour = null;
		try {
			deletedLibraryHour = service.deleteLibraryHour(librarian, libraryHour1);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(deletedLibraryHour);
		assertEquals(deletedLibraryHour.getLibraryHourId(), libraryHour1.getLibraryHourId());
		assertEquals(librarian.getLibraryHours().size(), 6);
	}
	
	@Test
	public void deleteLibraryHourIllegalInputs() {
		LibraryHour libraryHour1 = new LibraryHour();
		libraryHour1.setDay(Day.Monday);
		libraryHour1.setStartTime(Time.valueOf("08:00:00"));
		libraryHour1.setEndTime(Time.valueOf("18:00:00"));
		libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
		
		LibraryHour libraryHour7 = new LibraryHour();
		libraryHour7.setDay(Day.Sunday);
		libraryHour7.setStartTime(Time.valueOf("00:00:00"));
		libraryHour7.setEndTime(Time.valueOf("00:00:00"));
		libraryHour7.setLibraryHourId(LIBRARYHOUR_SUNDAY_ID);
		
		Librarian librarian = new Librarian();
		List<LibraryHour> libraryHours = new ArrayList<>();
		libraryHours.add(libraryHour1);
		librarian.setAddress("3445 Durocher");
		librarian.setEmail("123@gmail.com");
		librarian.setFirstName("Peter");
		librarian.setIsHead(false);
		librarian.setIsLocal(true);
		librarian.setLastName("Parker");
		librarian.setLibraryHours(libraryHours);
		librarian.setPassword("123#qwER");
		librarian.setUsername("Spiderman");
		librarian.setUserId(LIBRARIAN_A_ID);
		
		LibraryHour deletedLibraryHour = null;
		String error = "";
		try {
			deletedLibraryHour = service.deleteLibraryHour(null, libraryHour1);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(deletedLibraryHour);
		assertEquals("Cannot delete LibraryHour with empty arguments.", error);
		
		try {
			deletedLibraryHour = service.deleteLibraryHour(librarian, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(deletedLibraryHour);
		assertEquals("Cannot delete LibraryHour with empty arguments.", error);
		
		try {
			deletedLibraryHour = service.deleteLibraryHour(librarian, libraryHour7);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(deletedLibraryHour);
		assertEquals("Targeting librarian does not have such libraryHour.", error);
	}
	
	@Test
	public void assignLibraryHour() {
		List<LibraryHour> emptyHours = new ArrayList<>();
		Librarian librarian = new Librarian();
		librarian.setAddress("3445 Durocher");
		librarian.setEmail("123@gmail.com");
		librarian.setFirstName("Peter");
		librarian.setIsHead(false);
		librarian.setIsLocal(true);
		librarian.setLastName("Parker");
		librarian.setPassword("123#qwER");
		librarian.setUsername("Spiderman");
		librarian.setUserId(LIBRARIAN_A_ID);
		librarian.setLibraryHours(emptyHours);
		
		LibraryHour libraryHour1 = new LibraryHour();
		libraryHour1.setDay(Day.Monday);
		libraryHour1.setStartTime(Time.valueOf("08:00:00"));
		libraryHour1.setEndTime(Time.valueOf("18:00:00"));
		libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
		
		LibraryHour assignedHour = null;
		try {
			assignedHour = service.assignLibraryHour(librarian, libraryHour1);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(assignedHour);
		assertEquals(librarian.getLibraryHours().size(), 1);
		assertEquals(librarian.getLibraryHours(0).getLibraryHourId(), libraryHour1.getLibraryHourId());
	}
	
	@Test
	public void assignLibraryHourInvalidInput() {
		List<LibraryHour> emptyHours = new ArrayList<>();
		Librarian librarian = new Librarian();
		librarian.setAddress("3445 Durocher");
		librarian.setEmail("123@gmail.com");
		librarian.setFirstName("Peter");
		librarian.setIsHead(false);
		librarian.setIsLocal(true);
		librarian.setLastName("Parker");
		librarian.setPassword("123#qwER");
		librarian.setUsername("Spiderman");
		librarian.setUserId(LIBRARIAN_A_ID);
		librarian.setLibraryHours(emptyHours);
		
		LibraryHour libraryHour1 = new LibraryHour();
		libraryHour1.setDay(Day.Monday);
		libraryHour1.setStartTime(Time.valueOf("08:00:00"));
		libraryHour1.setEndTime(Time.valueOf("18:00:00"));
		libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
		
		LibraryHour assignedHour = null;
		String error = "";
		try {
			assignedHour = service.assignLibraryHour(null, libraryHour1);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(assignedHour);
		assertEquals("Cannot delete LibraryHour with empty arguments.", error);
		
		try {
			assignedHour = service.assignLibraryHour(librarian, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(assignedHour);
		assertEquals("Cannot delete LibraryHour with empty arguments.", error);
	}
	
	@Test
	public void assignLibraryHourExistedHour() {
		List<LibraryHour> libraryHours = new ArrayList<>();
		Librarian librarian = new Librarian();
		librarian.setAddress("3445 Durocher");
		librarian.setEmail("123@gmail.com");
		librarian.setFirstName("Peter");
		librarian.setIsHead(false);
		librarian.setIsLocal(true);
		librarian.setLastName("Parker");
		librarian.setPassword("123#qwER");
		librarian.setUsername("Spiderman");
		librarian.setUserId(LIBRARIAN_A_ID);
		
		LibraryHour libraryHour1 = new LibraryHour();
		libraryHour1.setDay(Day.Monday);
		libraryHour1.setStartTime(Time.valueOf("08:00:00"));
		libraryHour1.setEndTime(Time.valueOf("18:00:00"));
		libraryHour1.setLibraryHourId(LIBRARYHOUR_MONDAY_ID);
		libraryHours.add(libraryHour1);
		librarian.setLibraryHours(libraryHours);
		
		LibraryHour assignedHour = null;
		String error = "";
		try {
			assignedHour = service.assignLibraryHour(librarian, libraryHour1);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(assignedHour);
		assertEquals(error, "Targeting librarian already have such libraryHour.");
	}
	
}
