package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestMoviePersistence {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CreatorRepository creatorRepository;

	@AfterEach
	public void clearDatabase() {
		
		movieRepository.deleteAll();
		creatorRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadMovie() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		//initializing attributes for movie
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		//initialize date
		Date releaseDate = new Date(10-18-2021);
		int duration = 200;
		boolean available = true;
		BMGenre genre = Movie.BMGenre.Action;
		//initialize creator object
		Creator creator = new Creator();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		//setting attributes
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		//save creator in persistence
		creatorRepository.save(creator);
		//initialize movie object
		Movie movie = new Movie();
		//setting attributes
		movie.setTitle(title);
		movie.setIsArchive(isArchive);
		movie.setIsReservable(isReservable);
		movie.setReleaseDate(releaseDate);
		movie.setIsAvailable(available);
		movie.setDuration(duration);
		movie.setCreator(creator);
		movie.setGenre(genre);
		movie.setLibraryApplicationSystem(system);
		//save movie object
		movieRepository.save(movie);
		Long id = movie.getItemId();
		
		movie = null;
		
		//find movie object
		movie = movieRepository.findMovieByItemId(id);
		//testing if object persisted
		assertNotNull(movie);
		assertEquals(id, movie.getItemId());
		
		
		assertEquals(title, movie.getTitle());
		assertEquals(isArchive, movie.getIsArchive());
		assertEquals(isReservable,movie.getIsReservable());
		assertNotNull(movie.getReleaseDate());
		assertEquals(available, movie.getIsAvailable());
		assertEquals(duration, movie.getDuration());
		assertNotNull(movie.getCreator());
		assertEquals(genre, movie.getGenre());
		assertNotNull(movie.getLibraryApplicationSystem());
	}

}
