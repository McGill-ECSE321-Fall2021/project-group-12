package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

	Book findBookByItemId(Long itemId);
	
	List<Book> findBookByTitle(String title);
	
}
