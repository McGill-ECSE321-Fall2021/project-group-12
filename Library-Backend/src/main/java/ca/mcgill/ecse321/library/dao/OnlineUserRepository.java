package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.OnlineUser;

public interface OnlineUserRepository extends CrudRepository<OnlineUser, Long>{
	
	OnlineUser findOnlineUserByUserId(Long id);
	OnlineUser findOnlineUserByFirstNameAndLastName(String firstname, String lastname);
	OnlineUser findOnlineUserByUsername(String username);
	OnlineUser findOnlineUserByEmail(String email);
}
