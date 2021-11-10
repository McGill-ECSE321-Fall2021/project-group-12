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

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Book.BMGenre;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;

@ExtendWith(MockitoExtension.class)
public class TestReservationService {
	
	@Mock
	private ReservationRepository reservationDao;
	@Mock
	private BookRepository bookDao;
	@Mock
	private MovieRepository movieDao;
	@Mock
	private AlbumRepository albumDao;
	@Mock
	private OnlineUserRepository onlineUserDao;
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private ReservationService service;
	
	private static Long RESERVATION_ID = 0L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(reservationDao.findReservationByReservationId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(RESERVATION_ID)) {
				
				Reservation reservation = new Reservation();
				
				OnlineUser onlineUser = new OnlineUser();
				onlineUser.setAddress("3455 Durocher");
				onlineUser.setEmail("123@gmail.com");
				onlineUser.setFirstName("Parker");
				onlineUser.setLastName("Peter");
				onlineUser.setIsLocal(true);
				onlineUser.setPassword("12345");
				onlineUser.setUsername("SpiderMan");
				onlineUser.setUserId(2L);
				reservation.setUser(onlineUser);
				
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartDate(Date.valueOf("2021-11-18"));
				timeSlot.setEndDate(Date.valueOf("2021-11-19"));
				timeSlot.setStartTime(Time.valueOf("10:00:00"));
				timeSlot.setEndTime(Time.valueOf("11:00:00"));
				timeSlot.setTimeSlotId(3L);
				reservation.setTimeSlot(timeSlot);
				
				List<Item> items = new ArrayList<>();
				
				Creator bookCreator = new Creator();
				bookCreator.setFirstName("BookCreator");
				bookCreator.setLastName("BookAuthor");
				bookCreator.setCreatorType(CreatorType.Author);
				bookCreator.setCreatorId(101L);
				Book book = new Book();
				book.setGenre(BMGenre.Action);
				book.setIsArchive(false);
				book.setIsAvailable(false);
				book.setIsReservable(false);
				book.setNumPages(100);
				book.setReleaseDate(Date.valueOf("2000-01-01"));
				book.setReservation(reservation);
				book.setTitle("BookTitle");
				book.setItemId(100L);
				book.setCreator(bookCreator);
				
				Creator movieCreator = new Creator();
				movieCreator.setFirstName("MovieCreator");
				movieCreator.setLastName("MovieDirector");
				movieCreator.setCreatorType(CreatorType.Director);
				movieCreator.setCreatorId(103L);
				Movie movie = new Movie();
				movie.setDuration(120);
				movie.setGenre(Movie.BMGenre.Action);
				movie.setIsArchive(false);
				movie.setIsAvailable(false);
				movie.setIsReservable(false);
				movie.setItemId(102L);
				movie.setReleaseDate(Date.valueOf("2000-01-02"));
				movie.setReservation(reservation);
				movie.setTitle("MovieTitle");
				movie.setCreator(movieCreator);
				
				Creator albumCreator = new Creator();
				albumCreator.setFirstName("AlbumCreator");
				albumCreator.setLastName("AlbumArtist");
				albumCreator.setCreatorType(CreatorType.Artist);
				albumCreator.setCreatorId(105L);
				Album album = new Album();
				album.setGenre(MusicGenre.Classical);
				album.setIsArchive(false);
				album.setIsAvailable(false);
				album.setIsReservable(false);
				album.setItemId(104L);
				album.setNumSongs(10);
				album.setReleaseDate(Date.valueOf("2000-01-03"));
				album.setReservation(reservation);
				album.setTitle("AlbumTitle");
				album.setCreator(albumCreator);
				
				items.add(movie);
				items.add(album);
				items.add(book);
				reservation.setItems(items);
				return reservation;
			} else {
				return null;
			}
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(reservationDao.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateReservation() {
		assertEquals(0, service.getAllReservations().size());
		
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		Creator movieCreator = new Creator();
		movieCreator.setFirstName("MovieCreator");
		movieCreator.setLastName("MovieDirector");
		movieCreator.setCreatorType(CreatorType.Director);
		movieCreator.setCreatorId(103L);
		Movie movie = new Movie();
		movie.setDuration(120);
		movie.setGenre(Movie.BMGenre.Action);
		movie.setIsArchive(false);
		movie.setIsAvailable(true);
		movie.setIsReservable(true);
		movie.setItemId(102L);
		movie.setReleaseDate(Date.valueOf("2000-01-02"));
		movie.setTitle("MovieTitle");
		movie.setCreator(movieCreator);
		
		Creator albumCreator = new Creator();
		albumCreator.setFirstName("AlbumCreator");
		albumCreator.setLastName("AlbumArtist");
		albumCreator.setCreatorType(CreatorType.Artist);
		albumCreator.setCreatorId(105L);
		Album album = new Album();
		album.setGenre(MusicGenre.Classical);
		album.setIsArchive(false);
		album.setIsAvailable(true);
		album.setIsReservable(true);
		album.setItemId(104L);
		album.setNumSongs(10);
		album.setReleaseDate(Date.valueOf("2000-01-03"));
		album.setTitle("AlbumTitle");
		album.setCreator(albumCreator);
		
		items.add(movie);
		items.add(album);
		items.add(book);
		Reservation reservation = null;
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(reservation);
		assertEquals(items, reservation.getItems());
		assertEquals(onlineUser, reservation.getUser());
		assertEquals(timeSlot, reservation.getTimeSlot());
		for(Item i: reservation.getItems()) {
			assertEquals(false, i.getIsAvailable());
			assertEquals(false, i.getIsReservable());
			assertEquals(reservation, i.getReservation());
		}
	}
	
	@Test
	public void testCreateReservationNullUser() {
		OnlineUser onlineUser = null;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with empty arguments.", error);
	}
	
	@Test
	public void testCreateReservationNullTimeSlot() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = null;
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with empty arguments.", error);
	}
	
