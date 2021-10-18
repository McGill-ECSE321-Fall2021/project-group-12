package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;

@Repository
public interface CreatorRepository extends CrudRepository<Creator, Long>{
	
	Creator findCreatorByCreatorId(Long creatorId);
	
}