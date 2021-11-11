package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, User>{
	
	Reservation findReservationByReservationId(Long reservationId);
	List<Reservation> findByUser(User user);
	
}
