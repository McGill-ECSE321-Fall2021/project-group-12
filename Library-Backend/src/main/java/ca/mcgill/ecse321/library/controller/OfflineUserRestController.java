package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.EventDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.dto.OfflineUserDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.dto.TimeSlotDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.service.OfflineUserService;
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
public class OfflineUserRestController {

    @Autowired
    private OfflineUserService service;

    //get all online users
    @GetMapping(value = {"/offlineusers", "/offlineusers/"})
	public List<OfflineUserDto> getAllOfflineUsers(){
		return service.getAllOfflineUsers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

    //get one user by id
    @GetMapping(value = {"/offlineuser/userId/{userId}", "/offlineuser/userId/{userId}/"})
	public OfflineUserDto getOfflineUserByUserId(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDto(service.getOfflineUser(userId));
	}
    
    @PostMapping(value = {"/offlineuser/login", "/offlineuser/login/"})
	public OfflineUserDto loginOnlineUser(@RequestParam("id") Long id) throws IllegalArgumentException {
		return convertToDto(service.login(id));
	}
    
    //creating new user
    @PostMapping(value = {"/offlineuser/create", "/offlineuser/create/"})
    public OfflineUserDto createRestOfflineUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("address") String address, @RequestParam("isLocal") boolean isLocal) {
    	return convertToDto(service.createOfflineUser(firstname, lastname, address, isLocal));
    }

    //updating exist user
    @PutMapping(value = {"/offlineuser/update/{id}", "/offlineuser/update/{id}/"})
    public OfflineUserDto updateRestOfflineUser(@PathVariable("id") long id, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("address") String address, @RequestParam("isLocal") boolean isLocal) {
        return convertToDto(service.updateOfflineUser(id,firstname,lastname,address,isLocal));
    }
    
    //deleting user by id
    @DeleteMapping(value = { "/offlineuser/delete/{id}", "/offlineuser/delete/{id}/"})
    public OfflineUserDto deleteRestOfflineUser(@PathVariable("id") Long id) throws IllegalArgumentException {
        OfflineUser offlineUser = service.getOfflineUser(id);
        service.deleteOfflineUser(id);
        return convertToDto(offlineUser);
    }
    
    @PostMapping(value = {"/offlineuser/reserve/{userId}", "/offlineuser/reserve/{userId}/"})
	public ReservationDto reserveItems(@PathVariable("userId") Long userId, @RequestParam("itemIds") List<Long> itemIds, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		return convertToDto(service.reserveItems(userId, itemIds, timeSlotId));
	}
    
    @GetMapping(value = {"/offlineuser/reservations/{userId}", "/offlineuser/reservations/{userId}/"})
	public List<ReservationDto> getReservations(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfReservations(service.getReservations(userId));
	}
    
    @PostMapping(value = {"/offlineuser/additem/{userId}", "/offlineuser/additem/{userId}/"})
	public boolean addItemToReservation(@PathVariable("userId") Long userId, @RequestParam("reservationId") Long reservationId, @RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return service.addItemToReservation(userId, reservationId, itemId);
	}
    
    @PostMapping(value = {"/offlineuser/cancelreservation/{userId}", "/offlineuser/cancelreservation/{userId}/"})
	public boolean cancelReservation(@PathVariable("userId") Long userId, @RequestParam("reservationId") Long reservationId) throws IllegalArgumentException {
		return service.cancelReservation(userId, reservationId);
	}
    
    @PostMapping(value = {"/offlineuser/requestbooking/{userId}", "/offlineuser/requestbooking/{userId}/"})
	public EventDto requestBooking(@PathVariable("userId") Long userId, @RequestParam("name") String name, @RequestParam("isPrivate") boolean isPrivate, @RequestParam("timeSlotId") Long timeSlotId) throws IllegalArgumentException {
		return convertToDto(service.requestBooking(userId, name, isPrivate, timeSlotId));
	}
    
    @GetMapping(value = {"/offlineuser/requests/{userId}", "/offlineuser/requests/{userId}/"})
	public List<EventDto> getRequests(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getRequests(userId));
	}
    
    @GetMapping(value = {"/offlineuser/events/{userId}", "/offlineuser/events/{userId}/"})
	public List<EventDto> getEvents(@PathVariable("userId") Long userId) throws IllegalArgumentException {
		return convertToDtoListOfEvents(service.getEvents(userId));
	}
    
    @PostMapping(value = {"/onlineuser/cancelevent/{userId}", "/onlineuser/cancelevent/{userId}/"})
	public boolean cancelEvent(@PathVariable("userId") Long userId, @RequestParam("eventId") Long eventId) throws IllegalArgumentException {
		return service.cancelEvent(userId, eventId);
	}
    
    private EventDto convertToDto(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist");
		}
		if (!(event.getUser() instanceof OnlineUser)) {
			throw new IllegalArgumentException("User is not an OnlineUser");
		}
		EventDto eventDto = new EventDto(event.getName(), event.getIsPrivate() , event.getIsAccepted(), convertToDto(event.getTimeSlot()), convertToDto((OfflineUser)event.getUser()), event.getEventId());
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
		OfflineUserDto offlineUserDto = convertToDto((OfflineUser)user);
		ReservationDto reservationDto = new ReservationDto(itemDtos, offlineUserDto, timeSlotDto, reservation.getReservationId());
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
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
	}

    //this is simple converter from dto to use object - somehow we use this for security
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
}
