package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.model.OfflineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfflineUserService {

    @Autowired
    OfflineUserRepository offlineUserRepository;

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


    //this is method to loop and collect offline user data, and also other data because its generic
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
