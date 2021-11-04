package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.OnlineUserRepository;
import ca.mcgill.ecse321.library.model.OnlineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineUserService {

    @Autowired
    OnlineUserRepository onlineUserRepository;

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
        if (firstName != null) onlineUser.setFirstName(firstName);
        if (lastName != null) onlineUser.setLastName(lastName);
        if (address != null) onlineUser.setAddress(address);
        if (username != null) onlineUser.setUsername(username);
        if (password != null) onlineUser.setPassword(password);
        if (email != null) onlineUser.setEmail(email);
        onlineUser.setUserId(id);
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

    //this is method to loop and collect online users data, and also other data because its generic
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
