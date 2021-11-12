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
import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
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
	private TimeSlotRepository timeSlotDao;
	@Mock
	private OnlineUserRepository onlineUserDao;
	@Mock
	private CreatorRepository creatorDao;
	
	@InjectMocks
	private ReservationService service;
	
	private static final Long RESERVATION_ID = 0L;
	private static final Long USER_ID = 2L;
	private static final Long TIMESLOT_ID = 3L;
	private static final Long BOOK_ID = 100L;
	private static final Long CREATOR_ID = 101L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(reservationDao.findReservationByReservationId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(RESERVATION_ID)) {
				
				Reservation reservation = new Reservation();
				reservation.setReservationId(RESERVATION_ID);
				
				OnlineUser onlineUser = new OnlineUser();
				onlineUser.setAddress("3455 Durocher");
				onlineUser.setEmail("123@gmail.com");
				onlineUser.setFirstName("Parker");
				onlineUser.setLastName("Peter");
				onlineUser.setIsLocal(true);
				onlineUser.setPassword("12345");
				onlineUser.setUsername("SpiderMan");
				onlineUser.setUserId(USER_ID);
				reservation.setUser(onlineUser);
				
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartDate(Date.valueOf("2021-11-18"));
				timeSlot.setEndDate(Date.valueOf("2021-11-19"));
				timeSlot.setStartTime(Time.valueOf("10:00:00"));
				timeSlot.setEndTime(Time.valueOf("11:00:00"));
				timeSlot.setTimeSlotId(TIMESLOT_ID);
				reservation.setTimeSlot(timeSlot);
				
				List<Item> items = new ArrayList<>();
				reservation.setItems(items);
				
				Creator bookCreator = new Creator();
				bookCreator.setFirstName("BookCreator");
				bookCreator.setLastName("BookAuthor");
				bookCreator.setCreatorType(CreatorType.Author);
				bookCreator.setCreatorId(CREATOR_ID);
				Book book = new Book();
				book.setGenre(BMGenre.Action);
				book.setIsArchive(false);
				book.setIsAvailable(false);
				book.setIsReservable(false);
				book.setNumPages(100);
				book.setReleaseDate(Date.valueOf("2000-01-01"));
				book.setTitle("BookTitle");
				book.setItemId(BOOK_ID);
				book.setCreator(bookCreator);
				book.setReservation(reservation);
				
				reservation.addItem(book);
				
				
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
				
				reservation.addItem(movie);
				reservation.addItem(album);
				
				return reservation;
			} else {
				return null;
			}
		});
		
		lenient().when(reservationDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			
			List<Reservation> reservations = new ArrayList<>();
			Reservation reservation = new Reservation();
			Reservation reservation2 = new Reservation();
			Reservation reservation3 = new Reservation();
			Reservation reservation4 = new Reservation();
			reservation.setReservationId(RESERVATION_ID);
			reservation2.setReservationId(3000L);
			reservation3.setReservationId(3001L);
			reservation4.setReservationId(3002L);
				
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setAddress("3455 Durocher");
			onlineUser.setEmail("123@gmail.com");
			onlineUser.setFirstName("Parker");
			onlineUser.setLastName("Peter");
			onlineUser.setIsLocal(true);
			onlineUser.setPassword("12345");
			onlineUser.setUsername("SpiderMan");
			onlineUser.setUserId(USER_ID);
			reservation.setUser(onlineUser);
			reservation2.setUser(onlineUser);
			reservation3.setUser(onlineUser);
			
			OnlineUser onlineUser2 = new OnlineUser();
			onlineUser2.setAddress("3450 Durocher");
			onlineUser2.setEmail("321@gmail.com");
			onlineUser2.setFirstName("John");
			onlineUser2.setLastName("Smith");
			onlineUser2.setIsLocal(true);
			onlineUser2.setPassword("54321");
			onlineUser2.setUsername("Mr.J");
			onlineUser2.setUserId(233L);
			reservation4.setUser(onlineUser2);
				
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setStartDate(Date.valueOf("2021-11-18"));
			timeSlot.setEndDate(Date.valueOf("2021-11-19"));
			timeSlot.setStartTime(Time.valueOf("10:00:00"));
			timeSlot.setEndTime(Time.valueOf("11:00:00"));
			timeSlot.setTimeSlotId(TIMESLOT_ID);
			reservation.setTimeSlot(timeSlot);
			
			reservation4.setTimeSlot(timeSlot);
			
			TimeSlot timeSlot2 = new TimeSlot();
			timeSlot2.setStartDate(Date.valueOf("2021-11-18"));
			timeSlot2.setEndDate(Date.valueOf("2021-11-20"));
			timeSlot2.setStartTime(Time.valueOf("10:00:00"));
			timeSlot2.setEndTime(Time.valueOf("11:00:00"));
			timeSlot2.setTimeSlotId(1000L);
			reservation2.setTimeSlot(timeSlot2);
			
			TimeSlot timeSlot3 = new TimeSlot();
			timeSlot3.setStartDate(Date.valueOf("2021-11-18"));
			timeSlot3.setEndDate(Date.valueOf("2021-11-25"));
			timeSlot3.setStartTime(Time.valueOf("10:00:00"));
			timeSlot3.setEndTime(Time.valueOf("11:00:00"));
			timeSlot3.setTimeSlotId(2000L);
			reservation3.setTimeSlot(timeSlot3);
			
			List<Item> items = new ArrayList<>();
			List<Item> items2 = new ArrayList<>();
			List<Item> items3 = new ArrayList<>();
			List<Item> items4 = new ArrayList<>();
			reservation.setItems(items);
			reservation2.setItems(items2);
			reservation3.setItems(items3);
			reservation4.setItems(items4);
			
			Creator bookCreator = new Creator();
			bookCreator.setFirstName("BookCreator");
			bookCreator.setLastName("BookAuthor");
			bookCreator.setCreatorType(CreatorType.Author);
			bookCreator.setCreatorId(CREATOR_ID);
			Book book = new Book();
			book.setGenre(BMGenre.Action);
			book.setIsArchive(false);
			book.setIsAvailable(false);
			book.setIsReservable(false);
			book.setNumPages(100);
			book.setReleaseDate(Date.valueOf("2000-01-01"));
			book.setTitle("BookTitle");
			book.setItemId(BOOK_ID);
			book.setCreator(bookCreator);
			book.setReservation(reservation);
			
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
			movie.setReservation(reservation2);
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
			album.setReservation(reservation3);
			album.setTitle("AlbumTitle");
			album.setCreator(albumCreator);
			
			Album album2 = new Album();
			album2.setGenre(MusicGenre.Country);
			album2.setIsArchive(false);
			album2.setIsAvailable(false);
			album2.setIsReservable(false);
			album2.setItemId(111L);
			album2.setNumSongs(10);
			album2.setReleaseDate(Date.valueOf("2000-01-04"));
			album2.setReservation(reservation4);
			album2.setTitle("Album2Title");
			album2.setCreator(albumCreator);
			
			reservation.addItem(book);
			reservation2.addItem(movie);
			reservation3.addItem(album);
			reservation4.addItem(album2);
			
			reservations.add(reservation4);
			reservations.add(reservation3);
			reservations.add(reservation2);
			reservations.add(reservation);
			
			return reservations;
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(reservationDao.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(movieDao.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(albumDao.save(any(Album.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(creatorDao.save(any(Creator.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(onlineUserDao.save(any(OnlineUser.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateReservation() {
		
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
	public void testCreateReservationWithIdenticalItems() {
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

		items.add(book);
		items.add(movie);
		items.add(book);
		Reservation reservation = null;
		String error = "";
		try {
			reservation = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Cannot create reservation with identical items.", error);
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
		assertEquals(true, movie.getIsAvailable());
		assertEquals(true, movie.getIsReservable());
		assertNull(movie.getReservation());
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
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
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
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
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
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
		
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
		assertEquals(true, album.getIsAvailable());
		assertEquals(false, album.getIsReservable());
		assertNull(album.getReservation());
		
		items.add(book);
		Reservation reservation3 = null;
		try {
			reservation3 = service.createReservation(items, onlineUser, timeSlot);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(reservation3);
		assertEquals("At least one item selected is not reseverable.", error);
		assertEquals(false, movie.getIsAvailable());
		assertEquals(true, movie.getIsReservable());
		assertNull(book.getReservation());
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
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
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
		assertEquals(true, book.getIsAvailable());
		assertEquals(true, book.getIsReservable());
		assertNull(book.getReservation());
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
		
		items.add(movie);
		items.add(book);
		Reservation reservation = null;
		reservation = service.createReservation(items, onlineUser, timeSlot);
		
		TimeSlot timeSlotNew = new TimeSlot();
		timeSlotNew.setStartDate(Date.valueOf("2021-11-19"));
		timeSlotNew.setEndDate(Date.valueOf("2021-11-20"));
		timeSlotNew.setStartTime(Time.valueOf("11:00:00"));
		timeSlotNew.setEndTime(Time.valueOf("12:00:00"));
		timeSlotNew.setTimeSlotId(5L);
		
		Reservation updatedReservation = null;
		try {
			updatedReservation = service.updateReservation(reservation, timeSlotNew);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(updatedReservation);
		assertEquals(updatedReservation.getTimeSlot().getTimeSlotId(), timeSlotNew.getTimeSlotId());
		assertEquals(updatedReservation.getUser().getUserId(), onlineUser.getUserId());
		assertEquals(updatedReservation.getItems().size(), 2);
		assertEquals(updatedReservation.getItems().get(0).getItemId(), movie.getItemId());
		assertEquals(updatedReservation.getItems().get(1).getItemId(), book.getItemId());
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
			newReservation = service.updateReservation(reservation, timeSlotNew);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(newReservation);
		assertEquals("Cannot update reservation with newStartDate before oldEndDate.", error);
		assertEquals(false, book.getIsAvailable());
		assertEquals(false, book.getIsReservable());
		assertEquals(reservation, book.getReservation());
		
		TimeSlot timeSlotNull = null;
		try {
			newReservation = service.updateReservation(reservation, timeSlotNull);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(newReservation);
		assertEquals("Cannot update reservation with empty arguments.", error);
		assertEquals(false, book.getIsAvailable());
		assertEquals(false, book.getIsReservable());
		assertEquals(reservation, book.getReservation());
		
		TimeSlot timeSlotEmpty = new TimeSlot();
		try {
			newReservation = service.updateReservation(reservation, timeSlotEmpty);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(newReservation);
		assertEquals("Cannot update reservation with missing info in timeSlot.", error);
		assertEquals(false, book.getIsAvailable());
		assertEquals(false, book.getIsReservable());
		assertEquals(reservation, book.getReservation());
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
		try {
			service.deleteReservation(reservation);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(reservation);
		assertEquals(false, book.getIsReservable());
		assertEquals(false, movie.getIsReservable());
		assertEquals(false, album.getIsReservable());
		assertEquals(false, book.getIsAvailable());
		assertEquals(false, movie.getIsAvailable());
		assertEquals(false, album.getIsAvailable());
		assertEquals(timeSlot.getTimeSlotId(), reservation.getTimeSlot().getTimeSlotId());
		assertEquals(onlineUser.getUserId(), reservation.getUser().getUserId());
	}
	
	@Test
	public void testGetReservationById() {
		
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
		
		items.add(book);
		items.add(movie);
		items.add(album);
		
		Reservation reservation = service.createReservation(items, onlineUser, timeSlot);
		Reservation createdReservation = null;
		try {
			createdReservation = service.getReservation(RESERVATION_ID);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(createdReservation);
		assertEquals(createdReservation.getTimeSlot().getTimeSlotId(), reservation.getTimeSlot().getTimeSlotId());
		assertEquals(createdReservation.getTimeSlot().getEndDate(), reservation.getTimeSlot().getEndDate());
		assertEquals(createdReservation.getTimeSlot().getEndTime(), reservation.getTimeSlot().getEndTime());
		assertEquals(createdReservation.getTimeSlot().getStartDate(), reservation.getTimeSlot().getStartDate());
		assertEquals(createdReservation.getTimeSlot().getStartTime(), reservation.getTimeSlot().getStartTime());
		assertEquals(createdReservation.getUser().getUserId(), reservation.getUser().getUserId());
		assertEquals(createdReservation.getUser().getAddress(), reservation.getUser().getAddress());
		assertEquals(createdReservation.getUser().getFirstName(), reservation.getUser().getFirstName());
		assertEquals(createdReservation.getUser().getIsLocal(), reservation.getUser().getIsLocal());
		assertEquals(createdReservation.getUser().getLastName(), reservation.getUser().getLastName());
		for(Item i: createdReservation.getItems()) {
			Boolean checked = false;
			for(Item j : reservation.getItems()) {
				if(j.getItemId() == i.getItemId()) {
					checked = true;
					break;
				}
			}
			assertEquals(true, checked);
		}
	}
	
	@Test
	public void testGetAllReservations() {
		assertEquals(4, service.getAllReservations().size());
	}
	
	@Test
	public void testGetReservationsByUser() {
		OnlineUser onlineUser2 = new OnlineUser();
		onlineUser2.setAddress("3450 Durocher");
		onlineUser2.setEmail("321@gmail.com");
		onlineUser2.setFirstName("John");
		onlineUser2.setLastName("Smith");
		onlineUser2.setIsLocal(true);
		onlineUser2.setPassword("54321");
		onlineUser2.setUsername("Mr.J");
		onlineUser2.setUserId(233L);
		
		List<Reservation> reservations = null;
		try {
			reservations = service.getReservationsByUser(onlineUser2);
		} catch (Exception e) {
			fail();
		}
		assertEquals(1, reservations.size());
		assertEquals(reservations.get(0).getUser().getUserId(), onlineUser2.getUserId());
		assertEquals(reservations.get(0).getUser().getLastName(), onlineUser2.getLastName());
		assertEquals(reservations.get(0).getUser().getIsLocal(), onlineUser2.getIsLocal());
		assertEquals(reservations.get(0).getUser().getFirstName(), onlineUser2.getFirstName());
		assertEquals(reservations.get(0).getUser().getAddress(), onlineUser2.getAddress());
		OnlineUser user2 = (OnlineUser) reservations.get(0).getUser();
		assertEquals(user2.getEmail(), onlineUser2.getEmail());
		assertEquals(user2.getUsername(), onlineUser2.getUsername());
		assertEquals(user2.getPassword(), onlineUser2.getPassword());
	}

}
