package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.OfflineUser;

public interface OfflineUserRepository extends CrudRepository<OfflineUser, Long>{
	
	OfflineUser findOfflineUserByUserId(Long id);
	OfflineUser findOfflineUserByFirstNameAndAddress(String firstname, String address);
	OfflineUser findOfflineUserByFirstNameAndLastName(String firstname, String lastname);
}
