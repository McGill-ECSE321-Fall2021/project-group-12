package ca.mcgill.ecse321.library.controller;
import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.EventDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.dto.OnlineUserDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OnlineUserRestController {

	@Autowired
	private OnlineUserService service;
	
	@GetMapping(value = {"/onlineusers", "/onlineusers/"})
	public List<OnlineUserDto> getAllOnlineUsers(){
		return service.getAllOnlineUsers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/onlineuser/userId/{userId}", "/onlineuser/userId/{userId}/"})
	public OnlineUserDto getOnlineUserByUserId(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDto(service.getOnlineUser(userId));
	}
	
	@GetMapping(value = {"/onlineuser/username/{username}", "/onlineuser/username/{username}/"})
	public OnlineUserDto getOnlineUserByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDto(service.getOnlineUserByUsername(username));
	}
	
	@GetMapping(value = {"/onlineuser/email/{email}", "/onlineuser/email/{email}/"})
	public OnlineUserDto getOnlineUserByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(service.getOnlineUserByEmail(email));
	}
	
	@PostMapping(value = {"/onlineuser/login", "/onlineuser/login/"})
	public OnlineUserDto loginOnlineUser(@RequestParam("username") String username, @RequestParam("password") String password) throws IllegalArgumentException {
		return convertToDto(service.login(username, password));
	}
	
	@PostMapping(value = {"/onlineuser/create", "/onlineuser/create/"})
	public OnlineUserDto createOnlineUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("address") String address, @RequestParam("isLocal") boolean isLocal, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) throws IllegalArgumentException {
		return convertToDto(service.createOnlineUser(firstName, lastName, address, isLocal, username, password, email));
	}
	
	@PutMapping(value = {"/onlineuser/update/username", "/onlineuser/update/username/"})
	public OnlineUserDto updateOnlineUserUsername(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("newUsername") String newUsername) throws IllegalArgumentException {
		return convertToDto(service.changeUsername(username, password, newUsername));
	}
	
	@PutMapping(value = {"/onlineuser/update/password", "/onlineuser/update/password/"})
	public OnlineUserDto updateOnlineUserPassword(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword) throws IllegalArgumentException {
		return convertToDto(service.changePassword(username, password, newPassword));
	}
	
	@PutMapping(value = {"/onlineuser/update", "/onlineuser/update/"})
	public OnlineUserDto updateOnlineUser(@RequestParam("userId") Long userId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("address") String address, @RequestParam("isLocal") boolean isLocal, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) throws IllegalArgumentException {
		return convertToDto(service.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email));
	}
	
	@DeleteMapping(value = {"/onlineuser/delete/userId/{userId}", "/onlineuser/delete/userId/{userId}/"})
	public boolean deleteOnlineUserByUserId(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return service.deleteOnlineUserByUserId(userId);
	}
	
	@DeleteMapping(value = {"/onlineuser/delete/username/{username}", "/onlineuser/delete/username/{username}/"})
	public boolean deleteOnlineUserByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
		return service.deleteOnlineUserByUsername(username);
	}
	
	@DeleteMapping(value = {"/onlineuser/delete/email/{email}", "/onlineuser/delete/email/{email}/"})
	public boolean deleteOnlineUserByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
		return service.deleteOnlineUserByEmail(email);
	}
	
	@PostMapping(value = {"/onlineuser/reserve/userId/{userId}", "/onlineuser/reserve/userId/{userId}/"})
	public ReservationDto reserveItems(@PathVariable("userId") Long userId, @RequestParam("itemIds") List<Long> itemIds, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		return convertToDto(service.reserveItems(userId, itemIds, timeSlotId));
	}
	
	@PostMapping(value = {"/onlineuser/reserve/username/{username}", "/onlineuser/reserve/username/{username}/"})
	public ReservationDto reserveItems(@PathVariable("username") String username, @RequestParam("itemIds") List<Long> itemIds, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		OnlineUser onlineuser = service.getOnlineUserByUsername(username);
		return convertToDto(service.reserveItems(onlineuser.getUserId(), itemIds, timeSlotId));
	}
	
	@GetMapping(value = {"/onlineuser/reservations/userId/{userId}", "/onlineuser/reservations/userId/{userId}/"})
	public List<ReservationDto> getOnlineUserReservations(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfReservations(service.getReservations(userId));
	}
	
	@GetMapping(value = {"/onlineuser/reservations/username/{username}", "/onlineuser/reservations/username/{username}/"})
	public List<ReservationDto> getOnlineUserReservations(@PathVariable("username") String username) throws IllegalArgumentException {
		OnlineUser onlineuser = service.getOnlineUserByUsername(username);
		return convertToDtoListOfReservations(service.getReservations(onlineuser.getUserId()));
	}
	
	@PostMapping(value = {"/onlineuser/additem/userId/{userId}", "/onlineuser/additem/userId/{userId}/"})
	public boolean addItemToReservation(@PathVariable("userId") Long userId, @RequestParam("reservationId") Long reservationId, @RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return service.addItemToReservation(userId, reservationId, itemId);
	}
	
	@PostMapping(value = {"/onlineuser/additem/username/{username}", "/onlineuser/additem/username/{username}/"})
	public boolean addItemToReservation(@PathVariable("username") String username, @RequestParam("reservationId") Long reservationId, @RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return service.addItemToReservation(username, reservationId, itemId);
	}
	
	@PostMapping(value = {"/onlineuser/cancelreservation/userId/{userId}", "/onlineuser/cancelreservation/userId/{userId}/"})
	public boolean cancelReservation(@PathVariable("userId") Long userId, @RequestParam("reservationId") Long reservationId) throws IllegalArgumentException {
		return service.cancelReservation(userId, reservationId);
	}
	
	@PostMapping(value = {"/onlineuser/cancelreservation/username/{username}", "/onlineuser/cancelreservation/username/{username}/"})
	public boolean cancelReservation(@PathVariable("username") String username, @RequestParam("reservationId") Long reservationId) throws IllegalArgumentException {
		return service.cancelReservation(username, reservationId);
	}
	
	@PostMapping(value = {"/onlineuser/requestbooking/userId/{userId}", "/onlineuser/requestbooking/userId/{userId}/"})
	public EventDto requestBooking(@PathVariable("userId") Long userId, @RequestParam("name") String name, @RequestParam("isPrivate") boolean isPrivate, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		return convertToDto(service.requestBooking(userId, name, isPrivate, timeSlotId));
	}
	
	@PostMapping(value = {"/onlineuser/requestbooking/username/{username}", "/onlineuser/requestbooking/username/{username}/"})
	public EventDto requestBooking(@PathVariable("username") String username, @RequestParam("name") String name, @RequestParam("isPrivate") boolean isPrivate, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		return convertToDto(service.requestBooking(username, name, isPrivate, timeSlotId));
	}

	@GetMapping(value = {"/onlineuser/requests/userId/{userId}", "/onlineuser/requests/userId/{userId}/"})
	public List<EventDto> getRequests(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getRequests(userId));
	}
	
	@GetMapping(value = {"/onlineuser/requests/username{username}", "/onlineuser/requests/username/{username}/"})
	public List<EventDto> getRequests(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getRequests(username));
	}
	
	@GetMapping(value = {"/onlineuser/events/userId/{userId}", "/onlineuser/events/userId/{userId}/"})
	public List<EventDto> getEvents(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getEvents(userId));
	}

	@GetMapping(value = {"/onlineuser/events/username/{username}", "/onlineuser/events/username/{username}/"})
	public List<EventDto> getEvents(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getEvents(username));
	}
	
	@PostMapping(value = {"/onlineuser/cancelevent/userId/{userId}", "/onlineuser/cancelevent/userId/{userId}/"})
	public boolean cancelEvent(@PathVariable("userId") Long userId, @RequestParam("eventId") Long eventId) throws IllegalArgumentException {
		return service.cancelEvent(userId, eventId);
	}
	
	@PostMapping(value = {"/onlineuser/cancelevent/username/{username}", "/onlineuser/cancelevent/username/{username}/"})
	public boolean cancelEvent(@PathVariable("username") String username, @RequestParam("eventId") Long eventId) throws IllegalArgumentException {
		return service.cancelEvent(username, eventId);
	}
	
	@GetMapping(value = {"/items/getbytitle/{title}", "/items/getbytitle/{title}/"})
	public List<ItemDto> getItemsByTitle(@PathVariable("title") String title) throws IllegalArgumentException{
		return convertToDto(service.getItemsByTitle(title));
	}
	
	@GetMapping(value = {"/albums/getbytitle/{title}", "/albums/getbytitle/{title}/"})
	public List<AlbumDto> getAlbumsByTitle(@PathVariable("title") String title) throws IllegalArgumentException {
		return convertToDtoListOfAlbums(service.getAlbumsByTitle(title));
	}
	
	@GetMapping(value = {"/books/getbytitle/{title}", "/books/getbytitle/{title}/"})
	public List<BookDto> getBooksByTitle(@PathVariable("title") String title) throws IllegalArgumentException {
		return convertToDtoListOfBooks(service.getBooksByTitle(title));
	}

	@GetMapping(value = {"/movies/getbytitle/{title}", "/movies/getbytitle/{title}/"})
	public List<MovieDto> getMoviesByTitle(@PathVariable("title") String title) throws IllegalArgumentException {
		return convertToDtoListOfMovies(service.getMoviesByTitle(title));
	}

	@GetMapping(value = {"/newspapers/getbytitle/{title}", "/newspapers/getbytitle/{title}/"})
	public List<NewspaperDto> getNewspapersByTitle(@PathVariable("title") String title) throws IllegalArgumentException {
		return convertToDtoListOfNewspapers(service.getNewspapersByTitle(title));
	}
	
	private EventDto convertToDto(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist");
		}
		if (!(event.getUser() instanceof OnlineUser)) {
			throw new IllegalArgumentException("User is not an OnlineUser");
		}
		EventDto eventDto = new EventDto(event.getName(), event.getIsPrivate() , event.getIsAccepted(), convertToDto(event.getTimeSlot()), convertToDto((OnlineUser)event.getUser()), event.getEventId());
		return eventDto;
	}
	
	private ReservationDto convertToDto(Reservation reservation) throws IllegalArgumentException {
		if (reservation == null) {
			throw new IllegalArgumentException("Reservation cannot be null");
		}
		List<ItemDto> itemDtos = convertToDto(reservation.getItems());
		TimeSlotDto timeSlotDto = convertToDto(reservation.getTimeSlot());
		User user = reservation.getUser();
		if (!(user instanceof OnlineUser)) {
			throw new IllegalArgumentException("User is not an Online User");
		}
		OnlineUserDto onlineUserDto = convertToDto((OnlineUser)user);
		ReservationDto reservationDto = new ReservationDto(itemDtos, onlineUserDto, timeSlotDto, reservation.getReservationId());
		return reservationDto;
	}
	
	private TimeSlotDto convertToDto(TimeSlot timeSlot) {
		
		if (timeSlot == null) {
			throw new IllegalArgumentException("Timeslot does not exist");
		}
		
		TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getStartDate(), timeSlot.getEndDate(), timeSlot.getTimeSlotId());
		return timeSlotDto;
	}
	
	private List<ReservationDto> convertToDtoListOfReservations(List<Reservation> reservations){
		List<ReservationDto> reservationDtos = new ArrayList<ReservationDto>();
		for (Reservation r: reservations) {
			reservationDtos.add(convertToDto(r));
		}
		return reservationDtos;
	}
	
	private List<EventDto> convertToDtoListOfEvents(List<Event> events){
		List<EventDto> eventDtos = new ArrayList<EventDto>();
		for (Event e: events) {
			eventDtos.add(convertToDto(e));
		}
		return eventDtos;
	}
	
	private List<AlbumDto> convertToDtoListOfAlbums(List<Album> albums){
		List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
		for (Album a: albums) {
			albumDtos.add(convertToDto(a));
		}
		return albumDtos;
	}
	
	private List<BookDto> convertToDtoListOfBooks(List<Book> books){
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		for (Book b: books) {
			bookDtos.add(convertToDto(b));
		}
		return bookDtos;
	}
	
	private List<MovieDto> convertToDtoListOfMovies(List<Movie> movies){
		List<MovieDto> movieDtos = new ArrayList<MovieDto>();
		for (Movie m: movies) {
			movieDtos.add(convertToDto(m));
		}
		return movieDtos;
	}
	
	private List<NewspaperDto> convertToDtoListOfNewspapers(List<Newspaper> newspapers){
		List<NewspaperDto> newspaperDtos = new ArrayList<NewspaperDto>();
		for (Newspaper n: newspapers) {
			newspaperDtos.add(convertToDto(n));
		}
		return newspaperDtos;
	}
	
	private List<ItemDto> convertToDto(List<Item> items){
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		for (Item i: items) {
			if (i instanceof Movie) {
				Movie movie = (Movie)i;
				MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), convertToDto(movie.getCreator()), movie.getItemId());
				itemDtos.add(movieDto);
			} else if (i instanceof Album) {
				Album album = (Album)i;
				AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()), album.getItemId());
				itemDtos.add(albumDto);
			} else if (i instanceof Book) {
				Book book = (Book)i;
				BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()), book.getItemId());
				itemDtos.add(bookDto);
			} else {
				Newspaper newspaper = (Newspaper)i;
				NewspaperDto newspaperDto = new NewspaperDto(newspaper.getTitle(), newspaper.getIsArchive(), newspaper.getReleaseDate(), convertToDto(newspaper.getCreator()), newspaper.getItemId());
				itemDtos.add(newspaperDto);
			}
		}
		return itemDtos;
	}
	
	private AlbumDto convertToDto(Album album) {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()), album.getItemId());
		return albumDto;
	}
	
	private BookDto convertToDto(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()), book.getItemId());
		return bookDto;
	}
	
	private MovieDto convertToDto(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getIsArchive(), movie.getIsReservable(), movie.getIsAvailable(), movie.getReleaseDate(), movie.getDuration(), movie.getGenre(), convertToDto(movie.getCreator()), movie.getItemId());
		return movieDto;
	}
	
	public NewspaperDto convertToDto(Newspaper newspaper) {
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		NewspaperDto newspaperDto = new NewspaperDto(newspaper.getTitle(), newspaper.getIsArchive(), newspaper.getReleaseDate(), convertToDto(newspaper.getCreator()), newspaper.getItemId());
		return newspaperDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
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
}
