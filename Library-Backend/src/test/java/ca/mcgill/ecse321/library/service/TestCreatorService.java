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
import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;

@ExtendWith(MockitoExtension.class)
public class TestCreatorService {

	@Mock
	private CreatorRepository creatorDao;
	
	@Mock
	private MovieRepository movieDao;
	
	@Mock
	private AlbumRepository albumDao;
	
	@Mock 
	private BookRepository bookDao;
	
	@Mock
	private NewspaperRepository newspaperDao;

	@InjectMocks
	private CreatorService service;

	private static final String FIRST_NAME = "First";
	private static final String LAST_NAME = "Last";
	private static final CreatorType CREATOR_TYPE = CreatorType.Artist;

	private static final Long CREATOR_KEY = 0L;


	@BeforeEach
	public void setMockOutput() {
		lenient().when(creatorDao.findCreatorByCreatorId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CREATOR_KEY)) {
				Creator creator = new Creator();
				creator.setFirstName(FIRST_NAME);
				creator.setLastName(LAST_NAME);
				creator.setCreatorType(CREATOR_TYPE);
				creator.setCreatorId(CREATOR_KEY);
				return creator;
			}else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(creatorDao.save(any(Creator.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(albumDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {	
			List<Album> albums = new ArrayList<Album>();
			String title1  = "albumTitle1";
			boolean isArchive1 = false;
			boolean isReservable1 = true;
			Date releaseDate1 = Date.valueOf("2021-11-05");
			int numSongs1 = 8;
			boolean isAvailable1 = true;
			MusicGenre genre1 = MusicGenre.Classical;
			Long albumId1 = 10L;
			Album album1 = new Album();
			album1.setTitle(title1);
			album1.setIsArchive(isArchive1);
			album1.setIsReservable(isReservable1);
			album1.setReleaseDate(releaseDate1);
			album1.setNumSongs(numSongs1);
			album1.setIsAvailable(isAvailable1);
			album1.setGenre(genre1);
			album1.setItemId(albumId1);
			album1.setCreator(creatorDao.findCreatorByCreatorId(CREATOR_KEY));
			
			String title2  = "albumTitle2";
			boolean isArchive2 = true;
			boolean isReservable2 = false;
			Date releaseDate2 = Date.valueOf("2021-08-01");
			int numSongs2 = 21;
			boolean isAvailable2 = false;
			MusicGenre genre2 = MusicGenre.Mixed;
			Long albumId2 = 11L;
			Album album2 = new Album();
			album2.setTitle(title2);
			album2.setIsArchive(isArchive2);
			album2.setIsReservable(isReservable2);
			album2.setReleaseDate(releaseDate2);
			album2.setNumSongs(numSongs2);
			album2.setIsAvailable(isAvailable2);
			album2.setGenre(genre2);
			album2.setItemId(albumId2);
			album2.setCreator(creatorDao.findCreatorByCreatorId(CREATOR_KEY));
			
			albums.add(album1);
			albums.add(album2);
			
			return albums;
		});
		
		lenient().when(bookDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {	
			List<Book> books = new ArrayList<Book>();
			String title  = "bookTitle";
			boolean isArchive = true;
			boolean isReservable = true;
			Date releaseDate = Date.valueOf("2021-7-27");
			boolean isAvailable = true;
			int numPages = 531;
			Long bookId = 12L;
			Book.BMGenre genre = Book.BMGenre.Action;
			Book book = new Book();
			
			book.setTitle(title);
			book.setIsArchive(isArchive);
			book.setIsReservable(isReservable);
			book.setReleaseDate(releaseDate);
			book.setNumPages(numPages);
			book.setIsAvailable(isAvailable);
			book.setGenre(genre);
			book.setCreator(creatorDao.findCreatorByCreatorId(CREATOR_KEY));
			book.setItemId(bookId);
			
			books.add(book);
			return books;
		});
		
		lenient().when(movieDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {	
			List<Movie> movies = new ArrayList<Movie>();
			String title  = "movieTitle";
			boolean isArchive = false;
			boolean isReservable = false;
			Date releaseDate = Date.valueOf("2021-1-17");
			boolean isAvailable = false;
			int duration = 125;
			Long movieId = 13L;
			Movie.BMGenre genre = Movie.BMGenre.Fiction;
			Movie movie = new Movie();
			
			movie.setTitle(title);
			movie.setIsArchive(isArchive);
			movie.setIsReservable(isReservable);
			movie.setReleaseDate(releaseDate);
			movie.setDuration(duration);
			movie.setIsAvailable(isAvailable);
			movie.setGenre(genre);
			movie.setCreator(creatorDao.findCreatorByCreatorId(CREATOR_KEY));
			movie.setItemId(movieId);
			
			movies.add(movie);
			return movies;
		});
		
		lenient().when(newspaperDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {	
			List<Newspaper> newspapers = new ArrayList<Newspaper>();
			String title  = "newspaperTitle";
			boolean isArchive = false;
			boolean isAvailable = false;
			boolean isReservable = false;
			Date releaseDate = Date.valueOf("2021-1-17");
			Long newspaperId = 14L;
			Newspaper newspaper = new Newspaper();			
			newspaper.setTitle(title);
			newspaper.setIsArchive(isArchive);
			newspaper.setIsReservable(isReservable);
			newspaper.setReleaseDate(releaseDate);
			newspaper.setIsAvailable(isAvailable);
			newspaper.setCreator(creatorDao.findCreatorByCreatorId(CREATOR_KEY));
			newspaper.setItemId(newspaperId);
			
			newspapers.add(newspaper);
			return newspapers;
		});
	}

	@Test
	public void testCreateCreator() {
		assertEquals(0, service.getAllCreators().size());

		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
	}

	@Test
	public void  testCreateCreatorNullFirstName() {
		String firstName = null;
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Director;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty first name.", error);
	}

	@Test
	public void  testCreateCreatorNullLastName() {
		String firstName = "First";
		String lastName = null;
		CreatorType creatorType = CreatorType.Director;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty last name.", error);
	}

	@Test
	public void  testCreateCreatorNullCreatorType() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = null;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty creator type.", error);
	}

	@Test
	public void  testCreateCreatorEmptyFirstName() {
		String firstName = "";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty first name.", error);
	}

	@Test
	public void  testCreateCreatorEmptyLastName() {
		String firstName = "First";
		String lastName = "";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty last name.", error);
	}

	@Test
	public void  testCreateCreatorSpacesFirstName() {
		String firstName = " ";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty first name.", error);
	}
	
	@Test
	public void  testCreateCreatorSpacesLastName() {
		String firstName = "First";
		String lastName = " ";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(creator);
		assertEquals("A creator cannot have an empty last name.", error);
	}

	@Test
	public void updateCreator() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Author;
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (Exception e) {
			fail();
		}
		
		assertNotNull(creator);
		assertEquals(newFirstName, creator.getFirstName());
		assertEquals(newLastName, creator.getLastName());
		assertEquals(newCreatorType.toString(), creator.getCreatorType().toString());
	}
	
