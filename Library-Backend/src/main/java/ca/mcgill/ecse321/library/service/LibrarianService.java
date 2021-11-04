package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;

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
			//so that head librarian has a unique username
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
			Librarian headLibrarian = librarianRepository.findLibrarianByUsername(username);
			if (headLibrarian.getIsHead()) {
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
		public LibraryHour createLibraryHour(Long id, Time startTime, Time endTime, Day day) {
			//make new library hour
			//cannot allow if already existing (not implemented)
			LibraryHour newLibraryHour = new LibraryHour();
			newLibraryHour.setStartTime(startTime);
			newLibraryHour.setEndTime(endTime);
			newLibraryHour.setDay(day);
			libraryHourRepository.save(newLibraryHour);
			//adding libraryhour method missing?
			//needs to add the library hour to the librarian
			return newLibraryHour;
			
		}
		//incomplete -- need to check
		@Transactional
		public LibraryHour editLibraryHour() {
			LibraryHour editLibraryHour = new LibraryHour();
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
		public Reservation createReservation() {
			Reservation reservation = new Reservation();
			//incomplete
			return reservation;
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
		public List<Reservation> getReservationByUsername(String username) throws IllegalArgumentException {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUsername(username);
			OfflineUser foundOfflineUser = null;
			if (foundOnlineUser == null) {
				foundOfflineUser = offlineUserRepository.findOfflineUserByUsername(username);
			} else {
				return reservationRepository.findByUser(foundOnlineUser.getUserId());
			}
			if (foundOfflineUser == null) {
				throw new IllegalArgumentException("User does not esist.");
			} else {
				return reservationRepository.findByUser(foundOfflineUser.getUserId());
			}
		}
		
		private <T> List<T> toList(Iterable<T> iterable) {
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}
}

/*
 * use cases for librarian:
 * !!! how to determine if it's the head librarian that does the action?
 * !!! how to do the log-in services??
 * 
 * - librarian accepting reservation (do we have anything that implements this?)
 * - view person's reservation
 * - librarian adds item
 * - librarian deletes item
 * - librarian updates item
 * 
 * - view times (view existing timeslots taken by events)
 * - edit booking
 * - view bookings by person
 * - accept booking (do we have anything that implements this?)
 * - reject booking (do we have anything that implements this?)
 * 
 * - create offline account (done - need input checks)
 * - create online account (done - needs input checks)
 * - set librarian account (done - needs input checks)
 * - change password (done - need input checks)
 * - log in online account (??)
 * - edit personal information (done)
 * - log in offline account (??)
 * 
 * only head librarian
 * - add librarian (done)
 * - remove librarian (done)
 * - view schedule (done)
 * libraryhour, missing methods in java code? adding libraryhour
 * libraryhour, unidirectional but in wrong direction?
 * - create schedule (incomplete)
 * - edit schedule (incomplete)
 */