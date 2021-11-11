package ca.mcgill.ecse321.library.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.OfflineUserDto;
import ca.mcgill.ecse321.library.dto.OnlineUserDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
public class ReservationRestController {
	
	@Autowired
	private ReservationService service;
	@Autowired
	OnlineUserRepository onlineUserRepository;
	@Autowired
	OfflineUserRepository offlineUserRepository;
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	TimeSlotRepository timeSlotRepository;
	@Autowired
	AlbumRepository albumRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping(value = { "/reservations", "/reservations/" })
	public List<ReservationDto> getAllReservations() {
		List<ReservationDto> reservations = new ArrayList<>();
		for(Reservation reservation: service.getAllReservations()) {
			reservations.add(convertToDto(reservation));
		}
		return reservations;
	}
	
	@GetMapping(value = { "/reservations/{userId}", "/reservations/{userId}/" })
	public List<ReservationDto> getReservationsByUserId(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		List<ReservationDto> userReservations = new ArrayList<>();
		User user;
		if(offlineUserRepository.findOfflineUserByUserId(userId) != null) {
			user = offlineUserRepository.findOfflineUserByUserId(userId);
		} else if(librarianRepository.findLibrarianByUserId(userId) != null) {
			user = librarianRepository.findLibrarianByUserId(userId);
		} else {
			user = onlineUserRepository.findOnlineUserByUserId(userId);
		}
		for(Reservation reservation: service.getReservationsByUser(user)) {
			userReservations.add(convertToDto(reservation));
		}
		return userReservations;
	}
	
	@GetMapping(value = { "/reservation/{reservationId}", "/reservation/{reservationId}/" })
	public ReservationDto getReservationById(@PathVariable("reservationId") Long reservationId) throws IllegalArgumentException {
		ReservationDto reservation = convertToDto(service.getReservation(reservationId));
		return reservation;
	}
	
	@PostMapping(value = { "/reservation/create/{userId}", "/reservation/create/{userId}/" })
	public ReservationDto createReservation(@PathVariable("userId") Long userId,
	@RequestParam(name = "timeSlotId") Long timeSlotId,
	@RequestParam(name = "itemId1") Long itemId1,
	@RequestParam(name = "itemId2") Long itemId2,
	@RequestParam(name = "itemId3") Long itemId3,
	@RequestParam(name = "itemId4") Long itemId4,
	@RequestParam(name = "itemId5") Long itemId5)
	throws IllegalArgumentException {
		User user;
		if(offlineUserRepository.findOfflineUserByUserId(userId) != null) {
			user = offlineUserRepository.findOfflineUserByUserId(userId);
		} else if(librarianRepository.findLibrarianByUserId(userId) != null) {
			user = librarianRepository.findLibrarianByUserId(userId);
		} else {
			user = onlineUserRepository.findOnlineUserByUserId(userId);
		}
		List<Long> itemsId = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		
		itemsId.add(itemId5);
		itemsId.add(itemId4);
		itemsId.add(itemId3);
		itemsId.add(itemId2);
		itemsId.add(itemId1);
		
		for(Long i: itemsId) {
			if(albumRepository.findAlbumByItemId(i) != null) {
				items.add(albumRepository.findAlbumByItemId(i));
			} else if(bookRepository.findBookByItemId(i) != null) {
				items.add(bookRepository.findBookByItemId(i));
			} else if(movieRepository.findMovieByItemId(i) != null) {
				items.add(movieRepository.findMovieByItemId(i));
			} else {
				
			}
		}
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		Reservation reservation = service.createReservation(items, user, timeSlot);
		ReservationDto reservationDto = convertToDto(reservation);
		return reservationDto;
	}
	
	@PutMapping(value = { "/reservation/update/{reservationId}", "/reservation/update/{reservationId}/" })
	public ReservationDto updateReservation(@PathVariable("reservationId") Long reservationId,
	@RequestParam(name = "timeSlotId") Long timeSlotId,
	@RequestParam(name = "itemId1") Long itemId1,
	@RequestParam(name = "itemId2") Long itemId2,
	@RequestParam(name = "itemId3") Long itemId3,
	@RequestParam(name = "itemId4") Long itemId4,
	@RequestParam(name = "itemId5") Long itemId5)
	throws IllegalArgumentException {
		List<Long> itemsId = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		
		itemsId.add(itemId5);
		itemsId.add(itemId4);
		itemsId.add(itemId3);
		itemsId.add(itemId2);
		itemsId.add(itemId1);
		
		for(Long i: itemsId) {
			if(albumRepository.findAlbumByItemId(i) != null) {
				items.add(albumRepository.findAlbumByItemId(i));
			} else if(bookRepository.findBookByItemId(i) != null) {
				items.add(bookRepository.findBookByItemId(i));
			} else if(movieRepository.findMovieByItemId(i) != null) {
				items.add(movieRepository.findMovieByItemId(i));
			} else {
				
			}
		}
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		Reservation reservation = service.getReservation(reservationId);
		Reservation updatedReservation = service.updateReservation(items, reservation, timeSlot);
		ReservationDto updatedReservationDto = convertToDto(updatedReservation);
		return updatedReservationDto;
	}
	
	@DeleteMapping(value = { "/reservation/delete/{reservationId}", "/reservation/delete/{reservationId}/" })
	public ReservationDto deleteReservation(@PathVariable("reservationId") Long reservationId) throws IllegalArgumentException {
		Reservation reservation = service.getReservation(reservationId);
		service.deleteReservation(reservation);
		ReservationDto reservationDto = convertToDto(reservation);
		return reservationDto;
	}
	
