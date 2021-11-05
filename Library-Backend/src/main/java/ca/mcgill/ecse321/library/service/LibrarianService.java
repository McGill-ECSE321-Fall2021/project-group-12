package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.Movie.BMGenre;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.model.Book;

@Service
public class LibrarianService {

		@Autowired
		LibrarianRepository librarianRepository;
		@Autowired
		OfflineUserRepository offlineUserRepository;
		@Autowired
		OnlineUserRepository onlineUserRepository;
		@Autowired
		LibraryHourRepository libraryHourRepository;
		@Autowired
		ReservationRepository reservationRepository;
		@Autowired
		EventRepository eventRepository;
		
		@Autowired
		AlbumService albumService;
		@Autowired
		BookService bookService;
		@Autowired
		MovieService movieService;
		@Autowired
		NewspaperService newspaperService;
		@Autowired
		EventService eventService;
		
		
		@Transactional
		public Librarian createHeadLibrarian(String first, String last, String address, String email, String password, String username) {
			if (first == null || first.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty first name.");
			}
			if (last == null || last.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty last name.");
			}
			if (address == null || address.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty address.");
			}
			if (username == null || username.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty username.");
			}
			if (password == null || password.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty password.");
			}
			if (email == null || email.trim().length() == 0) {
				throw new IllegalArgumentException("Librarian cannot have an empty email.");
			}
			
			Librarian librarian = new Librarian();
			librarian.setFirstName(first);
			librarian.setLastName(last);
			librarian.setIsHead(true);
			librarian.setAddress(address);
			librarian.setEmail(email);
			librarian.setPassword(password);
			//head librarian has a unique username
			Iterable<OnlineUser> allOnlineUser = onlineUserRepository.findAll();
			for (OnlineUser u : allOnlineUser) {
				if (u.getUsername().equals(username)) throw new IllegalArgumentException("Username already taken.");
			}
			librarian.setUsername(username);
			librarianRepository.save(librarian);
			return librarian;
		}

		@Transactional
		public Librarian createLibrarian(String librarianUsename, String first, String last, String address, String email, String password, String username) throws IllegalArgumentException {
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(username);
			if (headLibrarian.getIsHead()) {
				if (first == null || first.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty first name.");
				}
				if (last == null || last.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty last name.");
				}
				if (address == null || address.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty address.");
				}
				if (username == null || username.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty username.");
				}
				if (password == null || password.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty password.");
				}
				if (email == null || email.trim().length() == 0) {
					throw new IllegalArgumentException("Librarian cannot have an empty email.");
				}
				Librarian librarian = new Librarian();
				librarian.setFirstName(first);
				librarian.setLastName(last);
				librarian.setIsHead(false);
				librarian.setAddress(address);
				librarian.setEmail(email);
				librarian.setPassword(password);
				Iterable<OnlineUser> allOnlineUser = onlineUserRepository.findAll();
				for (OnlineUser u : allOnlineUser) {
					if (u.getUsername().equals(username)) throw new IllegalArgumentException("Username already taken.");
				}
				librarian.setUsername(username);
				librarianRepository.save(librarian);
				return librarian;
			}
			throw new IllegalArgumentException("Only the Head Librarian can create a librarian account.");
		}
		
		@Transactional
		public Librarian getLibrarian(Long id) {
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			return librarian;
		}
		
		@Transactional
		public Librarian getLibrarian(String username) {
			Librarian librarian = librarianRepository.findLibrarianByUsername(username);
			return librarian;
		}
		
		@Transactional
		public Librarian getHeadLibrarian() {
			Iterable<Librarian> allLibrarian = librarianRepository.findAll();
			for (Librarian l : allLibrarian) {
				if (l.getIsHead()) return l;
			}
			return null;
		}
		
		//if any librarian can access the list of all librarians
		@Transactional
		public List<Librarian> getAllLibrarians() {
			return toList(librarianRepository.findAll());
		}
		
		@Transactional
		public boolean removeLibrarian(Librarian headLibrarian, Long id) throws IllegalArgumentException{
			if (headLibrarian.getIsHead()) {
				Librarian librarian = librarianRepository.findLibrarianByUserId(id);
				librarian.delete();
				return true;
			}
			throw new IllegalArgumentException("Only the Head Librarian can remove a librarian account.");
		}
		
		@Transactional
		public List<LibraryHour> getLibraryHour(Long id) {
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			return librarian.getLibraryHours();
		}
		
