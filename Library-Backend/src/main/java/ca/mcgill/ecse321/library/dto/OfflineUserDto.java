package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.Reservation;

import java.util.List;

public class OfflineUserDto extends UserDto{

    private Long userId;
    private List<Event> events;
    private List<Reservation> reservations;
    private LibraryApplicationSystem libraryApplicationSystem;

    public OfflineUserDto() {}
    
    public OfflineUserDto(String firstName, String lastName, String address, boolean isLocal, Long userId, List<Event> events, List<Reservation> reservations, LibraryApplicationSystem libraryApplicationSystem) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setIsLocal(isLocal);
        this.userId = userId;
        this.events = events;
        this.reservations = reservations;
        this.libraryApplicationSystem = libraryApplicationSystem;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public LibraryApplicationSystem getLibraryApplicationSystem() {
        return libraryApplicationSystem;
    }

    public void setLibraryApplicationSystem(LibraryApplicationSystem libraryApplicationSystem) {
        this.libraryApplicationSystem = libraryApplicationSystem;
    }
}