	@Test
	public void updateCreatorNullFirstName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = null;
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty first name.", error);
	}
	
	@Test
	public void updateCreatorEmptyFirstName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty first name.", error);
	}
	
	@Test
	public void updateCreatorSpacesFirstName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "    ";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty first name.", error);
	}
	
	@Test
	public void updateCreatorNullLastName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "NewFirst";
		String newLastName = null;
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty last name.", error);
	}
	
	@Test
	public void updateCreatorEmptyLastName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "NewFirst";
		String newLastName = "";
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty last name.", error);
	}
	
	@Test
	public void updateCreatorSpacesLastName() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "NewFirst";
		String newLastName = "     ";
		CreatorType newCreatorType = CreatorType.Author;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty last name.", error);
	}
	
	@Test
	public void updateCreatorNullCreatorType() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = null;
		
		String error = "";
		
		try {
			creator = service.updateCreator(CREATOR_KEY, newFirstName, newLastName, newCreatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO CHANGES HAVE BEEN MADE
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		
		assertEquals("A creator cannot have an empty creator type.", error);
	}
	
	@Test
	public void testGetCreator() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		assertNotNull(creator);
		Creator creator2 = null;
		try {
			creator2 = service.getCreator(CREATOR_KEY);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(creator2);
		assertEquals(creator.getFirstName(), creator2.getFirstName());
		assertEquals(creator.getLastName(), creator2.getLastName());
		assertEquals(creator.getCreatorType(), creator2.getCreatorType());
	}
	
	@Test
	public void testGetItemsByCreator() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		creator.setCreatorId(CREATOR_KEY);
		List<Item> items = null;
		try {
			 items = service.getItemsByCreator(CREATOR_KEY);
		} catch (Exception e) {
			fail();
		}
		Album album1 = null;
		Album album2 = null;
		Book book = null;
		Movie movie = null;
		Newspaper newspaper = null;
		for (Item i:items) {
			if (i.getItemId().equals(10L)) {
				album1 = (Album)i;
			} else if (i.getItemId().equals(11L)) {
				album2 = (Album)i;
			} else if (i.getItemId().equals(12L)) {
				book = (Book)i;
			} else if (i.getItemId().equals(13L)) {
				movie = (Movie)i;
			} else if (i.getItemId().equals(14L)) {
				newspaper = (Newspaper)i;
			}
		}
		assertNotNull(album1);
		assertNotNull(album2);
		assertNotNull(book);
		assertNotNull(movie);
		assertNotNull(newspaper);
		assertNotNull(creator);
		assertNotNull(items);
		assertEquals(5, items.size());
	}
}
