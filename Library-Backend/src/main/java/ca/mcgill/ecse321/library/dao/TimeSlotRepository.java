package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.TimeSlot;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long>{
	
	TimeSlot findTimeSlotByTimeSlotId(Long timeSlotId);

}
