package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	Event findEventByEventId(Long eventId);
	
}
