package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.LibraryHour;

@Repository
public interface LibraryHourRepository extends CrudRepository<LibraryHour, Long> {
	LibraryHour findLibraryHourByLibraryHourId(Long libraryHourId);
}
