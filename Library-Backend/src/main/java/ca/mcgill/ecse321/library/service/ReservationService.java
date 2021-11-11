package ca.mcgill.ecse321.library.service;

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
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

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
		if(timeSlot.getEndDate() == null || timeSlot.getEndTime() == null || timeSlot.getStartDate() == null || timeSlot.getStartTime() == null) {
			throw new IllegalArgumentException("Cannot create reservation with missing info in timeSlot.");
		}
		if(!timeSlot.getEndDate().after(timeSlot.getStartDate())) {
			throw new IllegalArgumentException("Cannot create reservation with endDate before or equal to startDate.");
		}
		List<Long> itemId = new ArrayList<>();
		for(Item i : items) {
			if(i.getIsReservable() == false || i.getIsAvailable() == false || i.getIsArchive() == true) {
				throw new IllegalArgumentException("At least one item selected is not reseverable.");
			}
			for(Long id : itemId) {
				if(id.compareTo(i.getItemId()) == 0) {
					throw new IllegalArgumentException("Cannot create reservation with identical items.");
				}
			}
			itemId.add(i.getItemId());
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
		if(timeSlot.getEndDate() == null || timeSlot.getEndTime() == null || timeSlot.getStartDate() == null || timeSlot.getStartTime() == null) {
			throw new IllegalArgumentException("Cannot update reservation with missing info in timeSlot.");
		}
		if(!timeSlot.getEndDate().after(timeSlot.getStartDate())) {
			throw new IllegalArgumentException("Cannot update reservation with endDate before or equal to startDate.");
		}
		TimeSlot oldTimeSlot = reservation.getTimeSlot();
		if(timeSlot.getStartDate().before(oldTimeSlot.getEndDate())) {
			throw new IllegalArgumentException("Cannot update reservation with newStartDate before oldEndDate.");
		}
		List<Item> preItems = reservation.getItems();
		for(Item i : preItems) {
			if(i instanceof Book) {
				Book book = (Book) i;
				book.removeReservation();
				book.setIsAvailable(true);
				book.setIsReservable(true);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.removeReservation();
				movie.setIsAvailable(true);
				movie.setIsReservable(true);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.removeReservation();
				album.setIsAvailable(true);
				album.setIsReservable(true);
				albumRepository.save(album);
			}
		}
		List<Long> itemId = new ArrayList<>();
		for(Item i : items) {
			if(i.getIsReservable() == false || i.getIsAvailable() == false || i.getIsArchive() == true) {
				for(Item j : preItems) {
					if(j instanceof Book) {
						Book book = (Book) j;
						book.setReservation(reservation);
						book.setIsAvailable(false);
						book.setIsReservable(false);
						bookRepository.save(book);
					} else if(j instanceof Movie) {
						Movie movie = (Movie) j;
						movie.setReservation(reservation);
						movie.setIsAvailable(false);
						movie.setIsReservable(false);
						movieRepository.save(movie);
					} else {
						Album album = (Album) j;
						album.setReservation(reservation);
						album.setIsAvailable(false);
						album.setIsReservable(false);
						albumRepository.save(album);
					}
				}
				throw new IllegalArgumentException("At least one item selected is not reseverable");
			}
		}
		for(Item i : items) {
			for(Long id : itemId) {
				if(id.compareTo(i.getItemId()) == 0) {
					for(Item j : preItems) {
						if(j instanceof Book) {
							Book book = (Book) j;
							book.setReservation(reservation);
							book.setIsAvailable(false);
							book.setIsReservable(false);
							bookRepository.save(book);
						} else if(j instanceof Movie) {
							Movie movie = (Movie) j;
							movie.setReservation(reservation);
							movie.setIsAvailable(false);
							movie.setIsReservable(false);
							movieRepository.save(movie);
						} else {
							Album album = (Album) j;
							album.setReservation(reservation);
							album.setIsAvailable(false);
							album.setIsReservable(false);
							albumRepository.save(album);
						}
					}
					throw new IllegalArgumentException("Cannot update reservation with identical items.");
				}
			}
			itemId.add(i.getItemId());
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
				book.removeReservation();
				book.setIsAvailable(true);
				book.setIsReservable(true);
				bookRepository.save(book);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				movie.removeReservation();
				movie.setIsAvailable(true);
				movie.setIsReservable(true);
				movieRepository.save(movie);
			} else {
				Album album = (Album) i;
				album.removeReservation();
				album.setIsAvailable(true);
				album.setIsReservable(true);
				albumRepository.save(album);
			}
		}
		reservationRepository.delete(reservation);
		reservation.removeReservation();
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
			if(r.getUser().getUserId().compareTo(user.getUserId()) == 0) {
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


