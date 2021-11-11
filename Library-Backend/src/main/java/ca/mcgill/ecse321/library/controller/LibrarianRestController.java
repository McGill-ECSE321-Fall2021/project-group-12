package ca.mcgill.ecse321.library.controller;


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
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.EventDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.LibraryHourDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.dto.OfflineUserDto;
import ca.mcgill.ecse321.library.dto.OnlineUserDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.dto.UserDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.service.AlbumService;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.CreatorService;
import ca.mcgill.ecse321.library.service.EventService;
import ca.mcgill.ecse321.library.service.MovieService;
import ca.mcgill.ecse321.library.service.NewspaperService;
import ca.mcgill.ecse321.library.service.ReservationService;
import ca.mcgill.ecse321.library.service.LibrarianService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = ",")
@RestController
public class LibrarianRestController {
	
	@Autowired
	private LibrarianService librarianService;
	@Autowired
	private CreatorService creatorService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private BookService bookService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private NewspaperService newspaperService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private EventService eventService;
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
	
	//done
	@PostMapping(value = {"/librarian/create/head", "/librarian/create/head/"})
	public LibrarianDto createHeadLibrarian(@RequestParam(value="firstname") String first, @RequestParam(value="lastname") String last, @RequestParam(value="address") String address, @RequestParam(value="email") String email, @RequestParam(value="password") String password, @RequestParam(value="username") String username) {
		return convertToDto(librarianService.createHeadLibrarian(first, last, address, email, password, username));
	}
	//done
	@PostMapping(value = {"/librarian/create", "/librarian/create/"})
	public LibrarianDto createLibrarian(@RequestParam(value="librarianUsername") String librarianUsername, @RequestParam(value="firstname") String first, @RequestParam(value="lastname") String last, @RequestParam(value="address") String address, @RequestParam(value="email") String email, @RequestParam(value="password") String password, @RequestParam(value="username") String username) {
		return convertToDto(librarianService.createLibrarian(librarianUsername, first, last, address, email, password, username));
	}
	//done
	@PutMapping(value = {"/librarian/update/{oldUsername}", "/librarian/update/{oldUsername}"})
	public LibrarianDto updateLibrarian(@PathVariable("oldUsername") String oldUsername, @RequestParam(value="firstname") String first, @RequestParam(value="lastname") String last, @RequestParam(value="address") String address, @RequestParam(value="email") String email, @RequestParam(value="password") String password, @RequestParam(value="username") String username, @RequestParam(value="isHead") boolean isHead) {
		return convertToDto(librarianService.updateLibrarian(oldUsername, first, last, address, email, password, username, isHead));
	}
	
	@GetMapping(value = {"/librarian", "/librarian/"})
	public LibrarianDto getLibrarianById(@RequestParam(value="id") Long id) {
		return convertToDto(librarianService.getLibrarian(id));
	}
	@GetMapping(value = {"/librarian/{username}", "/librarian/{username}/"})
	public LibrarianDto getLibrarianByUsername(@PathVariable("username") String username) {
		return convertToDto(librarianService.getLibrarian(username));
	}
	//done
	@GetMapping(value = {"/librarian/head", "/librarian/head/"})
	public LibrarianDto getHeadLibrarian() {
		return convertToDto(librarianService.getHeadLibrarian());
	}
	@GetMapping(value = {"/librarians", "/librarians/"})
	public List<LibrarianDto> getAllLibrarian() {
		return librarianService.getAllLibrarians().stream().map(p -> convertToDto(p)).collect(Collectors.toList());	
	}
	@DeleteMapping(value = { "/librarian/delete/{id}", "/librarian/delete/{id}/"})
	public LibrarianDto deleteLibrarian(@PathVariable("id") Long id, @RequestParam(value="librarianUsername") String librarianUsername) {
		return convertToDto(librarianService.removeLibrarian(librarianUsername, id));
	}

