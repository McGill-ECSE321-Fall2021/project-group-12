package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
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
}
