package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Transactional
	public Movie createMovie(String title, boolean isArchive, boolean isReservable, boolean isAvailable, Date releaseDate, int duration, BMGenre genre, Creator creator) throws IllegalArgumentException {
		Movie newMovie = new Movie();
		if (title == null || title.trim().length() == 0) {
			throw new IllegalArgumentException("Cannot create movie with empty title.");
		}
		if (releaseDate == null) {
			throw new IllegalArgumentException("Cannot create movie with empty date.");
		}
		if (creator == null) {
			throw new IllegalArgumentException("A movie cannot have an empty creator.");
		}
		if (duration <= 0) {
			throw new IllegalArgumentException("A movie cannot have a duration less or equal to zero.");
		}
		newMovie.setTitle(title);
		newMovie.setIsArchive(isArchive);
		newMovie.setIsReservable(isReservable);
		newMovie.setReleaseDate(releaseDate);
		newMovie.setIsAvailable(isAvailable);
		newMovie.setDuration(duration);
		newMovie.setGenre(genre);
		newMovie.setCreator(creator);
		movieRepository.save(newMovie);
		return newMovie;
	}
	@Transactional 
	public Movie updateMovie(Long id, boolean newIsArchive, boolean newIsReservable, boolean newIsAvailable) throws IllegalArgumentException {
		Movie movie = movieRepository.findMovieByItemId(id);
		//if the title, release date, or duration are null then don't change, remains the same
		//if the duration is zero
		movie.setIsArchive(newIsArchive);
		movie.setIsAvailable(newIsAvailable);
		movie.setIsReservable(newIsReservable);
		movieRepository.save(movie);
		return movie;
	}
	@Transactional
	public List<Movie> getAllMovies() {
		return toList(movieRepository.findAll());
	}
	@Transactional
	public Movie getMovie(Long id) throws IllegalArgumentException {
		Movie movie = movieRepository.findMovieByItemId(id);
		if (movie == null) {
			throw new IllegalArgumentException("Movie does not exist.");
		}
		return movie;
	}
	@Transactional
	public Movie deleteMovie(Long id) {
		Movie movie = movieRepository.findMovieByItemId(id);
		if (movie == null) {
			throw new IllegalArgumentException("Book does not exist.");
		}
		movieRepository.delete(movie);
		return movie;
	}
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