	@GetMapping(value = {"/librarian/schedules/{id}", "/librarian/schedules/{id}/"})
	public List<LibraryHourDto> getLibraryHourByLibrarianId(@PathVariable("id") Long id) {
		return convertToDtoLibraryHourList(librarianService.getLibraryHourByLibrarianId(id));
	}
	@PostMapping(value = {"/librarian/schedule/create/{id}", "/librarian/schedule/create/{id}/"})
	public LibraryHourDto createLibraryHour(@PathVariable("id") Long id, @RequestParam(value="librarianUsername") String librarianUsername, @RequestParam(value="username") String username, @RequestParam(value="startTime") Time startTime, @RequestParam(value="endTime") Time endTime, @RequestParam(value="day") Day day) {
		return convertToDto(librarianService.createLibraryHour(librarianUsername, username, startTime, endTime, day));
	}
	@PostMapping(value = {"/librarian/schedule/update/", "/librarian/schedule/update/"})
	public LibraryHourDto updateLibraryHour(@RequestParam(value="day") Day day, @RequestParam(value="librarianUsername") String librarianUsername, @RequestParam(value="username") String username, @RequestParam(value="startTime") Time startTime, @RequestParam(value="endTime") Time endTime) {
		return convertToDto(librarianService.updateLibraryHour(librarianUsername, username, startTime, endTime, day));
	}
	
	@PostMapping(value = {"/librarian/offline/create", "/librarian/offline/create/"})
	public OfflineUserDto createOfflineUser(@RequestParam(value="username") String librarianUsername, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="address") String address, @RequestParam(value="isLocal") boolean isLocal) {
		OfflineUser offlineUser = librarianService.createOfflineUser(librarianUsername, firstName, lastName, address, isLocal);
		return convertToDto(offlineUser);
	}
	@PostMapping(value = {"/librarian/online/create", "/librarian/online/create/"})
	public OnlineUserDto createOnlineUser(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="address") String address, @RequestParam(value="isLocal") boolean isLocal, @RequestParam(value="username") String username, @RequestParam(value="password") String password, @RequestParam(value="email") String email) {
		OnlineUser onlineUser = librarianService.createOnlineUser(firstName, lastName, address, isLocal, username, password, email);
		return convertToDto(onlineUser);
	}
	@PostMapping(value = {"/librarian/change-password", "/librarian/change-password/"})
	public OnlineUserDto changePassword(@RequestParam(value="useranme") String username, @RequestParam(value="oldPassword") String oldPassword, @RequestParam(value="newPassword") String newPassword) {
		OnlineUser onlineUser = librarianService.changePassword(username, oldPassword, newPassword);
		return convertToDto(onlineUser);
	}
