package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.HeadLibrarian;

@Repository
public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Long> {
	HeadLibrarian findHeadLibrarianByUserId(Long userId);
}
