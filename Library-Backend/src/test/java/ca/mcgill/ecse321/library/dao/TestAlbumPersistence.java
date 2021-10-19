package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAlbumPersistence {
	
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private CreatorRepository creatorRepository;

	@AfterEach
	public void clearDatabase() {
		
		albumRepository.deleteAll();
		creatorRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadAlbum() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = new Date(10-18-2021);
		int quantityAvailable = 5;
		int quantity = 12;
		int numSongs = 15;
		MusicGenre genre = Album.MusicGenre.Jazz;
		Creator creator = new Creator();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
		creator.setLibraryApplicationSystem(system);
		creatorRepository.save(creator);
		
		
		Album album = new Album();
		
		album.setTitle(title);
		album.setIsArchive(isArchive);
		album.setIsReservable(isReservable);
		album.setReleaseDate(releaseDate);
		album.setQuantityAvailable(quantityAvailable);
		album.setQuantity(quantity);
		album.setNumSongs(numSongs);
		album.setCreator(creator);
		album.setGenre(genre);
		
		album.setLibraryApplicationSystem(system);
	
		albumRepository.save(album);
		Long id = album.getItemId();
		
		album = null;
		
		
		album = albumRepository.findAlbumByItemId(id);
		
		assertNotNull(album);
		assertEquals(id, album.getItemId());
		
		
		assertEquals(title, album.getTitle());
		assertEquals(isArchive, album.getIsArchive());
		assertEquals(isReservable,album.getIsReservable());
		assertNotNull(album.getReleaseDate());
		assertEquals(quantityAvailable, album.getQuantityAvailable());
		assertEquals(quantity, album.getQuantity());
		assertEquals(numSongs, album.getNumSongs());
		assertNotNull(album.getCreator());
		assertEquals(genre, album.getGenre());
		assertNotNull(album.getLibraryApplicationSystem());
	}

}
