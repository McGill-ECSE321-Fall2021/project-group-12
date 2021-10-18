package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.Librarian;

@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
	
	Librarian findLibrarianByUserId(Long userId);

}