	@Test
	public void testCreateReservationNullItems() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = null;
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with empty arguments.", error);
	}
	
	@Test
	public void testCreateReservationEmptyItemsList() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with empty arguments.", error);
	}
	
	@Test
	public void testCreateReservationNonReservableItem() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(true);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		Creator movieCreator = new Creator();
		movieCreator.setFirstName("MovieCreator");
		movieCreator.setLastName("MovieDirector");
		movieCreator.setCreatorType(CreatorType.Director);
		movieCreator.setCreatorId(103L);
		Movie movie = new Movie();
		movie.setDuration(120);
		movie.setGenre(Movie.BMGenre.Action);
		movie.setIsArchive(false);
		movie.setIsAvailable(false);
		movie.setIsReservable(true);
		movie.setItemId(102L);
		movie.setReleaseDate(Date.valueOf("2000-01-02"));
		movie.setTitle("MovieTitle");
		movie.setCreator(movieCreator);
		
		Creator albumCreator = new Creator();
		albumCreator.setFirstName("AlbumCreator");
		albumCreator.setLastName("AlbumArtist");
		albumCreator.setCreatorType(CreatorType.Artist);
		albumCreator.setCreatorId(105L);
		Album album = new Album();
		album.setGenre(MusicGenre.Classical);
		album.setIsArchive(false);
		album.setIsAvailable(true);
		album.setIsReservable(false);
		album.setItemId(104L);
		album.setNumSongs(10);
		album.setReleaseDate(Date.valueOf("2000-01-03"));
		album.setTitle("AlbumTitle");
		album.setCreator(albumCreator);
		
		items.add(movie);
		String error = "";
		Reservation reservation = null;
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("At least one item selected is not reseverable.", error);
		
		items.clear();
		items.add(album);
		Reservation reservation2 = null;
		try {
			reservation2 = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation2);
		assertEquals("At least one item selected is not reseverable.", error);
		
		items.add(book);
		Reservation reservation3 = null;
		try {
			reservation3 = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation3);
		assertEquals("At least one item selected is not reseverable.", error);
	}
	
	@Test
	public void testCreateReservationEmptyTimeSlot() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with missing info in timeSlot.", error);
	}
	
	@Test
	public void testCreateReservationIllegalTimeSlotDate() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setEndDate(Date.valueOf("2000-01-01"));
		timeSlot.setStartDate(Date.valueOf("2000-01-01"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("12:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with endDate before or equal to startDate.", error);
	}
	
	@Test
	public void testUpdateReservation() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		Creator movieCreator = new Creator();
		movieCreator.setFirstName("MovieCreator");
		movieCreator.setLastName("MovieDirector");
		movieCreator.setCreatorType(CreatorType.Director);
		movieCreator.setCreatorId(103L);
		Movie movie = new Movie();
		movie.setDuration(120);
		movie.setGenre(Movie.BMGenre.Action);
		movie.setIsArchive(false);
		movie.setIsAvailable(true);
		movie.setIsReservable(true);
		movie.setItemId(102L);
		movie.setReleaseDate(Date.valueOf("2000-01-02"));
		movie.setTitle("MovieTitle");
		movie.setCreator(movieCreator);
		
		Creator albumCreator = new Creator();
		albumCreator.setFirstName("AlbumCreator");
		albumCreator.setLastName("AlbumArtist");
		albumCreator.setCreatorType(CreatorType.Artist);
		albumCreator.setCreatorId(105L);
		Album album = new Album();
		album.setGenre(MusicGenre.Classical);
		album.setIsArchive(false);
		album.setIsAvailable(true);
		album.setIsReservable(true);
		album.setItemId(104L);
		album.setNumSongs(10);
		album.setReleaseDate(Date.valueOf("2000-01-03"));
		album.setTitle("AlbumTitle");
		album.setCreator(albumCreator);
		
		items.add(movie);
		items.add(album);
		items.add(book);
		Reservation reservation = null;
		reservation = service.createReservation(items, onlineUser, timeSlot);
		
		Book bookNew = new Book();
		bookNew.setGenre(BMGenre.Classic);
		bookNew.setIsArchive(false);
		bookNew.setIsAvailable(true);
		bookNew.setIsReservable(true);
		bookNew.setNumPages(120);
		bookNew.setReleaseDate(Date.valueOf("2000-02-02"));
		bookNew.setTitle("NewBookTitle");
		bookNew.setItemId(200L);
		bookNew.setCreator(bookCreator);
		
		List<Item> newItems = new ArrayList<>();
		newItems.add(bookNew);
		newItems.add(movie);
		
		TimeSlot timeSlotNew = new TimeSlot();
		timeSlotNew.setStartDate(Date.valueOf("2021-11-19"));
		timeSlotNew.setEndDate(Date.valueOf("2021-11-20"));
		timeSlotNew.setStartTime(Time.valueOf("11:00:00"));
		timeSlotNew.setEndTime(Time.valueOf("12:00:00"));
		timeSlotNew.setTimeSlotId(5L);
		
		Reservation updatedReservation = null;
		try {
			updatedReservation = service.updateReservation(newItems, reservation, timeSlotNew);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(updatedReservation);
		assertEquals(newItems, updatedReservation.getItems());
		assertEquals(onlineUser, updatedReservation.getUser());
		assertEquals(timeSlotNew, updatedReservation.getTimeSlot());
		for(Item i: updatedReservation.getItems()) {
			assertEquals(false, i.getIsAvailable());
			assertEquals(false, i.getIsReservable());
			assertEquals(updatedReservation, i.getReservation());
		}
		assertEquals(true, book.getIsReservable());
		assertEquals(true, album.getIsReservable());
		assertEquals(null, book.getReservation());
		assertEquals(null, album.getReservation());
	}
	
	@Test
	public void testUpdateReservationIllegalTimeSlot() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		Reservation reservation = null;
		reservation = service.createReservation(items, onlineUser, timeSlot);
		
		TimeSlot timeSlotNew = new TimeSlot();
		timeSlotNew.setStartDate(Date.valueOf("2021-11-18"));
		timeSlotNew.setEndDate(Date.valueOf("2021-11-20"));
		timeSlotNew.setStartTime(Time.valueOf("10:00:00"));
		timeSlotNew.setEndTime(Time.valueOf("11:00:00"));
		timeSlotNew.setTimeSlotId(5L);
		
		Reservation newReservation = null;
		String error = "";
		try {
			newReservation = service.updateReservation(items, reservation, timeSlotNew);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(newReservation);
		assertEquals("Cannot update reservation with newStartDate before oldEndDate.", error);
	}
	
	@Test
	public void testDeleteReservation() {
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setAddress("3455 Durocher");
		onlineUser.setEmail("123@gmail.com");
		onlineUser.setFirstName("Parker");
		onlineUser.setLastName("Peter");
		onlineUser.setIsLocal(true);
		onlineUser.setPassword("12345");
		onlineUser.setUsername("SpiderMan");
		onlineUser.setUserId(2L);
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(Date.valueOf("2021-11-18"));
		timeSlot.setEndDate(Date.valueOf("2021-11-19"));
		timeSlot.setStartTime(Time.valueOf("10:00:00"));
		timeSlot.setEndTime(Time.valueOf("11:00:00"));
		timeSlot.setTimeSlotId(3L);
		
		List<Item> items = new ArrayList<>();
		
		Creator bookCreator = new Creator();
		bookCreator.setFirstName("BookCreator");
		bookCreator.setLastName("BookAuthor");
		bookCreator.setCreatorType(CreatorType.Author);
		bookCreator.setCreatorId(101L);
		Book book = new Book();
		book.setGenre(BMGenre.Action);
		book.setIsArchive(false);
		book.setIsAvailable(true);
		book.setIsReservable(true);
		book.setNumPages(100);
		book.setReleaseDate(Date.valueOf("2000-01-01"));
		book.setTitle("BookTitle");
		book.setItemId(100L);
		book.setCreator(bookCreator);
		
		items.add(book);
		Reservation reservation = null;
		reservation = service.createReservation(items, onlineUser, timeSlot);
		try {
			service.deleteReservation(reservation);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(true, book.getIsReservable());
		assertNull(reservation.getUser());
		assertNull(reservation.getItems());
		assertNull(reservation.getTimeSlot());
		assertNull(reservation.getReservationId());
		assertNull(reservation.getLibraryApplicationSystem());
	}

}
