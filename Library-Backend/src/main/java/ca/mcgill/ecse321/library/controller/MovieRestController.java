package ca.mcgill.ecse321.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;
import ca.mcgill.ecse321.library.service.CreatorService;
import ca.mcgill.ecse321.library.service.MovieService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CreatorService creatorService;
	
	@GetMapping(value = {"/movies", "/movies/"})
	public List<MovieDto> getAllMovies() {
		return movieService.getAllMovies().stream().map(p -> convertToDto(p)).collect(Collectors.toList());	
	}
	
	@GetMapping(value = {"/movie", "/movie/"})
	public MovieDto getMovie(@RequestParam("itemId") Long itemId) {
		return convertToDto(movieService.getMovie(itemId));
	}
	
	@GetMapping(value = {"/movie/creator", "/movie/creator/"})
	public CreatorDto getMovieCreator(@RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(movieService.getMovie(itemId)).getCreator();
	}
	
	@PostMapping(value = {"/movie/create", "/movie/create/"})
	public MovieDto createMovie(@RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") String releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Movie movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, date, duration, genre, creator);
		return convertToDto(movie);
	}
	
	@PutMapping(value = {"/movie/update", "/movie/update/"})
	public MovieDto updateMovie(@RequestParam("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable) {
		return convertToDto(movieService.updateMovie(itemId, isArchive, isReservable, isAvailable));
	}
	
	@DeleteMapping(value = { "/movie/delete", "/movie/delete/"})
	public MovieDto deleteMovie(@RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		Movie movie = movieService.getMovie(itemId);
		movieService.deleteMovie(itemId);
		return convertToDto(movie);
	}
	
	private MovieDto convertToDto(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), convertToDto(movie.getCreator()), movie.getItemId());
		return movieDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
	}
}
