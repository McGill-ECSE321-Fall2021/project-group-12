package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;

@Service
public class LibrarianService {

		@Autowired
		LibrarianRepository librarianRepository;
		@Autowired
		OfflineUserRepository offlineUserRepository;
		@Autowired
		OnlineUserRepository onlineUserRepository;
		
		/*
		 * use cases for librarian:
		 * - librarian accepting reservation
		 * - view person's reservation
		 * - librarian adds item
		 * - librarian deletes item
		 * - librarian updates item
		 * 
		 * - view times
		 * - edit booking
		 * - view bookings by person
		 * - accept booking
		 * - reject booking
		 * 
		 * - create offline account (done - need input checks)
		 * - create online account (done - needs input checks)
		 * - set librarian account (hl) (done - needs input checks)
		 * - change password (done - need input checks)
		 * - log in online account (??)
		 * - edit personal information (done)
		 * - log in offline account (??)
		 * 
		 * - add librarian (done)
		 * - remove librarian ()
		 * - view schedule
		 * - create schedule
		 * - edit schedule
		 */
		
		@Transactional
		public Librarian createHeadLibrarian(String first, String last, String address, String email, String password, String username) {
			Librarian librarian = new Librarian();
			librarian.setFirstName(first);
			librarian.setLastName(last);
			librarian.setIsHead(true);
			librarian.setAddress(address);
			librarian.setEmail(email);
			librarian.setPassword(password);
			librarian.setUsername(username);
			librarianRepository.save(librarian);
			return librarian;
		}
		//adding normal librarian, only head librarian can do
		@Transactional
		public Librarian createLibrarian(Librarian headLibrarian, String first, String last, String address, String email, String password, String username) throws IllegalArgumentException {
			if (headLibrarian.getIsHead()) {
				Librarian librarian = new Librarian();
				librarian.setFirstName(first);
				librarian.setLastName(last);
				librarian.setIsHead(false);
				librarian.setAddress(address);
				librarian.setEmail(email);
				librarian.setPassword(password);
				librarian.setUsername(username);
				librarianRepository.save(librarian);
				return librarian;
			}
			throw new IllegalArgumentException("Only Head Librarian can create a librarian account");
		}
		
		@Transactional
		public Librarian getLibrarian(Long id) {
			Librarian librarian = librarianRepository.findLibrarianByUserId(id);
			return librarian;
		}
		
		//if we're changing to use the lastname, first name we'll use this methoc
//		@Transactional
//		public Librarian getLibrarian(String name) {
//			Librariran librarian = librarianRepository.findLibrarianByName(name);
//			return librarian;
//		}
		
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
		public OnlineUser editOnlineUserInformation(OnlineUser onlineUser, String password, String username, String address, String isLocal) {
			OnlineUser foundOnlineUser = onlineUserRepository.findOnlineUserByUserId(onlineUser.getUserId());
			if (password != foundOnlineUser.getPassword()) {
				throw new IllegalArgumentException("Incorrect password, unable to make requested changes");
			}
			if (username != null) foundOnlineUser.setUsername(username);
			if (address != null) foundOnlineUser.setAddress(address);
			if (isLocal != null) {
				boolean isLocalInput = Boolean.valueOf(isLocal);
				foundOnlineUser.setIsLocal(isLocalInput);
			}
			return onlineUser;
		}
		
		@Transactional
		public OfflineUser editOfflineUserInformation(OfflineUser offlineUser, String address, String isLocal) {
			OfflineUser foundOfflineUser = offlineUserRepository.findOfflineUserByUserId(offlineUser.getUserId());
			if (address != null) foundOfflineUser.setAddress(address);
			if (isLocal != null) {
				boolean isLocalInput = Boolean.valueOf(isLocal);
				foundOfflineUser.setIsLocal(isLocalInput);
			}
			return offlineUser;
		}
		
		private <T> List<T> toList(Iterable<T> iterable) {
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
		}
}