//make optional requests?
	@PostMapping(value = {"/librarian/offline/update/{id}", "/librarian/offline/update/{id}/"})
	public OfflineUserDto updateOfflineUser(@PathVariable("id") Long id, @RequestParam(value="address") String address, @RequestParam(value="isLocal") boolean isLocal) {
		OfflineUser offlineUser = librarianService.updateOfflineUserInformation(id, address, isLocal);
		return convertToDto(offlineUser);
	}
	@PostMapping(value = {"/librarian/online/update/{id}", "/librarian/online/update/{id}/"})
	public OnlineUserDto updateOnlineUser(@PathVariable("id") Long id, @RequestParam(value="username") String username, @RequestParam(value="email") String email, @RequestParam(value="address") String address, @RequestParam(value="isLocal") boolean isLocal) {
		OnlineUser onlineUser = librarianService.updateOnlineUserInformation(id, username, email, address, isLocal);
		return convertToDto(onlineUser);
	}
	
	@PostMapping(value = {"/librarian/album/create", "/librarian/album/create/"})
	public AlbumDto createAlbum(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numSongs") int numSongs, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") MusicGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Album album = albumService.createAlbum(title, isArchive, isReservable, date, numSongs, available, genre, creator);
		return convertToDto(album);
	}
	@PostMapping(value = {"/librarian/album/update/{itemId}", "/librarian/album/update/{itemId}/"})
	public AlbumDto updateAlbum(@PathVariable("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(albumService.updateAlbum(itemId, isArchive, isReservable, available));
	}
	@DeleteMapping(value = {"/librarian/ablum/delete/{itemId}", "/librarian/album/delete/{itemId}/"})
	public AlbumDto deleteAlbum(@PathVariable("itemId") Long albumId) throws IllegalArgumentException {
		Album album = albumService.deleteAlbum(albumId);
		return convertToDto(album);
	}
	
	@PostMapping(value = {"/librarian/book/create/{librarian}", "/librarian/book/create/{librarian}/"})
	public BookDto createBook(@PathVariable("librarian") String librarianUsername, @RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numPages") int numPages, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") Book.BMGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Book book = bookService.createBook(title, isArchive, isReservable, date, numPages, available, genre, creator);
		return convertToDto(book);
	}
	@PostMapping(value = {"/librarian/book/update/{itemId}/{librarian}", "/librarian/book/update/{itemId}/{librarian}/"})
	public BookDto updateBook(@PathVariable("itemId") Long itemId, @PathVariable("librarian") String librarianUsername, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		return convertToDto(bookService.updateBook(itemId, isArchive, isReservable, available));
	}
	@DeleteMapping(value = {"/librarian/book/delete/{itemId}/{librarian}", "/librarian/book/delete/{itemId}/{librarian}/"})
	public BookDto deleteBook(@PathVariable("itemId") Long bookId, @PathVariable("librarian") String librarianUsername) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Book book = bookService.deleteBook(bookId);
		return convertToDto(book);
	}
	
	@PostMapping(value = {"/librarian/movie/create/{librarian}", "/librarian/movie/create/{librarian}/"})
	public MovieDto createMovie(@PathVariable("librarian") String librarianUsername, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") Movie.BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		librarianService.isLibrarian(librarianUsername);
		Creator creator = creatorService.getCreator(creatorId);
		Movie movie = movieService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		return convertToDto(movie);
	}
	@PostMapping(value = {"/librarian/movie/update/{itemId}/{librarian}", "/librarian/movie/update/{itemId}/{librarian}/"})
	public MovieDto updateMovie(@PathVariable("itemId") Long itemId, @PathVariable("librarian") String librarianUsername, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") Movie.BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		librarianService.isLibrarian(librarianUsername);
		return convertToDto(movieService.updateMovie(itemId, isArchive, isReservable, isAvailable));
	}
	@DeleteMapping(value = {"/librarian/movie/delete/{itemId}/{librarian}", "/librarian/movie/delete/{itemId}/{librarian}/"})
	public MovieDto deleteMovie(@PathVariable("itemId") Long itemId, @PathVariable("librarian") String librarianUsername) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Movie movie = movieService.getMovie(itemId);
		movieService.deleteMovie(itemId);
		return convertToDto(movie);
	}
	
	@PostMapping(value = {"/librarian/newspaper/create/{librarian}", "/librarian/newspaper/create/{librarian}/"})
	public NewspaperDto createNewspaper(@PathVariable("librarian") String librarianUsername, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(newspaperService.createNewspaper(title, isArchive, releaseDate, creator));
	}
	@PostMapping(value = {"/librarian/newspaper/update/{itemId}/{librarian}", "/librarian/newspaper/update/{itemId}/{librarian}/"})
	public NewspaperDto updateNewspaper(@PathVariable("itemId") Long itemId, @PathVariable("librarian") String librarianUsername, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(newspaperService.updateNewspaper(itemId, title, isArchive, releaseDate, creator));
	}
	@DeleteMapping(value = {"/librarian/newspaper/delete/{itemId}/{librarian}", "/librarian/newspaper/delete/{itemId}/{librarian}/"})
	public NewspaperDto deleteNewspaper(@PathVariable("itemId") Long itemId, @PathVariable("librarian") String librarianUsername) throws IllegalArgumentException {
		librarianService.isLibrarian(librarianUsername);
		Newspaper newspaper = newspaperService.getNewspaper(itemId);
		NewspaperDto  newspaperDto = convertToDto(newspaper);
		newspaperService.deleteNewspaper(itemId);
		return newspaperDto;
	}
	
	@DeleteMapping(value = { "/librarian/reservation/delete/{id}", "/librarian/reservation/delete/{id}/"})
	public ReservationDto deleteReservation(@PathVariable("id") Long id) {
		ReservationDto reservationDto = convertToDto(reservationService.getReservation(id));
		librarianService.removeReservation(id);
		return reservationDto;
	}
	@GetMapping(value = {"/librarian/reservation", "/librarian/reservation/"})
	public List<ReservationDto> getReservationById(@RequestParam(value="id") Long id) {
		return convertToDtoRList(librarianService.getReservationByUserId(id));
	}

	@GetMapping(value = {"/librarian/event-times", "/librarian/event-times/"})
	public List<TimeSlotDto> getTimeSlotWithEvent() {
		return convertToDtoTSList(librarianService.getTimeSlotsWithEvent());
	}
	@GetMapping(value = {"/librarian/events", "/librarian/events/"})
	public List<EventDto> getEvents() {
		return convertToDtoEList(eventService.getAllEvents());
	}
	@GetMapping(value = {"/librarian/events/{userId}", "/librarian/events/{userId}/"})
	public List<EventDto> getEventsByUser(@PathVariable("userId") Long userId) {
		return convertToDtoEList(librarianService.getEventsByUser(userId));
	}
	
	@PostMapping(value = {"/librarian/event/update/{eventId}", "/librarian/event/update/{eventId}/"})
	public EventDto updateEvent(@PathVariable("eventId") Long Id, @RequestParam(value="name") String name, @RequestParam(value="timeSlot") TimeSlot timeSlot, @RequestParam(value="isPrivate") Boolean isPrivate, @RequestParam(value="isAccepted") Boolean isAccepted, @RequestParam(value="user") User user) throws IllegalArgumentException {
		return convertToDto(eventService.updateEvent(Id, name, timeSlot, isPrivate, isAccepted, user));
	}
	@PostMapping(value = {"/librarian/event/accept/{eventId}", "/librarian/event/accept/{eventId}/"})
	public EventDto acceptEvent(@PathVariable("eventId") Long eventId) {
		return convertToDto(librarianService.acceptEvent(eventId));
	}
	@PostMapping(value = {"/librarian/event/reject/{eventId}", "/librarian/event/reject/{eventId}/"})
	public EventDto rejectEvent(@PathVariable("eventId") Long eventId) {
		return convertToDto(librarianService.rejectEvent(eventId));
	}
	
	private LibrarianDto convertToDto(Librarian librarian) throws IllegalArgumentException {
		if (librarian == null) {
			throw new IllegalArgumentException("Librarian does not exist.");
		}
		LibrarianDto librarianDto = new LibrarianDto(librarian.getFirstName(), librarian.getLastName(), librarian.getAddress(), librarian.getIsLocal(), librarian.getUsername(), librarian.getPassword(), librarian.getEmail(), librarian.getIsHead(), librarian.getUserId());
		return librarianDto;
	}
