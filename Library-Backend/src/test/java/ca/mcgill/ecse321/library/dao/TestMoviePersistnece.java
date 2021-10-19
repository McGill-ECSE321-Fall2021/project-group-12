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
public class TestMoviePersistnece {
	
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
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int quantityAvailable = 5;
		int quantity = 12;
		int duration = 200;
		BMGenre genre = Movie.BMGenre.Action;
		Creator creator = new Creator();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		creatorRepository.save(creator);
		
		Movie movie = new Movie();
		
		movie.setTitle(title);
		movie.setIsArchive(isArchive);
		movie.setIsReservable(isReservable);
		movie.setReleaseDate(releaseDate);
		movie.setQuantityAvailable(quantityAvailable);
		movie.setQuantity(quantity);
		movie.setDuration(duration);
		movie.setCreator(creator);
		movie.setGenre(genre);
		movie.setLibraryApplicationSystem(system);
	
		movieRepository.save(movie);
		Long id = movie.getItemId();
		
		movie = null;
		
		
		movie = movieRepository.findMovieByItemId(id);
		
		assertNotNull(movie);
		assertEquals(id, movie.getItemId());
		
		
		assertEquals(title, movie.getTitle());
		assertEquals(isArchive, movie.getIsArchive());
		assertEquals(isReservable,movie.getIsReservable());
		assertNotNull(movie.getReleaseDate());
		assertEquals(quantityAvailable, movie.getQuantityAvailable());
		assertEquals(quantity, movie.getQuantity());
		assertEquals(duration, movie.getDuration());
		assertNotNull(movie.getCreator());
		assertEquals(genre, movie.getGenre());
		assertNotNull(movie.getLibraryApplicationSystem());
	}

}
