package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
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
		for(Item i : items) {
			if(i instanceof Book) {
				Book book = (Book) i;
				book.setReservation(reservation);
				book.setIsAvailable(false);
				book.setIsReservable(false);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.setReservation(reservation);
				movie.setIsAvailable(false);
				movie.setIsReservable(false);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.setReservation(reservation);
				album.setIsAvailable(false);
				album.setIsReservable(false);
				albumRepository.save(album);
			}
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
			if(i instanceof Book) {
				Book book = (Book) i;
				book.setReservation(null);
				book.setIsAvailable(true);
				book.setIsReservable(true);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.setReservation(null);
				movie.setIsAvailable(true);
				movie.setIsReservable(true);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.setReservation(null);
				album.setIsAvailable(true);
				album.setIsReservable(true);
				albumRepository.save(album);
			}
		}
		reservation.setItems(items);
		reservation.setTimeSlot(timeSlot);
		for(Item i : items) {
			if(i instanceof Book) {
				Book book = (Book) i;
				book.setReservation(reservation);
				book.setIsAvailable(false);
				book.setIsReservable(false);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.setReservation(reservation);
				movie.setIsAvailable(false);
				movie.setIsReservable(false);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.setReservation(reservation);
				album.setIsAvailable(false);
				album.setIsReservable(false);
				albumRepository.save(album);
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
		List<Item> items = reservation.getItems();
		for(Item i : items) {
			if(i instanceof Book) {
				Book book = (Book) i;
				book.setReservation(null);
				book.setIsAvailable(true);
				book.setIsReservable(true);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.setReservation(null);
				movie.setIsAvailable(true);
				movie.setIsReservable(true);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.setReservation(null);
				album.setIsAvailable(true);
				album.setIsReservable(true);
				albumRepository.save(album);
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