//requires edits	
    private OfflineUserDto convertToDto(OfflineUser offlineUser) throws IllegalArgumentException {
        if (offlineUser == null) {
            throw new IllegalArgumentException("Offline user does not exist");
        }
        return new OfflineUserDto(
                offlineUser.getFirstName(),
                offlineUser.getLastName(),
                offlineUser.getAddress(),
                offlineUser.getIsLocal(),
                offlineUser.getUserId());
    }
    private OnlineUserDto convertToDto(OnlineUser onlineUser) throws IllegalArgumentException {
        if (onlineUser == null) {
            throw new IllegalArgumentException("Online user does not exist");
        }
        return new OnlineUserDto(
                onlineUser.getFirstName(),
                onlineUser.getLastName(),
                onlineUser.getAddress(),
                onlineUser.getIsLocal(),
                onlineUser.getUsername(),
                onlineUser.getPassword(),
                onlineUser.getEmail(),
                onlineUser.getUserId()); 		
    }
    private LibraryHourDto convertToDto(LibraryHour libraryHour) throws IllegalArgumentException {
    	if (libraryHour == null ) {
    		throw new IllegalArgumentException("Library hour does not exist.");
    	}
    	
    	LibraryHourDto libraryHourDto = new LibraryHourDto(libraryHour.getStartTime(),
    			libraryHour.getEndTime(),
    			libraryHour.getDay(),
    			libraryHour.getLibraryHourId());
    	return libraryHourDto;
    }
    private List<LibraryHourDto> convertToDtoLibraryHourList(List<LibraryHour> libraryHours) {
		List<LibraryHourDto> libraryHourDtoList = new ArrayList<>();
		for (LibraryHour lh : libraryHours) {
			libraryHourDtoList.add(convertToDto(lh));
		}
		return libraryHourDtoList;
    }
	private AlbumDto convertToDto(Album album) throws IllegalArgumentException {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()), album.getItemId());
		return albumDto;
	}
	private BookDto convertToDto(Book book) throws IllegalArgumentException {
		if (book == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()), book.getItemId());
		return bookDto;
	}
	private MovieDto convertToDto(Movie movie) throws IllegalArgumentException {
		if (movie == null) {
			throw new IllegalArgumentException("Movie does not exist");
		}
		CreatorDto creatorDto = new CreatorDto(movie.getCreator().getFirstName(), movie.getCreator().getLastName(), movie.getCreator().getCreatorType(), movie.getCreator().getCreatorId());
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), creatorDto, movie.getItemId());
		return movieDto;
	}
	public NewspaperDto convertToDto(Newspaper newspaper) throws IllegalArgumentException {
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		NewspaperDto newspaperDto = new NewspaperDto(newspaper.getTitle(), newspaper.getIsArchive(), newspaper.getReleaseDate(), convertToDto(newspaper.getCreator()), newspaper.getItemId());
		return newspaperDto;
	}
	private CreatorDto convertToDto(Creator creator) throws IllegalArgumentException {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
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
			reservationDto.setUserDto(convertToDto(user));
		} else {
			if(librarianRepository.findLibrarianByUserId(user.getUserId()) != null) {
				reservationDto.setUserDto(convertToDto(user));
			} else {
				reservationDto.setUserDto(convertToDto(user));
			}
		}
		return reservationDto;
	}
	private List<ReservationDto> convertToDtoRList(List<Reservation> reservations) throws IllegalArgumentException {
		List<ReservationDto> reservationDtoList = new ArrayList<>();
		for (Reservation r : reservations) {
			reservationDtoList.add(convertToDto(r));
		}
		return reservationDtoList;
	}
	private ItemDto convertToDto(Item item) throws IllegalArgumentException {
		if(item == null) {
			throw new IllegalArgumentException("Input user is null.");
		}
		ItemDto itemDto = new AlbumDto();
		if(albumRepository.findAlbumByItemId(item.getItemId()) != null) {
			Album album = albumRepository.findAlbumByItemId(item.getItemId());
			itemDto.setCreator(convertToDto(album.getCreator()));
			itemDto.setIsArchive(album.getIsArchive());
			itemDto.setReleaseDate(album.getReleaseDate());
			itemDto.setTitle(album.getTitle());
		} else if(bookRepository.findBookByItemId(item.getItemId()) != null) {
			Book book = bookRepository.findBookByItemId(item.getItemId());
			itemDto.setCreator(convertToDto(book.getCreator()));
			itemDto.setIsArchive(book.getIsArchive());
			itemDto.setReleaseDate(book.getReleaseDate());
			itemDto.setTitle(book.getTitle());
		} else {
			Movie movie = movieRepository.findMovieByItemId(item.getItemId());
			itemDto.setCreator(convertToDto(movie.getCreator()));
			itemDto.setIsArchive(movie.getIsArchive());
			itemDto.setReleaseDate(movie.getReleaseDate());
			itemDto.setTitle(movie.getTitle());
		}
		return itemDto;
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
	private EventDto convertToDto(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist");
		}
		EventDto eventDto = new EventDto(event.getName(), event.getIsPrivate() , event.getIsAccepted(), convertToDto(event.getTimeSlot()), convertToDto(event.getUser()), event.getEventId());
		return eventDto;
	}
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		if (timeSlot == null) {
			throw new IllegalArgumentException("TimeSlot does not exist");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getTimeSlotId());
		return timeSlotDto;
	}
	private List<TimeSlotDto> convertToDtoTSList(List<TimeSlot> listTimeSlots) {
		List<TimeSlotDto> timeSlotDtoList = new ArrayList<>();
		for (TimeSlot ts : listTimeSlots) {
			timeSlotDtoList.add(convertToDto(ts));
		}
		return timeSlotDtoList;
	}
	private List<EventDto> convertToDtoEList(List<Event> listEvents) {
		List<EventDto> eventDtoList = new ArrayList<>();
		for (Event e : listEvents) {
			eventDtoList.add(convertToDto(e));
		}
		return eventDtoList;
	}
