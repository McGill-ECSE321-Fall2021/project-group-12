package ca.mcgill.ecse321.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private CreatorService creatorService;
	
	@GetMapping(value = {"/movies", "/movies/"})
	public List<MovieDto> getAllMovies() {
		return movieService.getAllMovies().stream().map(p -> convertToDto(p)).collect(Collectors.toList());	
	}
	@GetMapping(value = {"/movie/{itemId}", "/movie/{itemId}/"})
	public MovieDto getMovie(@PathVariable("itemId") Long itemId) {
		return convertToDto(movieService.getMovie(itemId));
	}
	@PostMapping(value = {"/movie/create", "/movie/create/"})
	public MovieDto createMovie(@RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		Creator creator = creatorService.getCreator(creatorId);
		Movie movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		return convertToDto(movie);
	}
	@PutMapping(value = {"/movie/update/{itemId}", "/movie/update/{itemId}/"})
	public MovieDto updateMovie(@PathVariable("itemId") Long itemId, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(movieService.updateMovie(itemId, title, isArchive, isReservable, releaseDate, isAvailable, duration, genre, creator));
	}
	
	@DeleteMapping(value = { "/movie/delete/{itemId}", "/movie/delete/{itemId}/"})
	public MovieDto deleteMovie(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		Movie movie = movieService.getMovie(itemId);
		movieService.deleteMovie(itemId);
		return convertToDto(movie);
	}
	
	private MovieDto convertToDto(Movie movie) throws IllegalArgumentException {
		if (movie == null) {
			throw new IllegalArgumentException("Movie does not exist");
		}
		CreatorDto creatorDto = new CreatorDto(movie.getCreator().getFirstName(), movie.getCreator().getLastName(), movie.getCreator().getCreatorType(), movie.getCreator().getCreatorId());
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), creatorDto);
		return movieDto;
	}
}
