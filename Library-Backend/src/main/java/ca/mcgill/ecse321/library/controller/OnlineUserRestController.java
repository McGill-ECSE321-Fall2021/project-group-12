package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.dto.OnlineUserDto;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.LibraryApplicationSystem;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.service.OnlineUserService;
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
public class OnlineUserRestController {

    @Autowired
    private OnlineUserService onlineUserService;

    //get all online users
    @GetMapping(value = {"/users", "/onlineUsers/"})
    public List<OnlineUserDto> getAllRestOnlineUsers() {
        return onlineUserService.getAllOnlineUsers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    //get one user by id
    @GetMapping(value = {"/users/onlineusers/{id}", "/users/onlineusers/{id}/"})
    public OnlineUserDto getRestUser(@PathVariable("id") Long id) {
        return convertToDto(onlineUserService.getOnlineUser(id));
    }

//    @PostMapping(value = {"/user/onlineusers/create/reservation","/user/onlineusers/create/reservation"})
//    public OnlineUserDto createReservation(long userId, boolean isLocal, Reservation reservation){
//        OnlineUser onlineUser = onlineUserService.reserveItems(userId,isLocal,reservation);
//        return convertToDto(onlineUser);
//    }
//
//    @PostMapping(value = {"/user/onlineusers/update/password","/user/onlineusers/update/password/"})
//    public OnlineUserDto changePassword(long userId,String password){
//        OnlineUser onlineUser = onlineUserService.changePassword(userId,password);
//        return convertToDto(onlineUser);
//    }
//
//    @PostMapping(value = {"/user/onlineusers/remove/reservation","/user/onlineusers/remove/reservation/"})
//    public OnlineUserDto removeReservation(long userId,long reservationId){
//        OnlineUser onlineUser = onlineUserService.removeReservation(userId,reservationId);
//        return convertToDto(onlineUser);
//    }
//
//    @PostMapping(value = {"/user/onlineusers/create/event","/user/onlineusers/create/event"})
//    public OnlineUserDto createEvent(long userId, String name, boolean isPrivate,TimeSlot timeSlot){
//        OnlineUser onlineUser = onlineUserService.createEvent(userId,name,isPrivate, timeSlot);
//        return convertToDto(onlineUser);
//    }
//
//    @PostMapping(value = {"/user/onlineusers/remove/event","/user/onlineusers/remove/event"})
//    public OnlineUserDto createEvent(long userId, long eventId){
//        OnlineUser onlineUser = onlineUserService.removeEvent(userId,eventId);
//        return convertToDto(onlineUser);
//    }
//
//    //creating new user
//    @PostMapping(value = {"/users/onlineusers/create", "/users/onlineusers/create/"})
//    public OnlineUserDto createRestOnlineUser(@RequestParam("firstname") String firstname,
//                                              @RequestParam("lastname") String lastname,
//                                              @RequestParam("address") String address,
//                                              @RequestParam("isLocal") boolean isLocal,
//                                              @RequestParam("username") String username,
//                                              @RequestParam("password") String password,
//                                              @RequestParam("email") String email) {
//        OnlineUser onlineUser = onlineUserService.createOnlineUser(firstname, lastname, address, isLocal, username, password, email);
//        return convertToDto(onlineUser);
//    }
//
//    //online user login
//    @PostMapping(value = {"/user/onlineusers/login", "/user/onlineusers/login/"})
//    public OnlineUserDto login(@RequestParam("email") String email,
//                               @RequestParam("password") String password) {
//        OnlineUser onlineUser = onlineUserService.loginUser(email, password);
//        return convertToDto(onlineUser);
//    }

    //updating exist user
    @PostMapping(value = {"/users/onlineusers/update/{id}", "/users/onlineusers/update/{id}/"})
    public OnlineUserDto updateRestOnlineUser(@PathVariable("id") long id,
                                              @RequestParam("firstname") String firstname,
                                              @RequestParam("lastname") String lastname,
                                              @RequestParam("address") String address,
                                              @RequestParam("isLocal") boolean isLocal,
                                              @RequestParam("username") String username,
                                              @RequestParam("password") String password,
                                              @RequestParam("email") String email) {

        return convertToDto(onlineUserService.updateOnlineUser(id, firstname, lastname, address, isLocal, username, password, email));
    }

    //deleting user by id
    @DeleteMapping(value = {"/users/onlineusers/delete/{id}", "/users/onlineusers/delete/{id}/"})
    public OnlineUserDto deleteRestOnlineUser(@PathVariable("id") Long id) throws IllegalArgumentException {
        OnlineUser onlineUser = onlineUserService.getOnlineUser(id);
        onlineUserService.deleteOnlineUser(id);
        return convertToDto(onlineUser);
    }

    //this is simple converter from dto to use object - somehow we use this for security
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