		@Transactional
		public LibraryHour createLibraryHour(String username, Long id, Time startTime, Time endTime, Day day) {
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			List<LibraryHour> libraryHours = librarian.getLibraryHours();
			for (LibraryHour lh : libraryHours) {
				if (lh.getDay().equals(day)) throw new IllegalArgumentException("Library hour for selected day already created.");
			}
			LibraryHour newLibraryHour = new LibraryHour();
			newLibraryHour.setStartTime(startTime);
			newLibraryHour.setEndTime(endTime);
			newLibraryHour.setDay(day);
			libraryHourRepository.save(newLibraryHour);
			libraryHours.add(newLibraryHour);
			librarian.setLibraryHours(libraryHours);
			librarianRepository.save(librarian);
			return newLibraryHour;
			
		}

		@Transactional
		public LibraryHour editLibraryHour(String username, Long id, Time startTime, Time endTime, Day day) {
			LibraryHour editLibraryHour = new LibraryHour();
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			List<LibraryHour> libraryHours = librarian.getLibraryHours();
			for (LibraryHour lh : libraryHours) {
				if (lh.getDay().equals(day)) {
					lh.delete();
					editLibraryHour.setStartTime(startTime);
					editLibraryHour.setEndTime(endTime);
					editLibraryHour.setDay(day);
					libraryHourRepository.save(editLibraryHour);
					libraryHours.add(editLibraryHour);
					librarian.setLibraryHours(libraryHours);
					librarianRepository.save(librarian);
				}
			}
			return editLibraryHour;
		}
		
		@Transactional
		public OfflineUser createOfflineUser(String first, String last, String address, boolean isLocal) {
			OfflineUser offlineUser = new OfflineUser();
			offlineUser.setFirstName(first);
			offlineUser.setLastName(last);
			offlineUser.setAddress(address);
			offlineUser.setIsLocal(isLocal);
			offlineUserRepository.save(offlineUser);
			return offlineUser;
		}
		
