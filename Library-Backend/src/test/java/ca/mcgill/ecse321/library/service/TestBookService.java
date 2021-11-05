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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Book.BMGenre;

@ExtendWith(MockitoExtension.class)
public class TestBookService {
	
	@Mock
	private BookRepository bookDao;
	
	@Mock 
	private CreatorRepository creatorRepository;
	
	@InjectMocks
	private BookService bookService;
	
	private static final String TITLE = "Title";
	private static final boolean IS_ARCHIVE = false;
	private static final boolean IS_RESERVABLE = true;
	private static final Date RELEASE_DATE = Date.valueOf("2021-10-31");
	private static final int NUM_PAGES = 150;
	private static final boolean IS_AVAILABLE = true;
	private static final BMGenre GENRE = BMGenre.Action;

	private static final Long BOOK_ID = 1L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(bookDao.findBookByItemId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BOOK_ID)) {
				Book book = new Book();
				book.setTitle(TITLE);
				book.setIsArchive(IS_ARCHIVE);
				book.setIsReservable(IS_RESERVABLE);
				book.setReleaseDate(RELEASE_DATE);
				book.setNumPages(NUM_PAGES);
				book.setIsAvailable(IS_AVAILABLE);
				book.setGenre(GENRE);
				Creator creator = new Creator();
				creator.setFirstName("First");
				creator.setLastName("Last");
				creator.setCreatorType(CreatorType.Author);
				creator.setCreatorId(100L);
				book.setCreator(creator);
				return book;
			}else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateBook() {
		assertEquals(0, bookService.getAllBooks().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(book);
		assertEquals(title, book.getTitle());
		assertEquals(isArchive, book.getIsArchive());
		assertEquals(isReservable, book.getIsReservable());
		assertEquals(releaseDate, book.getReleaseDate());
		assertEquals(numPages, book.getNumPages());
		assertEquals(isAvailable, book.getIsAvailable());
		assertEquals(genre, book.getGenre());
		assertNotNull(book.getCreator());
		assertEquals(100L, book.getCreator().getCreatorId());
	}
	
	@Test
	public void testCreateBookNullTitle() {

		String title = null;
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("Cannot create book with empty title.", error);
	}
	
	@Test
	public void testCreateBookEmptyTitle() {

		String title = "";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("Cannot create book with empty title.", error);
	}
	
	@Test
	public void testCreateBookSpacesTitle() {

		String title = "        ";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("Cannot create book with empty title.", error);
	}
	
	@Test
	public void testCreateBookNullDate() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = null;
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("Cannot create book with empty date.", error);
	}
	
	@Test
	public void testCreateBookNullCreator() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		Creator creator = null;
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("A book cannot have an empty creator.", error);
	}
	
	@Test
	public void testCreateBookZeroPages() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 0;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("A book cannot have a length less or equal to zero.", error);
	}
	
	@Test
	public void testCreateBookNegPages() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = -150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Book book = null;
		
		try {
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(book);
		assertEquals("A book cannot have a length less or equal to zero.", error);
	}
	
	@Test
	public void testUpdateBook() {
		assertEquals(0, bookService.getAllBooks().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Book book = null;
		
		try {
			isArchive = false;
			isReservable = false;
			isAvailable = false;
			
			book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
			book = bookService.updateBook(BOOK_ID, isArchive, isReservable, isAvailable);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(book);
		assertEquals(title, book.getTitle());
		assertEquals(isArchive, book.getIsArchive());
		assertEquals(isReservable, book.getIsReservable());
		assertEquals(releaseDate, book.getReleaseDate());
		assertEquals(numPages, book.getNumPages());
		assertEquals(isAvailable, book.getIsAvailable());
		assertEquals(genre, book.getGenre());
		assertNotNull(book.getCreator());
		assertEquals(100L, book.getCreator().getCreatorId());
	}
	
	@Test
	public void testGetBook() {
		assertEquals(0, bookService.getAllBooks().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Book book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		assertNotNull(book); 
		Book book2 = null;
		
		try {	
			book2 = bookService.getBook(BOOK_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(book2);
		assertEquals(title, book2.getTitle());
		assertEquals(isArchive, book2.getIsArchive());
		assertEquals(isReservable, book2.getIsReservable());
		assertEquals(releaseDate, book2.getReleaseDate());
		assertEquals(numPages, book2.getNumPages());
		assertEquals(isAvailable, book2.getIsAvailable());
		assertEquals(genre, book2.getGenre());
		assertNotNull(book2.getCreator());
		assertEquals(100L, book2.getCreator().getCreatorId());
	}
	
	@Test
	public void testDeleteBook() {
		assertEquals(0, bookService.getAllBooks().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numPages = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Book book = bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, isAvailable, genre, creator);
		assertNotNull(book); 
		
		try {	
			book = bookService.deleteBook(BOOK_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(0, bookService.getAllBooks().size());		
	}

}