	private ReservationDto convertToDto(Reservation reservation) throws IllegalArgumentException {
		if(reservation == null) {
			throw new IllegalArgumentException("Input reservation is null.");
		}
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setReservationId(reservation.getReservationId());
		reservationDto.setTimeSlot(convertToDto(reservation.getTimeSlot()));
		reservationDto.setItems(convertToDto(reservation.getItems()));
		User user = reservation.getUser();
		if(offlineUserRepository.findOfflineUserByUserId(user.getUserId()) != null) {
			reservationDto.setUserDto(convertToOfflineUserDto(user));
		} else {
			if(librarianRepository.findLibrarianByUserId(user.getUserId()) != null) {
				reservationDto.setUserDto(convertToLibrarianDto(user));
			} else {
				reservationDto.setUserDto(convertToOnlineUserDto(user));
			}
		}
		return reservationDto;
	}
	
	private ItemDto convertToDto(Item item) throws IllegalArgumentException {
		if(item == null) {
			throw new IllegalArgumentException("Input user is null.");
		}
		ItemDto itemDto;
		if(albumRepository.findAlbumByItemId(item.getItemId()) != null) {
			Album album = albumRepository.findAlbumByItemId(item.getItemId());
			Creator creator = album.getCreator();
			MusicGenre genre = album.getGenre();
			Boolean isArchive = album.getIsArchive();
			Boolean isAvailable = album.getIsAvailable();
			Boolean isReservable = album.getIsReservable();
			Long itemId = album.getItemId();
			int numSongs = album.getNumSongs();
			Date date = album.getReleaseDate();
			String title = album.getTitle();
			AlbumDto albumDto = new AlbumDto(title, isArchive, isReservable, date, numSongs, isAvailable, genre, convertToDto(creator), itemId);
			itemDto = albumDto;
		} else if(bookRepository.findBookByItemId(item.getItemId()) != null) {
			Book book = bookRepository.findBookByItemId(item.getItemId());
			BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()), book.getItemId());
			itemDto = bookDto;
		} else {
			Movie movie = movieRepository.findMovieByItemId(item.getItemId());
			MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), convertToDto(movie.getCreator()), movie.getItemId());
			itemDto = movieDto;
		}
		return itemDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
	}
	
	private OnlineUserDto convertToOnlineUserDto(User user) throws IllegalArgumentException {
		if(user == null) {
			throw new IllegalArgumentException("Input user is null.");
		}
		OnlineUserDto onUser = new OnlineUserDto();
		OnlineUser thisUser = onlineUserRepository.findOnlineUserByUserId(user.getUserId());
		onUser.setAddress(thisUser.getAddress());
		onUser.setEmail(thisUser.getEmail());
		onUser.setFirstName(thisUser.getFirstName());
		onUser.setLastName(thisUser.getLastName());
		onUser.setIsLocal(thisUser.getIsLocal());
		onUser.setPassword(thisUser.getPassword());
		onUser.setUserId(thisUser.getUserId());
		onUser.setUsername(thisUser.getUsername());
		
		return onUser;
	}

	private LibrarianDto convertToLibrarianDto(User user) throws IllegalArgumentException {
		if(user == null) {
			throw new IllegalArgumentException("Input user is null.");
		}
		LibrarianDto librarianDto = new LibrarianDto();
		Librarian thisUser = librarianRepository.findLibrarianByUserId(user.getUserId());
		librarianDto.setAddress(thisUser.getAddress());
		librarianDto.setEmail(thisUser.getEmail());
		librarianDto.setFirstName(thisUser.getFirstName());
		librarianDto.setLastName(thisUser.getLastName());
		librarianDto.setIsHead(thisUser.getIsHead());
		librarianDto.setIsLocal(thisUser.getIsLocal());
		librarianDto.setPassword(thisUser.getPassword());
		librarianDto.setUserId(thisUser.getUserId());
		librarianDto.setUsername(thisUser.getUsername());
		return librarianDto;
	}

	private OfflineUserDto convertToOfflineUserDto(User user) throws IllegalArgumentException {
		if(user == null) {
			throw new IllegalArgumentException("Input user is null.");
		}
		OfflineUserDto offUser = new OfflineUserDto();
		OfflineUser thisUser = offlineUserRepository.findOfflineUserByUserId(user.getUserId());
		offUser.setAddress(thisUser.getAddress());
		offUser.setFirstName(thisUser.getFirstName());
		offUser.setLastName(thisUser.getLastName());
		offUser.setIsLocal(thisUser.getIsLocal());
		offUser.setUserId(thisUser.getUserId());
		return offUser;
	}

	private List<ItemDto> convertToDto(List<Item> items) throws IllegalArgumentException {
		if(items == null) {
			throw new IllegalArgumentException("Input items is null.");
		}
		List<ItemDto> itemsDto = new ArrayList<>();
		for(Item item: items) {
			itemsDto.add(convertToDto(item));
		}
		return itemsDto;
	}

	private TimeSlotDto convertToDto(TimeSlot timeSlot) throws IllegalArgumentException {
		if(timeSlot == null) {
			throw new IllegalArgumentException("Input timeSlot is null.");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		timeSlotDto.setStartTime(timeSlot.getStartTime());
		timeSlotDto.setEndTime(timeSlot.getEndTime());
		timeSlotDto.setStartDate(timeSlot.getStartDate());
		timeSlotDto.setEndDate(timeSlot.getEndDate());
		timeSlotDto.setTimeSlotId(timeSlot.getTimeSlotId());
		return timeSlotDto;
	}
	
}
