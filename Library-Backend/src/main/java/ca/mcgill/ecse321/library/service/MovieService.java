package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Transactional
	public Movie createMovie(String title, boolean isArchive, boolean isReservable, Date releaseDate, boolean isAvailable, int duration, BMGenre genre) throws IllegalArgumentException {
		Movie newMovie = new Movie();
		if (title == null || title.trim().length() == 0) {
			throw new IllegalArgumentException("A movie cannot have an empty title.");
		}
		if (releaseDate == null) {
			throw new IllegalArgumentException("A movie cannot have an empty release date.");
		}
		if (duration == 0) {
			throw new IllegalArgumentException("A movie cannot have a duration of zero.");
		}
		newMovie.setTitle(title);
		newMovie.setIsArchive(isArchive);
		newMovie.setIsReservable(isReservable);
		newMovie.setReleaseDate(releaseDate);
		newMovie.setIsAvailable(isAvailable);
		newMovie.setDuration(duration);
		newMovie.setGenre(genre);
		movieRepository.save(newMovie);
		return newMovie;
	}
	@Transactional 
	public Movie updateMovie(Long id, String newTitle, boolean newIsArchive, boolean newIsReservable, Date newReleaseDate, boolean newIsAvailable, int newDuration, BMGenre newGenre) throws IllegalArgumentException {
		Movie movie = movieRepository.findMovieByItemId(id);
		//if the title, release date, or duration are null then don't change, remains the same
		//if the duration is zero
		if (newTitle != null) movie.setTitle(newTitle);
		if (newReleaseDate != null) movie.setReleaseDate(newReleaseDate);
		if (newDuration != 0) {
			movie.setDuration(newDuration);
		} else {
			//if the duration is zero
			throw new IllegalArgumentException("Invalid Input: The duration of a movie cannot be zero.");
		}
		if (newGenre != null) movie.setGenre(newGenre);
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
	public boolean deleteMovie(Long id) {
		Movie movie = movieRepository.findMovieByItemId(id);
		if (movie == null) {
			throw new IllegalArgumentException("Movie does not exist.");
		}
		movieRepository.delete(movie);
		return true;
	}
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
