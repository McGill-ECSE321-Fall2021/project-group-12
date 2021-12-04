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
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@ExtendWith(MockitoExtension.class)
public class TestMovieService {
	
	@Mock
	private MovieRepository movieDao;
	
	@Mock 
	private CreatorRepository creatorRepository;
	
	@InjectMocks
	private MovieService movieService;
	
	private static final String TITLE = "Title";
	private static final boolean IS_ARCHIVE = false;
	private static final boolean IS_RESERVABLE = true;
	private static final Date RELEASE_DATE = Date.valueOf("2021-10-31");
	private static final int DURATION = 150;
	private static final boolean IS_AVAILABLE = true;
	private static final BMGenre GENRE = BMGenre.Action;

	private static final Long MOVIE_ID = 1L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(movieDao.findMovieByItemId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(MOVIE_ID)) {
				Movie movie = new Movie();
				movie.setTitle(TITLE);
				movie.setIsArchive(IS_ARCHIVE);
				movie.setIsReservable(IS_RESERVABLE);
				movie.setReleaseDate(RELEASE_DATE);
				movie.setDuration(DURATION);
				movie.setIsAvailable(IS_AVAILABLE);
				movie.setGenre(GENRE);
				Creator creator = new Creator();
				creator.setFirstName("First");
				creator.setLastName("Last");
				creator.setCreatorType(CreatorType.Author);
				creator.setCreatorId(100L);
				movie.setCreator(creator);
				return movie;
			}else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(movieDao.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateMovie() {
		assertEquals(0, movieService.getAllMovies().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
		assertEquals(isArchive, movie.getIsArchive());
		assertEquals(isReservable, movie.getIsReservable());
		assertEquals(releaseDate, movie.getReleaseDate());
		assertEquals(duration, movie.getDuration());
		assertEquals(isAvailable, movie.getIsAvailable());
		assertEquals(genre, movie.getGenre());
		assertNotNull(movie.getCreator());
		assertEquals(100L, movie.getCreator().getCreatorId());
	}
	
	@Test
	public void testCreateMovieNullTitle() {

		String title = null;
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("Cannot create movie with empty title.", error);
	}
	
	@Test
	public void testCreateMovieEmptyTitle() {

		String title = "";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("Cannot create movie with empty title.", error);
	}
	
	@Test
	public void testCreateMovieSpacesTitle() {

		String title = "        ";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("Cannot create movie with empty title.", error);
	}
	
	@Test
	public void testCreateMovieNullDate() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = null;
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("Cannot create movie with empty date.", error);
	}
	
	@Test
	public void testCreateMovieNullCreator() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
		boolean isAvailable = true;
		BMGenre genre = BMGenre.Action;
		
		Creator creator = null;
		
		String error = "";
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("A movie cannot have an empty creator.", error);
	}
	
	@Test
	public void testCreateMovieZeroDuration() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 0;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("A movie cannot have a duration less or equal to zero.", error);
	}
	
	@Test
	public void testCreateMovieNegDuration() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = -150;
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
		
		Movie movie = null;
		
		try {
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(movie);
		assertEquals("A movie cannot have a duration less or equal to zero.", error);
	}
	
	@Test
	public void testUpdateMovie() {
		assertEquals(0, movieService.getAllMovies().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = null;
		
		try {
			isArchive = false;
			isReservable = false;
			isAvailable = false;
			
			movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
			movie = movieService.updateMovie(MOVIE_ID, isArchive, isReservable, isAvailable);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
		assertEquals(isArchive, movie.getIsArchive());
		assertEquals(isReservable, movie.getIsReservable());
		assertEquals(releaseDate, movie.getReleaseDate());
		assertEquals(duration, movie.getDuration());
		assertEquals(isAvailable, movie.getIsAvailable());
		assertEquals(genre, movie.getGenre());
		assertNotNull(movie.getCreator());
		assertEquals(100L, movie.getCreator().getCreatorId());
	}
	
	@Test
	public void testGetMovie() {
		assertEquals(0, movieService.getAllMovies().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		assertNotNull(movie); 
		Movie movie2 = null;
		
		try {	
			movie2 = movieService.getMovie(MOVIE_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(movie2);
		assertEquals(title, movie2.getTitle());
		assertEquals(isArchive, movie2.getIsArchive());
		assertEquals(isReservable, movie2.getIsReservable());
		assertEquals(releaseDate, movie2.getReleaseDate());
		assertEquals(duration, movie2.getDuration());
		assertEquals(isAvailable, movie2.getIsAvailable());
		assertEquals(genre, movie2.getGenre());
		assertNotNull(movie2.getCreator());
		assertEquals(100L, movie2.getCreator().getCreatorId());
	}
	
	@Test
	public void testDeleteMovie() {
		assertEquals(0, movieService.getAllMovies().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int duration = 150;
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
		
		Movie movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		assertNotNull(movie); 
		
		try {	
			movie = movieService.deleteMovie(MOVIE_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		 
		assertEquals(0, movieService.getAllMovies().size());		
	}

}
