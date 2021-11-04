package ca.mcgill.ecse321.library.controller;


import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dto.OfflineUserDto;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.OfflineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class OfflineUserRestController {

    @Autowired
    private OfflineUserRepository offlineUserRepository;
    private OfflineUserService offlineUserService;

    //get all online users
    @GetMapping(value = {"/users", "/offlineUsers/"})
    public List<OfflineUserDto> getAllRestOfflineUsers() {
        return offlineUserService.getAllOfflineUsers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    //get one user by id
    @GetMapping(value = {"/users/offlineUsers/{id}", "/users/offlineUsers/{id}/"})
    public OfflineUserDto getOfflineUser(@PathVariable("id") Long id) {
        return convertToDto(offlineUserService.getOfflineUser(id));
    }

    //creating new user
    @PostMapping(value = {"/users/offlineUsers/create", "/users/offlineUsers/create/"})
    public OfflineUserDto createRestOfflineUser(@RequestParam("firstname") String firstname,
                                              @RequestParam("lastname") String lastname,
                                              @RequestParam("address") String address,
                                              @RequestParam("isLocal") boolean isLocal) {
        OfflineUser offlineUser = offlineUserService.createOfflineUser(firstname, lastname, address, isLocal);
        return convertToDto(offlineUser);
    }

    //updating exist user
    @PostMapping(value = {"/users/offlineUsers/update/{id}", "/users/offlineUsers/update/{id}/"})
    public OfflineUserDto updateRestOfflineUser(@PathVariable("id") long id,
                                              @RequestParam("firstname") String firstname,
                                              @RequestParam("lastname") String lastname,
                                              @RequestParam("address") String address,
                                              @RequestParam("isLocal") boolean isLocal) {

        return convertToDto(offlineUserService.updateOfflineUser(id,firstname,lastname,address,isLocal));
    }

    //deleting user by id
    @DeleteMapping(value = { "/users/offlineUsers/delete/{id}", "/users/offlineUsers/delete/{id}/"})
    public OfflineUserDto deleteRestOfflineUser(@PathVariable("id") Long id) throws IllegalArgumentException {
        OfflineUser offlineUser = offlineUserService.getOfflineUser(id);
        offlineUserService.deleteOfflineUser(id);
        return convertToDto(offlineUser);
    }

    //this is simple converter from dto to use object - somehow we use this for security
    private OfflineUserDto convertToDto(OfflineUser offlineUser) throws IllegalArgumentException {
        if (offlineUser == null) {
            throw new IllegalArgumentException("Offline user does not exist");
        }

        List<Event> eventList = offlineUser.getEvents();
        List<Reservation> reservationList = offlineUser.getReservations();
        LibraryApplicationSystem libraryApplicationSystem = offlineUser.getLibraryApplicationSystem();

        return new OfflineUserDto(
                offlineUser.getFirstName(),
                offlineUser.getLastName(),
                offlineUser.getAddress(),
                offlineUser.getIsLocal(),
                offlineUser.getUserId(),
                eventList,
                reservationList,
                libraryApplicationSystem);
    }
}
