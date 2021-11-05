package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineUserService {

    @Autowired
    OnlineUserRepository onlineUserRepository;
    
    @Autowired
    ReservationRepository reservationRepository;
    
    @Autowired
    EventRepository eventRepository; 
    
    @Autowired
    AlbumRepository albumRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    MovieRepository movieRepository;

    //This method getting all the online user details and save it to the database
    @Transactional
    public OnlineUser createOnlineUser(String firstName,
                                       String lastName,
                                       String address,
                                       boolean isLocal,
                                       String username,
                                       String password,
                                       String email) {
        OnlineUser onlineUser = new OnlineUser();
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("the first name cannot have an empty first name.");
        }
        if (lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("the last name cannot have an empty last name.");
        }
        if (address == null || address.trim().length() == 0) {
            throw new IllegalArgumentException("the address cannot have an empty address.");
        }
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("the username cannot have an empty username.");
        }
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("the password cannot have an empty password.");
        }
        if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("the email cannot have an empty email.");
        }
        // confirm username and email are unique
        Iterable<OnlineUser> iterable = onlineUserRepository.findAll();
        for (OnlineUser user:iterable) {
        	if (user.getUsername() == username ) {
        		throw new IllegalArgumentException("A user with that username already exists.");
        	} else if (user.getEmail() == email) {
        		throw new IllegalArgumentException("A user with that email already exists.");
        	}
        }
        // confirm email is of valid form.
        if (!isEmail(email)) {
        	throw new IllegalArgumentException("Provided email is invalid.");
        }
        onlineUser.setFirstName(firstName);
        onlineUser.setLastName(lastName);
        onlineUser.setAddress(address);
        onlineUser.setIsLocal(isLocal);
        onlineUser.setUsername(username);
        onlineUser.setPassword(password);
        onlineUser.setEmail(email);
        onlineUserRepository.save(onlineUser);
        return onlineUser;
    }

    //this method will get online user details and update it in the database
    @Transactional
    public OnlineUser updateOnlineUser(Long id,
                                       String firstName,
                                       String lastName,
                                       String address,
                                       boolean isLocal,
                                       String username,
                                       String password,
                                       String email) throws IllegalArgumentException {
        OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("the first name cannot have an empty first name.");
        }
        if (lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("the last name cannot have an empty last name.");
        }
        if (address == null || address.trim().length() == 0) {
            throw new IllegalArgumentException("the address cannot have an empty address.");
        }
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("the username cannot have an empty username.");
        }
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("the password cannot have an empty password.");
        }
        if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("the email cannot have an empty email.");
        }
        // confirm username and email are unique
        Iterable<OnlineUser> iterable = onlineUserRepository.findAll();
        for (OnlineUser user:iterable) {
        	if (user.getUserId() != id && user.getUsername() == username) {
        		throw new IllegalArgumentException("A user with that username already exists.");
        	} else if (user.getUserId() != id && user.getEmail() == email) {
        		throw new IllegalArgumentException("A user with that email already exists.");
        	}
        }
        // confirm email is of valid form.
        if (!isEmail(email)) {
        	throw new IllegalArgumentException("Provided email is invalid.");
        }
        onlineUser.setFirstName(firstName);
        onlineUser.setLastName(lastName);
        onlineUser.setIsLocal(isLocal);
        onlineUser.setUsername(username);
        onlineUser.setPassword(password);
        onlineUser.setEmail(email);
        onlineUserRepository.save(onlineUser);
        return onlineUser;
    }

    // will show all online users from database
    @Transactional
    public List<OnlineUser> getAllOnlineUsers() {
        return toList(onlineUserRepository.findAll());
    }

    // will get 1 online user by id
    @Transactional
    public OnlineUser getOnlineUser(Long id) throws IllegalArgumentException {
        OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
        if (onlineUser == null) {
            throw new IllegalArgumentException("Online user does not exist.");
        }
        return onlineUser;
    }
    
    @Transactional
    public OnlineUser getOnlineUserByUsername(String username) throws IllegalArgumentException {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
    		throw new IllegalArgumentException("Online user does not exist.");
    	}
    	return onlineUser;
    }
    
    @Transactional
    public OnlineUser getOnlineUserByEmail(String email) throws IllegalArgumentException {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByEmail(email);
    	if (onlineUser == null) {
    		throw new IllegalArgumentException("Online user does not exist.");
    	}
    	return onlineUser;
    }

    // delete user from database by the id
    @Transactional
    public boolean deleteOnlineUser(Long id) {
        OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
        if (onlineUser == null) {
            throw new IllegalArgumentException("Online user does not exist.");
        }
        onlineUserRepository.delete(onlineUser);
        return true;
    }
    
    @Transactional
    public Reservation reserveItems(Long id, List<Item> items, TimeSlot timeSlot) {
        OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
        if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
        }
        if (items == null || items.size() == 0) {
        	throw new IllegalArgumentException("Items cannot be empty.");
        }
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
    	Reservation reservation = new Reservation();
    	reservation.setUser(onlineUser);
    	reservation.setItems(items);
    	reservation.setTimeSlot(timeSlot);
    	reservationRepository.save(reservation);
    	return reservation;
    }
    
    @Transactional
    public Reservation reserveItems(String username, List<Item> items, TimeSlot timeSlot) {
        OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
        if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
        }
        if (items == null || items.size() == 0) {
        	throw new IllegalArgumentException("Items cannot be empty.");
        }
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
    	Reservation reservation = new Reservation();
    	reservation.setUser(onlineUser);
    	reservation.setItems(items);
    	reservation.setTimeSlot(timeSlot);
    	reservationRepository.save(reservation);
    	return reservation;
    }
    
    @Transactional
    public List<Reservation> getReservations(Long id) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	List<Reservation> reservations = toList(reservationRepository.findAll());
    	List<Reservation> userReservations = new ArrayList<Reservation>();
    	for (Reservation r:reservations) {
    		if (r.getUser() != null && r.getUser().getUserId() == id) {
    			userReservations.add(r);
    		}
    	}
    	return userReservations;
    }
    
    @Transactional
    public List<Reservation> getReservations(String username) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Long id = getUserIdFromUsername(username);
    	List<Reservation> reservations = toList(reservationRepository.findAll());
    	List<Reservation> userReservations = new ArrayList<Reservation>();
    	for (Reservation r:reservations) {
    		if (r.getUser() != null && r.getUser().getUserId() == id) {
    			userReservations.add(r);
    		}
    	}
    	return userReservations;
    }
    
    @Transactional
    public boolean addItemToReservation(Long userId, Long reservationId, Long itemId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(userId);
    	if (onlineUser == null) {
    		throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
    	if (reservation == null) {
    		throw new IllegalArgumentException("Reservation does not exist.");
    	}
    	Album album = albumRepository.findAlbumByItemId(itemId);
    	if (album != null) {
    		return addItem(userId, reservationId, album);
    	}
    	Book book = bookRepository.findBookByItemId(itemId);
    	if (book != null) {
    		return addItem(userId, reservationId, book);
    	}
    	Movie movie = movieRepository.findMovieByItemId(itemId);
    	if (movie != null) {
    		return addItem(userId, reservationId, movie);
    	}
    	throw new IllegalArgumentException("Item does not exist");
    }
    
    @Transactional
    public boolean addItemToReservation(String username, Long reservationId, Long itemId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
    		throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
    	if (reservation == null) {
    		throw new IllegalArgumentException("Reservation does not exist.");
    	}
    	Long userId = getUserIdFromUsername(username);
    	Album album = albumRepository.findAlbumByItemId(itemId);
    	if (album != null) {
    		return addItem(userId, reservationId, album);
    	}
    	Book book = bookRepository.findBookByItemId(itemId);
    	if (book != null) {
    		return addItem(userId, reservationId, book);
    	}
    	Movie movie = movieRepository.findMovieByItemId(itemId);
    	if (movie != null) {
    		return addItem(userId, reservationId, movie);
    	}
    	throw new IllegalArgumentException("Item does not exist");
    }
    
    @Transactional
    public boolean addItem(Long userId, Long reservationId, Item item) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(userId);
    	if (onlineUser == null) {
    		throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
    	if (reservation == null) {
    		throw new IllegalArgumentException("Reservation does not exist.");
    	}
    	if (item == null) {
    		throw new IllegalArgumentException("Item does not exist.");
    	}
    	if (reservation.getUser() != null && reservation.getUser().getUserId() == userId) {
    		if (reservation.getItems() == null) {
    			reservation.setItems(new ArrayList<Item>());
    		}
    		for (Item i: reservation.getItems()) {
    			if (i.getItemId() == item.getItemId()) {
    				// Item is already in the reservation
    				return true;
    			}
    		}
    		reservation.addItem(item);
    		return true;
    	}
    	return false;
    }
    
    @Transactional
    public boolean cancelReservation(Long userId, Long reservationId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(userId);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
    	if (reservation == null) {
    		throw new IllegalArgumentException("Reservation does not exists.");
    	}
    	if (reservation.getUser() != null && reservation.getUser().getUserId() == userId) {
    		reservationRepository.delete(reservation);
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    @Transactional
    public boolean cancelReservation(String username, Long reservationId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Reservation reservation = reservationRepository.findReservationByReservationId(reservationId);
    	if (reservation == null) {
    		throw new IllegalArgumentException("Reservation does not exists.");
    	}
    	Long userId = getUserIdFromUsername(username);
    	if (reservation.getUser() != null && reservation.getUser().getUserId() == userId) {
    		reservationRepository.delete(reservation);
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    @Transactional
    public Event requestBooking(Long id, String name, boolean isPrivate, TimeSlot timeSlot) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
        if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
        }
        if (name == null || name.trim().length() == 0) {
        	throw new IllegalArgumentException("The name of an event cannot be empty.");
        }
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
        Event event = new Event();
        event.setName(name);
        event.setIsPrivate(isPrivate);
        event.setTimeSlot(timeSlot);
        event.setUser(onlineUser);
        event.setIsAccepted(false);
        eventRepository.save(event);
        return event;
    }
    
    @Transactional
    public Event requestBooking(String username, String name, boolean isPrivate, TimeSlot timeSlot) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
        if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
        }
        if (name == null || name.trim().length() == 0) {
        	throw new IllegalArgumentException("The name of an event cannot be empty.");
        }
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
        Event event = new Event();
        event.setName(name);
        event.setIsPrivate(isPrivate);
        event.setTimeSlot(timeSlot);
        event.setUser(onlineUser);
        event.setIsAccepted(false);
        eventRepository.save(event);
        return event;
    }
    
    @Transactional
    public List<Event> getRequests(Long id) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	List<Event> events = toList(eventRepository.findAll());
    	List<Event> requests = new ArrayList<Event>();
    	for (Event e:events) {
    		if (e.getUser() != null && e.getUser().getUserId() == id && !e.getIsAccepted()) {
    			requests.add(e);
    		}
    	}
    	return requests;
    }
    
    @Transactional
    public List<Event> getRequests(String username) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Long id = getUserIdFromUsername(username);
    	List<Event> events = toList(eventRepository.findAll());
    	List<Event> requests = new ArrayList<Event>();
    	for (Event e:events) {
    		if (e.getUser() != null && e.getUser().getUserId() == id && !e.getIsAccepted()) {
    			requests.add(e);
    		}
    	}
    	return requests;
    }
    
    @Transactional
    public List<Event> getEvents(Long id) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(id);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	List<Event> events = toList(eventRepository.findAll());
    	List<Event> userEvents = new ArrayList<Event>();
    	for (Event e:events) {
    		if (e.getUser() != null && e.getUser().getUserId() == id && e.getIsAccepted()) {
    			userEvents.add(e);
    		}
    	}
    	return userEvents;
    }
    
    @Transactional
    public List<Event> getEvents(String username) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Long id = getUserIdFromUsername(username);
    	List<Event> events = toList(eventRepository.findAll());
    	List<Event> userEvents = new ArrayList<Event>();
    	for (Event e:events) {
    		if (e.getUser() != null && e.getUser().getUserId() == id && e.getIsAccepted()) {
    			userEvents.add(e);
    		}
    	}
    	return userEvents;
    }
    
    @Transactional
    public boolean cancelEvent(Long userId, Long eventId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUserId(userId);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Event event = eventRepository.findEventByEventId(eventId);
    	if (event == null) {
    		throw new IllegalArgumentException("Reservation does not exists.");
    	}
    	if (event.getUser() != null && event.getUser().getUserId() == userId) {
    		eventRepository.delete(event);
    	}
    	return true;
    }
    
    @Transactional
    public boolean cancelEvent(String username, Long eventId) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	Event event = eventRepository.findEventByEventId(eventId);
    	if (event == null) {
    		throw new IllegalArgumentException("Reservation does not exists.");
    	}
    	Long userId = getUserIdFromUsername(username);
    	if (event.getUser() != null && event.getUser().getUserId() == userId) {
    		eventRepository.delete(event);
    	}
    	return true;
    }
    
    //this is method to loop and collect online users data, and also other data because its generic
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
    // Confirm that user email is correct in format: email@email.com
    private boolean isEmail(String email) {
    	String[] emailStrings = email.split("@");
    	return emailStrings.length == 2 && emailStrings[1].contains(".");
    }
    
    private Long getUserIdFromUsername(String username) {
    	OnlineUser onlineUser = onlineUserRepository.findOnlineUserByUsername(username);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Online user does not exist.");
    	}
    	return onlineUser.getUserId();
    }
}
