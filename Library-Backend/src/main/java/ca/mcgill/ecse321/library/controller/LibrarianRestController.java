package ca.mcgill.ecse321.library.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.EventDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.dto.OfflineUserDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.dto.UserDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.service.CreatorService;
import ca.mcgill.ecse321.library.service.MovieService;
import ca.mcgill.ecse321.library.service.NewspaperService;
import ca.mcgill.ecse321.library.service.LibrarianService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = ",")
@RestController
public class LibrarianRestController {
	
	@Autowired
	private LibrarianService librarianService;
	@Autowired
	private CreatorService creatorService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private NewspaperService newspaperService;
	
//	@PostMapping(value = {"/librarian/create/head", "/librarian/create/head/"})
//	@PostMapping(value = {"/librarian/create", "/librarian/create/"})
//	@GetMapping(value = {"/librarian/{itemId}", "/librarian/{itemId}/"})
//	@GetMapping(value = {"/librarian/{username}", "/librarian/{username}/"})
//	@GetMapping(value = {"/librarian/head", "/librarian/head/"})
//	@GetMapping(value = {"/librarian", "/librarian/"})
//	@DeleteMapping(value = { "/librarian/delete/{id}", "/librarian/delete/{id}/"})
//
//	@GetMapping(value = {"/librarian/schedule", "/librarian/schedule/"})
//	@PostMapping(value = {"/librarian/create/schedule", "/librarian/create/schedule/"})
//	@PostMapping(value = {"/librarian/update/schedule", "/librarian/update/schedule/"})
//	@PostMapping(value = {"/librarian/create/offline", "/librarian/create/offline/"})
//	@PostMapping(value = {"/librarian/create/online", "/librarian/create/online/"})
//	@PostMapping(value = {"/librarian/change-password", "/librarian/change-password/"})
//	@PostMapping(value = {"/librarian/update/online", "/librarian/update/online/"})
//	@PostMapping(value = {"/librarian/update/offline", "/librarian/update/offline/"})
	
