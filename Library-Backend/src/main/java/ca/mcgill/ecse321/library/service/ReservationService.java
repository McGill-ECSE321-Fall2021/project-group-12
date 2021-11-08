package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	TimeSlotRepository timeSlotRepository;
	@Autowired
	OnlineUserRepository onlineUserRepository;
	@Autowired
	OfflineUserRepository offlineUserRepository;
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	AlbumRepository albumRepository;
	
	
	
	@Transactional
	public Reservation createReservation(List<Item> items, User user, TimeSlot timeSlot) throws IllegalArgumentException {
		if(items == null || items.isEmpty() == true || user == null || timeSlot == null) {
			throw new IllegalArgumentException("Cannot create reservation with empty arguments.");
		}
		for(Item i : items) {
			if(i.getIsReservable() == false) {
				throw new IllegalArgumentException("At least one item selected is not reseverable");
			}
		}
		Reservation reservation = new Reservation();
		reservation.setItems(items);
		reservation.setUser(user);
		reservation.setTimeSlot(timeSlot);
		timeSlotRepository.save(timeSlot);
		for(Item i : items) {
			i.setReservation(reservation);
			i.setIsAvailable(false);
			i.setIsReservable(false);
			if(bookRepository.findBookByItemId(i.getItemId()) != null) {
				bookRepository.save((Book) i);
			} else if(movieRepository.findMovieByItemId(i.getItemId()) != null) {
				movieRepository.save((Movie) i);
			} else {
				albumRepository.save((Album) i);
			}
		}
		user.addReservation(reservation);
		if(offlineUserRepository.findOfflineUserByUserId(user.getUserId()) != null) {
			offlineUserRepository.save((OfflineUser) user);
		} else if (onlineUserRepository.findOnlineUserByUserId(user.getUserId()) != null) {
			onlineUserRepository.save((OnlineUser) user);
		} else {
			librarianRepository.save((Librarian) user);
		}
		reservationRepository.save(reservation);
		return reservation;	
	}
	
	@Transactional
	public Reservation updateReservation(List<Item> items, Reservation reservation, TimeSlot timeSlot) throws IllegalArgumentException {
		if(items == null || items.isEmpty() == true || reservation == null || timeSlot == null) {
			throw new IllegalArgumentException("Cannot update reservation with empty arguments.");
		}
		for(Item i : items) {
			if(i.getIsReservable() == false) {
				throw new IllegalArgumentException("At least one item selected is not reseverable");
			}
		}
		List<Item> preItems = reservation.getItems();
		for(Item i : preItems) {
			i.setReservation(null);
			i.setIsAvailable(true);
			i.setIsReservable(true);
			if(bookRepository.findBookByItemId(i.getItemId()) != null) {
				bookRepository.save((Book) i);
			} else if(movieRepository.findMovieByItemId(i.getItemId()) != null) {
				movieRepository.save((Movie) i);
			} else {
				albumRepository.save((Album) i);
			}
		}
		timeSlotRepository.delete(reservation.getTimeSlot());
		timeSlotRepository.save(timeSlot);
		reservation.setItems(items);
		reservation.setTimeSlot(timeSlot);
		for(Item i : items) {
			i.setReservation(reservation);
			i.setIsAvailable(false);
			i.setIsReservable(false);
			if(bookRepository.findBookByItemId(i.getItemId()) != null) {
				bookRepository.save((Book) i);
			} else if(movieRepository.findMovieByItemId(i.getItemId()) != null) {
				movieRepository.save((Movie) i);
			} else {
				albumRepository.save((Album) i);
			}
		}
		reservationRepository.save(reservation);
		return reservation;
	}
	
	@Transactional
	public void deleteReservation(Reservation reservation) throws IllegalArgumentException {
		if(reservation == null) {
			throw new IllegalArgumentException("The input is empty.");
		}
		User user = reservation.getUser();
		TimeSlot timeSlot = reservation.getTimeSlot();
		List<Item> items = reservation.getItems();
		user.getReservations().remove(reservation);
		if(offlineUserRepository.findOfflineUserByUserId(user.getUserId()) != null) {
			offlineUserRepository.save((OfflineUser) user);
		} else if(onlineUserRepository.findOnlineUserByUserId(user.getUserId()) != null) {
			onlineUserRepository.save((OnlineUser) user);
		} else {
			librarianRepository.save((Librarian) user);
		}
		timeSlotRepository.delete(timeSlot);
		for(Item i : items) {
			i.setReservation(null);
			i.setIsAvailable(true);
			i.setIsReservable(true);
			if(bookRepository.findBookByItemId(i.getItemId()) != null) {
				bookRepository.save((Book) i);
			} else if(movieRepository.findMovieByItemId(i.getItemId()) != null) {
				movieRepository.save((Movie) i);
			} else {
				albumRepository.save((Album) i);
			}
		}
		reservationRepository.delete(reservation);
	}
	
	
	@Transactional
	public Reservation getReservation(Long reservationId) throws IllegalArgumentException {
		Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
		if(reservation == null) {
			throw new IllegalArgumentException("Reservation does not exist.");
		}
		return reservation;
	}
	
	@Transactional
	public List<Reservation> getReservationsByUser(User user) throws IllegalArgumentException {
		if(user == null) {
			throw new IllegalArgumentException("Input is empty.");
		}
		List<Reservation> result = new ArrayList<>();
		List<Reservation> allReservation = (List<Reservation>) reservationRepository.findAll();
		for(Reservation r: allReservation) {
			if(r.getUser().getUserId() == user.getUserId()) {
				result.add(r);
			}
		}
		return result;	
	}
	
	@Transactional
	public List<Reservation> getAllReservations() {
		return (List<Reservation>) reservationRepository.findAll();
	}

}


