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

import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@ExtendWith(MockitoExtension.class)
public class TestCreatorService {

	@Mock
	private CreatorRepository creatorDao;

	@InjectMocks
	private CreatorService service;

	private static final String FIRST_NAME = "First";
	private static final String LAST_NAME = "Last";
	private static final CreatorType CREATOR_TYPE = CreatorType.Artist;

	private static final Long CREATOR_KEY = 0L;
	
	private static final Long CREATOR2_KEY = 1L;


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
			}else if (invocation.getArgument(0).equals(CREATOR2_KEY)) {
				String title = "Movie Title";
				boolean isArchive = false;
				boolean isReservable = true;
				Date releaseDate = Date.valueOf("2021-11-02");
				boolean isAvailable = true;
				int duration = 3;
				BMGenre genre = BMGenre.Action;
				Movie movie = new Movie();
				movie.setTitle(title);
				movie.setIsArchive(isArchive);
				movie.setIsReservable(isReservable);
				movie.setReleaseDate(releaseDate);
				movie.setIsAvailable(isAvailable);
				movie.setDuration(duration);
				movie.setGenre(genre);
				
				String firstName = "First";
				String lastName = "Last";
				CreatorType creatorType = CreatorType.Artist;
				Creator creator = new Creator();
				creator.setFirstName(firstName);
				creator.setLastName(lastName);
				creator.setCreatorType(creatorType);
				return creator;
			}else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(creatorDao.save(any(Creator.class))).thenAnswer(returnParameterAsAnswer);
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
	public void testAddItemToCreator() {
		String title = "Movie Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-11-02");
		boolean isAvailable = true;
		int duration = 3;
		BMGenre genre = BMGenre.Action;
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setIsArchive(isArchive);
		movie.setIsReservable(isReservable);
		movie.setReleaseDate(releaseDate);
		movie.setIsAvailable(isAvailable);
		movie.setDuration(duration);
		movie.setGenre(genre);
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		// CREATION IS ALREADY TESTED ABOVE
		creator = service.createCreator(firstName, lastName, creatorType);
		
		try {
			creator = service.addItemToCreator(CREATOR_KEY, movie);
		} catch (Exception e) {
			fail();
		}
		
		assertNotNull(creator);
		assertNotNull(creator.getItems());
		assertEquals(1, creator.getItems().size());
		Movie itemMovie = (Movie)creator.getItems().get(0);
		assertEquals(movie.getTitle(), itemMovie.getTitle());
		assertEquals(movie.getIsArchive(), itemMovie.getIsArchive());
		assertEquals(movie.getIsReservable(), itemMovie.getIsReservable());
		assertEquals(movie.getReleaseDate(), itemMovie.getReleaseDate());
		assertEquals(movie.getIsAvailable(), itemMovie.getIsAvailable());
		assertEquals(movie.getDuration(), itemMovie.getDuration());
		assertEquals(movie.getGenre(), itemMovie.getGenre());
		assertNotNull(itemMovie.getCreator());
		Creator itemCreator = itemMovie.getCreator();
		assertEquals(creator.getFirstName(), itemCreator.getFirstName());
		assertEquals(creator.getLastName(), itemCreator.getLastName());
		assertEquals(creator.getCreatorType(), itemCreator.getCreatorType());
	}

}
