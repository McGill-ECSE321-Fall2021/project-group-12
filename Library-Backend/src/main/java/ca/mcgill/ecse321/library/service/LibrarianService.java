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
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;

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
		
		@Transactional
		public Librarian createHeadLibrarian(String first, String last, String address, String email, String password, String username) throws IllegalArgumentException {
			nullInputOnlineCheck(first, last, address, username, password, email);
			
			Librarian librarian = new Librarian();
			librarian.setFirstName(first);
			librarian.setLastName(last);
			librarian.setIsHead(true);
			librarian.setIsLocal(true);
			librarian.setAddress(address);
			if (isValidPassword(password)) librarian.setPassword(password);
			isUniqueUsernameAndEmail(username, email);						
			if (isValidEmail(email)) librarian.setEmail(email);
			if (isValidUsername(username)) librarian.setUsername(username);
			librarianRepository.save(librarian);
			return librarian;
		}
//add librarian
		@Transactional
		public Librarian createLibrarian(String librarianUsername, String first, String last, String address, String email, String password, String username) throws IllegalArgumentException {
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(librarianUsername);
			if (headLibrarian.getIsHead()) {
				nullInputOnlineCheck(first, last, address, username, password, email);
				Librarian librarian = new Librarian();
				librarian.setFirstName(first);
				librarian.setLastName(last);
				librarian.setIsHead(false);
				librarian.setIsLocal(true);
				librarian.setAddress(address);
				if (isValidPassword(password)) librarian.setPassword(password);
				isUniqueUsernameAndEmail(username, email);						
				if (isValidEmail(email)) librarian.setEmail(email);
				if (isValidUsername(username)) librarian.setUsername(username);
				librarianRepository.save(librarian);
				return librarian;
			}
			throw new IllegalArgumentException("Only the Head Librarian can create a librarian account.");
		}
		
		@Transactional
		public Librarian updateLibrarian(String oldUsername, String first, String last, String address, String email, String password,
				String username, boolean isHead) throws IllegalArgumentException {
			Librarian librarian = librarianRepository.findLibrarianByUsername(oldUsername);

			if (librarian == null) {
				throw new IllegalArgumentException("Librarian does not exist.");
			}
			nullInputOnlineCheck(first, last, address, username, password, email);
			librarian.setFirstName(first);
			librarian.setLastName(last);
			librarian.setIsHead(isHead);
			librarian.setIsLocal(true);
			librarian.setAddress(address);
			if (isValidPassword(password))
				librarian.setPassword(password);
			isUniqueUsernameAndEmail(username, email);						
			if (isValidEmail(email))
				librarian.setEmail(email);
			if (isValidUsername(username))
				librarian.setUsername(username);
			librarianRepository.save(librarian);
			return librarian;
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
		
		@Transactional
		public List<Librarian> getAllLibrarians() {
			return toList(librarianRepository.findAll());
		}
//remove librarian
		@Transactional
		public Librarian removeLibrarian(String librarianUsername, Long id) throws IllegalArgumentException{
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(librarianUsername);
			if (headLibrarian.getIsHead()) {
				Librarian librarian = librarianRepository.findLibrarianByUserId(id);
				librarianRepository.delete(librarian);
				return headLibrarian;
			}
			throw new IllegalArgumentException("Only the Head Librarian can remove a librarian account.");
		}
//view schedule
		@Transactional
		public List<LibraryHour> getLibraryHourByLibrarianId(Long id) throws IllegalArgumentException{
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			if (librarian == null) throw new IllegalArgumentException("Librarian does not exist.");
			return librarian.getLibraryHours();
		}
//create library hour / schedule
		@Transactional
		public LibraryHour createLibraryHour(String librarianUsername, String username, Time startTime, Time endTime, Day day) throws IllegalArgumentException{
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(librarianUsername);
			if (headLibrarian == null) throw new IllegalArgumentException("Head Librarian username does not exist.");
			if (headLibrarian.getIsHead()) {
				Librarian librarian = librarianRepository.findLibrarianByUsername(username);
				if (librarian == null) throw new IllegalArgumentException("Librarian username does not exist.");
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
			} throw new IllegalArgumentException("Only the Head Librarian can create a schedule.");
		}
//edit library hour / schedule
		@Transactional
		public LibraryHour updateLibraryHour(String librarianUsername, String username, Time startTime, Time endTime, Day day) {
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(librarianUsername);
			if (headLibrarian == null) throw new IllegalArgumentException("Head Librarian username does not exist.");
			if (headLibrarian.getIsHead()) {
				LibraryHour editLibraryHour = new LibraryHour();
				Librarian librarian = librarianRepository.findLibrarianByUsername(username);
				if (librarian == null) throw new IllegalArgumentException("Librarian username does not exist.");
				List<LibraryHour> libraryHours = librarian.getLibraryHours();
				for (LibraryHour lh : libraryHours) {
					if (lh.getDay().equals(day)) {
						libraryHourRepository.delete(lh);
						editLibraryHour.setStartTime(startTime);
						editLibraryHour.setEndTime(endTime);
						editLibraryHour.setDay(day);
						libraryHourRepository.save(editLibraryHour);
						libraryHours.add(editLibraryHour);
						librarian.setLibraryHours(libraryHours);
						librarianRepository.save(librarian);
						return editLibraryHour;
					}
				} throw new IllegalArgumentException("Library hour does not exist.");
			} throw new IllegalArgumentException("Only the Head Librarian can create a schedule.");
		}
//create offline account		
		@Transactional
		public OfflineUser createOfflineUser(String librarianUsername, String first, String last, String address, boolean isLocal) throws IllegalArgumentException{
			isLibrarian(librarianUsername);
			OfflineUser offlineUser = new OfflineUser();
			if (first == null || first.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty first name.");
			}
			if (last == null || last.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty last name.");
			}
			if (address == null || address.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty address.");
			}
			offlineUser.setFirstName(first);
			offlineUser.setLastName(last);
			offlineUser.setAddress(address);
			offlineUser.setIsLocal(isLocal);
			offlineUserRepository.save(offlineUser);
			return offlineUser;
		}
//create online account
		@Transactional
		public OnlineUser createOnlineUser(String first, String last, String address, boolean isLocal, String username, String password, String email) throws IllegalArgumentException{
			OnlineUser onlineUser = new OnlineUser();
			nullInputOnlineCheck(first, last, address, username, password, email);
			onlineUser.setFirstName(first);
			onlineUser.setLastName(last);
			onlineUser.setAddress(address);
			onlineUser.setIsLocal(isLocal);
			if (isValidPassword(password)) onlineUser.setPassword(password);
			isUniqueUsernameAndEmail(username, email);						
			if (isValidUsername(username)) onlineUser.setUsername(username);
			if (isValidEmail(email)) onlineUser.setEmail(email);
			onlineUserRepository.save(onlineUser);
			return onlineUser;
		}
//change password
		@Transactional
		public OnlineUser changePassword(String username, String oldPassword, String newPassword) throws IllegalArgumentException {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUsername(username);
			if (foundOnlineUser == null) throw new IllegalArgumentException("User does not exist.");
			if (oldPassword != foundOnlineUser.getPassword()) {
				throw new IllegalArgumentException("Incorrect password.");
			}
			if (isValidPassword(newPassword)) foundOnlineUser.setPassword(newPassword);
			onlineUserRepository.save(foundOnlineUser);
			return foundOnlineUser;
		}
//edit personal information
		//if null then the information does not change
		@Transactional
		public OfflineUser updateOfflineUserInformation(Long userId, String address, boolean isLocal) throws IllegalArgumentException {
			OfflineUser foundOfflineUser = offlineUserRepository.findOfflineUserByUserId(userId);
			if (foundOfflineUser == null) throw new IllegalArgumentException("User does not exist.");
			if (address != null) foundOfflineUser.setAddress(address);
			foundOfflineUser.setIsLocal(isLocal);
			offlineUserRepository.save(foundOfflineUser);
			return foundOfflineUser;
		}
		@Transactional
		public OnlineUser updateOnlineUserInformation(Long userId, String newUsername, String newEmail, String newAddress, boolean isLocal) throws IllegalArgumentException {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(userId);
			if (foundOnlineUser == null) throw new IllegalArgumentException("User does not exist.");
			isUniqueUsernameAndEmail(newUsername, newEmail);						
			if (newUsername != null) {
				if (isValidUsername(newUsername)) foundOnlineUser.setUsername(newUsername);
			}
			if (newEmail != null) foundOnlineUser.setEmail(newEmail); {
				if (isValidEmail(newEmail)) foundOnlineUser.setEmail(newEmail);
			}
			if (newAddress != null) foundOnlineUser.setAddress(newAddress);
			foundOnlineUser.setIsLocal(isLocal);
			onlineUserRepository.save(foundOnlineUser);
			return foundOnlineUser;
		}
//cancel reservation
		@Transactional
		public boolean removeReservation(Long id) {
			Reservation reservation = reservationRepository.findReservationByReservationId(id);
			reservationRepository.delete(reservation);
			return true;
		}
//view reservation
		@Transactional
		public List<Reservation> getReservationByUserId(Long id) throws IllegalArgumentException {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(id);
			OfflineUser foundOfflineUser = null;
			if (foundOnlineUser == null) {
				foundOfflineUser = offlineUserRepository.findOfflineUserByUserId(id);
			} else {
				return reservationRepository.findByUser(id);
			}
			if (foundOfflineUser == null) {
				throw new IllegalArgumentException("User does not exist.");
			} else {
				return reservationRepository.findByUser(id);
			}
		}
//view times
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
//view bookings
		@Transactional
		public List<Event> getEventsByUser(Long id) throws IllegalArgumentException {
			if (offlineUserRepository.findOfflineUserByUserId(id) == null) {
				if (onlineUserRepository.findOnlineUserByUserId(id) == null) {
					throw new IllegalArgumentException("User does not exist.");
				}
			}
			List<Event> eventsByUser = new ArrayList<>();
			Iterable<Event> allEvents = eventRepository.findAll();
			for (Event e : allEvents) {
				if (e.getUser().getUserId() == id) {
					eventsByUser.add(e);
				}
			}
			return eventsByUser;
		}
//accept / reject bookings
		@Transactional
		public Event acceptEvent(Long id) throws IllegalArgumentException {
			Event event = eventRepository.findEventByEventId(id);
			if (event == null) throw new IllegalArgumentException("Event does not exist.");
			event.setIsAccepted(true);
			eventRepository.save(event);
			return event;
		}
		
		@Transactional
		public Event rejectEvent(Long id) throws IllegalArgumentException {
			Event event = eventRepository.findEventByEventId(id);
			if (event == null) throw new IllegalArgumentException("Event does not exist.");
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

		public void isLibrarian(String username) throws IllegalArgumentException {
			if (librarianRepository.findLibrarianByUsername(username) == null) throw new IllegalArgumentException("Librarian does not exist.");
		}
		
		private void isUniqueUsernameAndEmail(String username, String email) throws IllegalArgumentException {
			Iterable<OnlineUser> allOnlineUser = onlineUserRepository.findAll();
			for (OnlineUser u : allOnlineUser) {
				if (u.getEmail().equals(email)) throw new IllegalArgumentException("Email already in use.");
			}
			for (OnlineUser u : allOnlineUser) {
				if (u.getUsername().equals(username)) throw new IllegalArgumentException("Username already exists.");
			}
		}

		private void nullInputOnlineCheck(String first, String last, String address, String username, String password, String email) {
			if (first == null || first.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty first name.");
			}
			if (last == null || last.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty last name.");
			}
			if (address == null || address.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty address.");
			}
			if (username == null || username.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty username.");
			}
			if (password == null || password.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty password.");
			}
			if (email == null || email.trim().length() == 0) {
				throw new IllegalArgumentException("User cannot have an empty email.");
			}
		}
		private boolean isValidEmail(String email) {
			String[] emailStrings = email.split("@");
			if (!(emailStrings.length == 2 && emailStrings[1].contains("."))) {
				throw new IllegalArgumentException("Provided email is invalid.");
			}
			return true;
		}

		private boolean isValidUsername(String username) throws IllegalArgumentException {
			if (username.contains(" ")) {
				throw new IllegalArgumentException("A username cannot contain spaces.");
			}
			return true;
		}

		// Passwords must contain at least 1 upper case letter and one of the following
		// special characters: \'!\', \'#\', \'$\', \'%\', \'&\', \'*\', \'+\', \'-\',
		// \'=\', \'?\', \'@\', \'^\', \'_\'.
		private boolean isValidPassword(String password) throws IllegalArgumentException {
			if (password.contains(" ")) {
				throw new IllegalArgumentException("A password cannot contain spaces.");
			}
			if (password.length() < 8) {
				throw new IllegalArgumentException("A password must be at least 8 characters.");
			}
			boolean uppercase = false;
			boolean specialChar = false;
			boolean invalid = false;
			for (int i = 0; i < password.length(); i++) {
				if (password.charAt(i) > 64 && password.charAt(i) < 91) {
					uppercase = true;
				} else if (password.charAt(i) == 33 || password.charAt(i) == 35 || password.charAt(i) == 36
						|| password.charAt(i) == 37 || password.charAt(i) == 38 || password.charAt(i) == 42
						|| password.charAt(i) == 43 || password.charAt(i) == 45 || password.charAt(i) == 61
						|| password.charAt(i) == 63 || password.charAt(i) == 64 || password.charAt(i) == 94
						|| password.charAt(i) == 95) {
					// special characters: !, #, $, %, &, *, +, -, =, ?, @, ^, _
					specialChar = true;
				} else if (!(password.charAt(i) > 96 && password.charAt(i) < 123)) {
					invalid = true; // may be invalid if character at i is not uppercase or one of the designated
									// special characters.
				}
			}
			if (invalid) {
				throw new IllegalArgumentException("Password contains illegal characters.");
			}
			if (!uppercase) {
				throw new IllegalArgumentException("Password does not contain an upper case letter.");
			}
			if (!specialChar) {
				throw new IllegalArgumentException("Password does not contain a special character letter.");
			}
			return true;
		}
}