		@Transactional
		public OnlineUser createOnlineUser(String first, String last, String address, boolean isLocal, String username, String password) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setFirstName(first);
			onlineUser.setLastName(last);
			onlineUser.setAddress(address);
			onlineUser.setIsLocal(isLocal);
			onlineUser.setUsername(username);
			onlineUser.setPassword(password);
			onlineUserRepository.save(onlineUser);
			return onlineUser;
		}
		
		@Transactional
		public OnlineUser changePassword(OnlineUser onlineUser, String oldPassword, String newPassword) {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(onlineUser.getUserId());
			if (oldPassword != foundOnlineUser.getPassword()) {
				throw new IllegalArgumentException("Incorrect password");
			}
			foundOnlineUser.setPassword(newPassword);
			return foundOnlineUser;
		}
		
		//if null then the information does not change
		@Transactional
		public OnlineUser editOnlineUserInformation(OnlineUser onlineUser, String password, String username, String address, boolean isLocal) {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(onlineUser.getUserId());
			if (password != foundOnlineUser.getPassword()) {
				throw new IllegalArgumentException("Incorrect password, unable to make requested changes");
			}
			if (username != null) foundOnlineUser.setUsername(username);
			if (address != null) foundOnlineUser.setAddress(address);
			foundOnlineUser.setIsLocal(isLocal);
			return onlineUser;
		}
		
		@Transactional
		public OfflineUser editOfflineUserInformation(OfflineUser offlineUser, String address, boolean isLocal) {
			OfflineUser foundOfflineUser = offlineUserRepository.findOfflineUserByUserId(offlineUser.getUserId());
			if (address != null) foundOfflineUser.setAddress(address);
			foundOfflineUser.setIsLocal(isLocal);
			return offlineUser;
		}
		
		@Transactional
		public Album createAlbum(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numSongs, boolean available, MusicGenre genre, Creator creator) {
			return albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, available, genre, creator);
		}
		
		@Transactional
		public Album updateAlbum(Long itemId, boolean isArchive, boolean isReservable, boolean available) {
			return albumService.updateAlbum(itemId, isArchive, isReservable, available);
		}
		
		@Transactional
		public Album deleteAlbum(Long albumId) {
			return albumService.deleteAlbum(albumId);
		}
		
		@Transactional
		public Book createBook(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numPages, boolean available, Book.BMGenre genre, Creator creator) {
			return bookService.createBook(title, isArchive, isReservable, releaseDate, numPages, available, genre, creator);
		}
		
		@Transactional
		public Book updateBook(Long itemId, boolean isArchive, boolean isReservable, boolean available) {
			return bookService.updateBook(itemId, isArchive, isReservable, available);
		}
		
		@Transactional
		public Book deleteBook(Long bookId) {
			return bookService.deleteBook(bookId);
		}
		
		@Transactional
		public Movie createMovie(String title, boolean isArchive, boolean isReservable, boolean isAvailable, Date releaseDate, int duration, Movie.BMGenre genre, Creator creator) {
			return movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		}
		
		@Transactional 
		public Movie updateMovie(Long id, String newTitle, boolean newIsArchive, boolean newIsReservable, Date newReleaseDate, boolean newIsAvailable, int newDuration, BMGenre newGenre, Creator newCreator) {
			return movieService.updateMovie(id, newTitle, newIsArchive, newIsReservable, newReleaseDate, newIsAvailable, newDuration, newGenre, newCreator);
		}
		
		@Transactional
		public boolean deleteMovie(Long movieId) {
			return movieService.deleteMovie(movieId);
		}
		
		@Transactional
		public Newspaper createNewspaper(String title, boolean isArchive, Date releaseDate, Creator creator) {
			return newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}
		
		@Transactional
		public Newspaper updateNewspaper(Long itemId, String title, boolean isArchive, Date releaseDate, Creator creator) {
			return newspaperService.updateNewspaper(itemId, title, isArchive, releaseDate, creator);
		}
		
		@Transactional
		public Newspaper deleteNewspaper(Long itemId) {
			return newspaperService.deleteNewspaper(itemId);
		}

		@Transactional
		public boolean removeReservation(Long id) {
			Reservation reservation = reservationRepository.findReservationByReservationId(id);
			reservation.delete();
			return true;
		}
		
		@Transactional
		public List<Reservation> getReservationByUserId(Long id) throws IllegalArgumentException {
			//will edit to remove redundant code
			//might need to check if the code is correct - correct direction
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(id);
			OfflineUser foundOfflineUser = null;
			if (foundOnlineUser == null) {
				foundOfflineUser = offlineUserRepository.findOfflineUserByUserId(id);
			} else {
				return reservationRepository.findByUser(id);
			}
			if (foundOfflineUser == null) {
				throw new IllegalArgumentException("User does not esist.");
			} else {
				return reservationRepository.findByUser(id);
			}
		}
		
		@Transactional
		public List<Reservation> getReservationByFirstNameAndLastName(String firstname, String lastname) throws IllegalArgumentException {
			OfflineUser foundOfflineUser = null;
			OnlineUser foundOnlineUser = null;
			foundOfflineUser = offlineUserRepository.findOfflineUserByFirstNameAndLastName(firstname, lastname);
			if (foundOfflineUser == null) {
				foundOnlineUser = onlineUserRepository.findOnlineUserByFirstNameAndLastName(firstname, lastname);
				if (foundOnlineUser == null) {
					throw new IllegalArgumentException("User does not esist.");
				}
				return reservationRepository.findByUser(foundOnlineUser.getUserId());
			} else {
				return reservationRepository.findByUser(foundOfflineUser.getUserId());
			}
		}
		//do we want to sort?
		@Transactional
		public List<TimeSlot> getTimeSlotsWithEvent() {
			List<TimeSlot> timeSlotsWithEvents = new ArrayList<>();
			Iterable<Event> allEvents = eventRepository.findAll();
			for (Event e : allEvents) {
				if (e.getIsAccepted()) {
					timeSlotsWithEvents.add(e.getTimeSlot());
				}
			}
			return timeSlotsWithEvents;
		}
		
		@Transactional
		public List<Event> getAllEvents() {
			return toList(eventRepository.findAll());
		}
		
		@Transactional
		public List<Event> getEventsByUser(Long id) {
			List<Event> eventsByUser = new ArrayList<>();
			Iterable<Event> allEvents = eventRepository.findAll();
			for (Event e : allEvents) {
				if (e.getUser().equals(onlineUserRepository.findOnlineUserByUserId(id))) {
					eventsByUser.add(e);
				} else if (e.getUser().equals(offlineUserRepository.findOfflineUserByUserId(id))) {
					eventsByUser.add(e);
				}
			}
			return eventsByUser;
		}
		
		@Transactional
		public Event updateEvent(Long id, String name, TimeSlot timeSlot, Boolean isPrivate,Boolean isAccepted, User user) {
			return eventService.updateEvent(id, name, timeSlot, isPrivate, isAccepted, user);
		}
		
		@Transactional
		public Event acceptEvent(Long id) {
			Event event = eventRepository.findEventByEventId(id);
			event.setIsAccepted(true);
			eventRepository.save(event);
			return event;
		}
		
		@Transactional
		public Event rejectEvent(Long id) {
			Event event = eventRepository.findEventByEventId(id);
			event.setIsAccepted(false);
			eventRepository.save(event);
			return event;
		}
		
		private <T> List<T> toList(Iterable<T> iterable) {
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}
}