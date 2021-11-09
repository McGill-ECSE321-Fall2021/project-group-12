package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfflineUserService {

    @Autowired
    OfflineUserRepository offlineUserRepository;
    
    @Autowired
    ReservationRepository reservationRepository;
    
    @Autowired
    EventRepository eventRepository;
    
    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    AlbumRepository albumRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    NewspaperRepository newspaperRepository;
    
    @Autowired
    TimeSlotRepository timeSlotRepository;
    
    // librarian logs in offline user
    public OfflineUser login(Long id) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
    	if (offlineUser == null) {
    		throw new IllegalArgumentException("Offline user does not exist");
    	}
    	return offlineUser;
    }

    //This method getting all the offline user details and save it to the database
    @Transactional
    public OfflineUser createOfflineUser(String firstName,
                                        String lastName,
                                        String address,
                                        boolean isLocal) {
        OfflineUser offlineUser = new OfflineUser();
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("the first name cannot have an empty first name.");
        }
        if (lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("the last name cannot have an empty last name.");
        }
        if (address == null || address.trim().length() == 0) {
            throw new IllegalArgumentException("the address cannot have an empty address.");
        }

        offlineUser.setFirstName(firstName);
        offlineUser.setLastName(lastName);
        offlineUser.setAddress(address);
        offlineUser.setIsLocal(isLocal);

        offlineUserRepository.save(offlineUser);
        return offlineUser;
    }

    //this method will get offline user details and update it in the database
    @Transactional
    public OfflineUser updateOfflineUser(Long id,
                                       String firstName,
                                       String lastName,
                                       String address,
                                       boolean isLocal) throws IllegalArgumentException {
        OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
        if (firstName != null) offlineUser.setFirstName(firstName);
        if (lastName != null) offlineUser.setLastName(lastName);
        if (address != null) offlineUser.setAddress(address);

        offlineUser.setUserId(id);
        offlineUser.setFirstName(firstName);
        offlineUser.setLastName(lastName);
        offlineUser.setIsLocal(isLocal);

        offlineUserRepository.save(offlineUser);
        return offlineUser;
    }

    // will show all offline users from database
    @Transactional
    public List<OfflineUser> getAllOfflineUsers() {
        return toList(offlineUserRepository.findAll());
    }

    // will get 1 offline user by id
    @Transactional
    public OfflineUser getOfflineUser(Long id) throws IllegalArgumentException {
        OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
        if (offlineUser == null) {
            throw new IllegalArgumentException("Offline user does not exist.");
        }
        return offlineUser;
    }

    // delete user from database by the id
    @Transactional
    public boolean deleteOfflineUser(Long id) {
        OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
        if (offlineUser == null) {
            throw new IllegalArgumentException("Offline user does not exist.");
        }
        offlineUserRepository.delete(offlineUser);
        return true;
    }
    
    @Transactional
    public Reservation reserveItems(Long id, List<Long> itemIds, Long timeSlotId) {
        OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
        if (offlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
        }
        List<Item> items = getItemsFromItemIds(itemIds);
        if (items == null || items.size() == 0) {
        	throw new IllegalArgumentException("Items cannot be empty.");
        }
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
    	Reservation reservation = new Reservation();
    	reservation.setUser(offlineUser);
    	reservation.setItems(items);
    	reservation.setTimeSlot(timeSlot);
    	reservationRepository.save(reservation);
    	return reservation;
    }
    
    @Transactional
    public List<Reservation> getReservations(Long id) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
    	if (offlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
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
    public boolean addItemToReservation(Long userId, Long reservationId, Long itemId) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(userId);
    	if (offlineUser == null) {
    		throw new IllegalArgumentException("Offline user does not exist.");
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
    
    public boolean addItem(Long userId, Long reservationId, Item item) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(userId);
    	if (offlineUser == null) {
    		throw new IllegalArgumentException("Offline user does not exist.");
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
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(userId);
    	if (offlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
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
    public Event requestBooking(Long id, String name, boolean isPrivate, Long timeSlotId) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
        if (offlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
        }
        if (name == null || name.trim().length() == 0) {
        	throw new IllegalArgumentException("The name of an event cannot be empty.");
        }
        
        TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
        
        if (timeSlot == null) {
        	throw new IllegalArgumentException("TimeSlot cannot be empry.");
        }
        Event event = new Event();
        event.setName(name);
        event.setIsPrivate(isPrivate);
        event.setTimeSlot(timeSlot);
        event.setUser(offlineUser);
        event.setIsAccepted(false);
        eventRepository.save(event);
        return event;
    }
    
    @Transactional
    public List<Event> getRequests(Long id) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(id);
    	if (offlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
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
    public List<Event> getEvents(Long id) {
    	OfflineUser onlineUser = offlineUserRepository.findOfflineUserByUserId(id);
    	if (onlineUser == null) {
        	throw new IllegalArgumentException("Offline user does not exist.");
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
    public boolean cancelEvent(Long userId, Long eventId) {
    	OfflineUser offlineUser = offlineUserRepository.findOfflineUserByUserId(userId);
    	if (offlineUser == null) {
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
    
    //this is method to loop and collect offline user data, and also other data because its generic
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
    
    private List<Item> getItemsFromItemIds(List<Long> itemIds) throws IllegalArgumentException {
    	if (itemIds == null) {
    		throw new IllegalArgumentException("List of item ids cannot be null");
    	}
    	List<Item> items = new ArrayList<Item>();
    	for (Long itemId:itemIds) {
    		if (albumRepository.existsById(itemId)) {
    			Album album = albumRepository.findAlbumByItemId(itemId);
    			items.add(album);
    		} else if(bookRepository.existsById(itemId)) {
    			Book book = bookRepository.findBookByItemId(itemId);
    			items.add(book);
    		} else if (movieRepository.existsById(itemId)) {
    			Movie movie = movieRepository.findMovieByItemId(itemId);
    			items.add(movie);
    		} else if (newspaperRepository.existsById(itemId)) {
    			Newspaper newspaper = newspaperRepository.findByItemId(itemId);
    			items.add(newspaper);
    		} else {
    			throw new IllegalArgumentException("Invalid item id");
    		}
    	}
    	return items;
    }

}


