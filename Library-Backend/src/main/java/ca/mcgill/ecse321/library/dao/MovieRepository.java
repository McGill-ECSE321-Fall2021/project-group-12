package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{

	Movie findMovieByMovieId(Long movieId);
	
}
