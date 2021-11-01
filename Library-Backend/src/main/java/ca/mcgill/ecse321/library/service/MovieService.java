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
	public Movie createMovie(String title, boolean isArchive, boolean isReservable, Date releaseDate, boolean isAvailable, int duration, BMGenre genre) {
		Movie newMovie = new Movie();
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
	
	//edit a movie with with id, 
	
	@Transactional
	public List<Movie> getAllMovies() {
		return toList(movieRepository.findAll());
	}
	
	@Transactional
	public boolean deleteMovie(Long id) {
		movieRepository.findById(id);
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