//should check
	private UserDto convertToDto(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User does not exist");
		}
		if (user instanceof Librarian) {
			Librarian librarian = (Librarian) user;
			LibrarianDto userDto = new LibrarianDto(librarian.getFirstName(),
					librarian.getLastName(),
					librarian.getAddress(),
					librarian.getIsLocal(),
					librarian.getUsername(),
					librarian.getPassword(),
					librarian.getEmail(),
					librarian.getIsHead(),
					librarian.getUserId());
			return userDto;
		}
		if (user instanceof OnlineUser) {
			OnlineUser onlineUser = (OnlineUser) user;
			OnlineUserDto userDto = new OnlineUserDto(onlineUser.getFirstName(), 
					onlineUser.getLastName(),
					onlineUser.getAddress(),
					onlineUser.getIsLocal(),
					onlineUser.getUsername(),
					onlineUser.getPassword(),
					onlineUser.getEmail(),
					onlineUser.getUserId());
			return userDto;
		}
		if (user instanceof OfflineUser) {
			OfflineUser offlineUser = (OfflineUser) user;
			OfflineUserDto userDto = new OfflineUserDto(offlineUser.getFirstName(), 
					offlineUser.getLastName(),
					offlineUser.getAddress(),
					offlineUser.getIsLocal(),
					offlineUser.getUserId());
			return userDto;
		}		
		OfflineUserDto userDto = new OfflineUserDto();
		return userDto;
	}
	
}
