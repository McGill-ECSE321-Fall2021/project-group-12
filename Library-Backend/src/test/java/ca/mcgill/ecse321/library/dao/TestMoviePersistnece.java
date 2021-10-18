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

import ca.mcgill.ecse321.library.LibraryApplication;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestMoviePersistnece {
	
	@Autowired
	private MovieRepository movieRepository;
	
	private LibraryApplicationSystem system = new LibraryApplicationSystem();

	@AfterEach
	public void clearDatabase() {
		
		movieRepository.deleteAll();
		system = new LibraryApplicationSystem();
	}
	
	@Test
	public void testPersistAndLoadMovie() {
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int quantityAvailable = 5;
		int quantity = 12;
		int duration = 200;
		BMGenre genre = Movie.BMGenre.Action;
		Creator creator = new Creator();
		
		Movie movie = new Movie();
		
		movie.setTitle(title);
		movie.setIsArchive(isArchive);
		movie.setIsReservable(isReservable);
		movie.setReleaseDate(releaseDate);
		movie.setQuantity(quantityAvailable);
		movie.setQuantity(quantity);
		movie.setDuration(duration);
		movie.setCreator(creator);
		movie.setGenre(genre);
		movie.setLibraryApplicationSystem(system);
	
		movieRepository.save(movie);
		Long id = movie.getItemId();
		
		movie = null;
		
		
		movie = movieRepository.findMovieByMovieId(id);
		
		assertNotNull(creator);
		assertEquals(id, movie.getItemId());
		
		
		assertEquals(title, movie.getTitle());
		assertEquals(isArchive, movie.getIsArchive());
		assertEquals(isReservable,movie.getIsReservable());
		assertEquals(releaseDate, movie.getReleaseDate());
		assertEquals(quantityAvailable, movie.getQuantityAvailable());
		assertEquals(quantity, movie.getQuantity());
		assertEquals(duration, movie.getDuration());
		assertEquals(creator, movie.getCreator());
		assertEquals(genre, movie.getGenre());
		assertEquals(movie.toString(), movie.toString());
		assertEquals(system, movie.getLibraryApplicationSystem());
	}

}
