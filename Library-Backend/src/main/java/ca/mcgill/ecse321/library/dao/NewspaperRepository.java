package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Newspaper;

import org.springframework.stereotype.Repository;

@Repository
public interface NewspaperRepository extends CrudRepository<Newspaper, Long>{
	
	Newspaper findByItemId(Long ItemId);
	
	List<Newspaper> findNewspaperByTitle(String title);
}
