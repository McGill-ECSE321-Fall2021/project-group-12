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
				throw new IllegalArgumentException("At least one item selected is not reservable.");
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
	public Reservation updateReservation(Reservation reservation, TimeSlot timeSlot) throws IllegalArgumentException {
		if(reservation == null || timeSlot == null) {
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
		reservation.setTimeSlot(timeSlot);
		return reservation;
	}
	
	@Transactional
	public void deleteReservation(Reservation reservation) throws IllegalArgumentException {
		if(reservation == null) {
			throw new IllegalArgumentException("The input is empty.");
		}
		
		List<Item> items = reservation.getItems();
		Book newBook = new Book();
		Movie newMovie = new Movie();
		Album newAlbum = new Album();
		List<Book> newBooks = new ArrayList<>();
		List<Movie> newMovies = new ArrayList<>();
		List<Album> newAlbums = new ArrayList<>();
		
		for(Item i : items) {
			if(i instanceof Book) {
				Book book = (Book) i;
				newBook.setCreator(book.getCreator());
				newBook.setGenre(book.getGenre());
				newBook.setIsArchive(book.getIsArchive());
				newBook.setIsAvailable(true);
				newBook.setIsReservable(true);
				newBook.setItemId(i.getItemId());
				newBook.setNumPages(book.getNumPages());
				newBook.setReleaseDate(book.getReleaseDate());
				newBook.setTitle(book.getTitle());
				newBooks.add(newBook);
			} else if(i instanceof Movie) {
				Movie movie = (Movie) i;
				newMovie.setCreator(movie.getCreator());
				newMovie.setDuration(movie.getDuration());
				newMovie.setGenre(movie.getGenre());
				newMovie.setIsArchive(movie.getIsArchive());
				newMovie.setIsAvailable(true);
				newMovie.setIsReservable(true);
				newMovie.setItemId(movie.getItemId());
				newMovie.setReleaseDate(movie.getReleaseDate());
				newMovie.setTitle(movie.getTitle());
				newMovies.add(newMovie);
			} else if(i instanceof Album) {
				Album album = (Album) i;
				newAlbum.setCreator(album.getCreator());
				newAlbum.setGenre(album.getGenre());
				newAlbum.setIsArchive(album.getIsArchive());
				newAlbum.setIsAvailable(true);
				newAlbum.setIsReservable(true);
				newAlbum.setItemId(album.getItemId());
				newAlbum.setNumSongs(album.getNumSongs());
				newAlbum.setReleaseDate(album.getReleaseDate());
				newAlbum.setTitle(album.getTitle());
				newAlbums.add(newAlbum);
			} else {
				
			}
		}
		reservationRepository.delete(reservation);
		for(Book b : newBooks) {
			bookRepository.save(b);
		}
		for(Movie m : newMovies) {
			movieRepository.save(m);
		}
		for(Album a : newAlbums) {
			albumRepository.save(a);
		}
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


