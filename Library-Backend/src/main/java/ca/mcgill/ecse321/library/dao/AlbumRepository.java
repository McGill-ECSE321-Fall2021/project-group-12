package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.library.model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{

	Album findAlbumByItemId(Long itemId);
	
}