	@PostMapping(value = {"/librarian/create/album", "/librarian/create/album/"})
	public AlbumDto createAlbum(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numSongs") int numSongs, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") MusicGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Album album = librarianService.createAlbum(title, isArchive, isReservable, date, numSongs, available, genre, creator);
		return convertToDto(album);
	}
	@PostMapping(value = {"/librarian/update/album/{itemId}", "/librarian/update/album/{itemId}/"})
	public AlbumDto updateAlbum(@PathVariable("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(librarianService.updateAlbum(itemId, isArchive, isReservable, available));
	}
	@DeleteMapping(value = {"/librarian/delete/album/{itemId}", "/librarian/delete/album/{itemId}/"})
	public AlbumDto deleteAlbum(@PathVariable("itemId") Long albumId) throws IllegalArgumentException {
		Album album = librarianService.deleteAlbum(albumId);
		return convertToDto(album);
	}
	
	@PostMapping(value = {"/librarian/create/book", "/librarian/create/book/"})
	public BookDto createBook(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numPages") int numPages, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") Book.BMGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Book book = librarianService.createBook(title, isArchive, isReservable, date, numPages, available, genre, creator);
		return convertToDto(book);
	}
	@PostMapping(value = {"/librarian/update/book/{itemId}", "/librarian/update/book/{itemId}/"})
	public BookDto updateBook(@PathVariable("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(librarianService.updateBook(itemId, isArchive, isReservable, available));
	}
	@DeleteMapping(value = {"/librarian/delete/book/{itemId}", "/librarian/delete/book/{itemId}/"})
	public BookDto deleteBook(@PathVariable("itemId") Long bookId) throws IllegalArgumentException {
		Book book = librarianService.deleteBook(bookId);
		return convertToDto(book);
	}
	
	@PostMapping(value = {"/librarian/create/movie", "/librarian/create/movie/"})
	public MovieDto createMovie(@RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") Movie.BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		Creator creator = creatorService.getCreator(creatorId);
		Movie movie = librarianService.createMovie(title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator);
		return convertToDto(movie);
	}
	@PostMapping(value = {"/librarian/update/movie/{itemId}", "/librarian/update/movie/{itemId}/"})
	public MovieDto updateMovie(@PathVariable("itemId") Long itemId, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean isAvailable, @RequestParam("releaseDate") Date releaseDate, @RequestParam("duration") int duration, @RequestParam("genre") Movie.BMGenre genre, @RequestParam("creatorId") Long creatorId) {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(librarianService.updateMovie(itemId, title, isArchive, isReservable, releaseDate, isAvailable, duration, genre, creator));
	}
	@DeleteMapping(value = {"/librarian/delete/movie/{itemId}", "/librarian/delete/movie/{itemId}/"})
	public MovieDto deleteMovie(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		Movie movie = movieService.getMovie(itemId);
		librarianService.deleteMovie(itemId);
		return convertToDto(movie);
	}
	
	@PostMapping(value = {"/librarian/create/newspaper", "/librarian/create/newspaper/"})
	public NewspaperDto createNewspaper(@RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(librarianService.createNewspaper(title, isArchive, releaseDate, creator));
	}
	@PostMapping(value = {"/librarian/update/newspaper/{itemId}", "/librarian/update/newspaper/{itemId}/"})
	public NewspaperDto updateNewspaper(@PathVariable("itemId") Long itemId, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(librarianService.updateNewspaper(itemId, title, isArchive, releaseDate, creator));
	}
	@DeleteMapping(value = {"/librarian/delete/newspaper/{itemId}", "/librarian/delete/newspaper/{itemId}/"})
	public NewspaperDto deleteNewspaper(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		Newspaper newspaper = newspaperService.getNewspaper(itemId);
		NewspaperDto  newspaperDto = convertToDto(newspaper);
		librarianService.deleteNewspaper(itemId);
		return newspaperDto;
	}
//	@DeleteMapping(value = { "/librarian/delete/reservation/{id}", "/librarian/delete/reservation/{id}/"})
//	@GetMapping(value = {"/librarian/reservation/{id}", "/librarian/reservation/{id}/"})
//	@GetMapping(value = {"/librarian/reservation/{first}/{last}", "/librarian/reservation/{first}/{last}/"})
//
//	@GetMapping(value = {"/librarian/event-times", "/librarian/event-times/"})
//	@GetMapping(value = {"/librarian/event", "/librarian/event/"})
//	@GetMapping(value = {"/librarian/event/{userId}", "/librarian/event/{userId}/"})
	
	@PostMapping(value = {"/librarian/update/event/{eventId}", "/librarian/update/event/{eventId}/"})
	public EventDto updateEvent(@PathVariable("eventId") Long Id, @RequestParam(value="name") String name, @RequestParam(value="timeSlot") TimeSlot timeSlot, @RequestParam(value="isPrivate") Boolean isPrivate, @RequestParam(value="isAccepted") Boolean isAccepted, @RequestParam(value="user") User user) throws IllegalArgumentException {
		return convertToDto(librarianService.updateEvent(Id, name, timeSlot, isPrivate, isAccepted, user));
	}
	@PostMapping(value = {"/librarian/accept/event/{eventId}", "/librarian/accept/event/{eventId}/"})
	public EventDto acceptEvent(@PathVariable("eventId") Long eventId) {
		return convertToDto(librarianService.acceptEvent(eventId));
	}
	@PostMapping(value = {"/librarian/reject/event/{eventId}", "/librarian/reject/event/{eventId}/"})
	public EventDto rejectEvent(@PathVariable("eventId") Long eventId) {
		return convertToDto(librarianService.rejectEvent(eventId));
	}
	
	
	private AlbumDto convertToDto(Album album) {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()));
		return albumDto;
	}
	private BookDto convertToDto(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()));
		return bookDto;
	}
	private MovieDto convertToDto(Movie movie) throws IllegalArgumentException {
		if (movie == null) {
			throw new IllegalArgumentException("Movie does not exist");
		}
		CreatorDto creatorDto = new CreatorDto(movie.getCreator().getFirstName(), movie.getCreator().getLastName(), movie.getCreator().getCreatorType(), movie.getCreator().getCreatorId());
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), creatorDto);
		return movieDto;
	}
	public NewspaperDto convertToDto(Newspaper newspaper) {
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		NewspaperDto newspaperDto = new NewspaperDto(newspaper.getTitle(), newspaper.getIsArchive(), newspaper.getReleaseDate(), convertToDto(newspaper.getCreator()));
		return newspaperDto;
	}
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
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
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		return timeSlotDto;
	}
	//should check
	private UserDto convertToDto(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User does not exist");
		}
		//UserDto userDto = new UserDto();
		OfflineUserDto userDto = new OfflineUserDto();
		return userDto;
	}
	
}
