package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
	
	Librarian findLibrarianByUserId(Long userId);
	Librarian findLibrarianByUsername(String username);
}
