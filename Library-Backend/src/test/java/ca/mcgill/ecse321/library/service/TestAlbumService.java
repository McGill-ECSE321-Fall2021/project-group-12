package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(MockitoExtension.class)
public class TestAlbumService {
	
	@Mock
	private AlbumRepository albumDao;
	
	@Mock 
	private CreatorRepository creatorRepository;
	
	@InjectMocks
	private AlbumService albumService;
	
	private static final String TITLE = "Title";
	private static final boolean IS_ARCHIVE = false;
	private static final boolean IS_RESERVABLE = true;
	private static final Date RELEASE_DATE = Date.valueOf("2021-10-31");
	private static final int NUM_SONGS = 15;
	private static final boolean IS_AVAILABLE = true;
	private static final MusicGenre GENRE = MusicGenre.Rock;

	private static final Long ALBUM_ID = 1L;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(albumDao.findAlbumByItemId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ALBUM_ID)) {
				Album album = new Album();
				album.setTitle(TITLE);
				album.setIsArchive(IS_ARCHIVE);
				album.setIsReservable(IS_RESERVABLE);
				album.setReleaseDate(RELEASE_DATE);
				album.setNumSongs(NUM_SONGS);
				album.setIsAvailable(IS_AVAILABLE);
				album.setGenre(GENRE);
				Creator creator = new Creator();
				creator.setFirstName("First");
				creator.setLastName("Last");
				creator.setCreatorType(CreatorType.Author);
				creator.setCreatorId(100L);
				album.setCreator(creator);
				return album;
			}else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(albumDao.save(any(Album.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateAlbum() {
		assertEquals(0, albumService.getAllAlbums().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(album);
		assertEquals(title, album.getTitle());
		assertEquals(isArchive, album.getIsArchive());
		assertEquals(isReservable, album.getIsReservable());
		assertEquals(releaseDate, album.getReleaseDate());
		assertEquals(numSongs, album.getNumSongs());
		assertEquals(isAvailable, album.getIsAvailable());
		assertEquals(genre, album.getGenre());
		assertNotNull(album.getCreator());
		assertEquals(100L, album.getCreator().getCreatorId());
	}
	
	@Test
	public void testCreateAlbumNullTitle() {

		String title = null;
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(album);
		assertEquals("Cannot create album with empty title.", error);
	}
	
	@Test
	public void testCreateAlbumEmptyTitle() {

		String title = "";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(album);
		assertEquals("Cannot create album with empty title.", error);
	}
	
	@Test
	public void testCreateAlbumSpacesTitle() {

		String title = "       ";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(album);
		assertEquals("Cannot create album with empty title.", error);
	}
	
	@Test
	public void testCreateAlbumNullDate() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = null;
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(album);
		assertEquals("Cannot create album with empty date.", error);
	}
	
	@Test
	public void testCreateAlbumNullCreator() {

		String title = "title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		Creator creator = null;
		
		String error = "";
		
		Album album = null;
		
		try {
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(album);
		assertEquals("An album cannot have an empty creator.", error);
	}
	
	@Test
	public void testUpdateAlbum() {
		assertEquals(0, albumService.getAllAlbums().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Album album = null;
		
		try {	
			isArchive = false;
			isReservable = false;
			isAvailable = false;
			
			album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
			album = albumService.updateAlbum(ALBUM_ID, isArchive, isReservable, isAvailable);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(album);
		assertEquals(title, album.getTitle());
		assertEquals(isArchive, album.getIsArchive());
		assertEquals(isReservable, album.getIsReservable());
		assertEquals(releaseDate, album.getReleaseDate());
		assertEquals(numSongs, album.getNumSongs());
		assertEquals(isAvailable, album.getIsAvailable());
		assertEquals(genre, album.getGenre());
		assertNotNull(album.getCreator());
		assertEquals(100L, album.getCreator().getCreatorId());
	}
	
	@Test
	public void testGetAlbum() {
		assertEquals(0, albumService.getAllAlbums().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Album album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		assertNotNull(album); 
		Album album2 = null;
		
		try {	
			album2 = albumService.getAlbum(ALBUM_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(album2);
		assertEquals(title, album2.getTitle());
		assertEquals(isArchive, album2.getIsArchive());
		assertEquals(isReservable, album2.getIsReservable());
		assertEquals(releaseDate, album2.getReleaseDate());
		assertEquals(numSongs, album2.getNumSongs());
		assertEquals(isAvailable, album2.getIsAvailable());
		assertEquals(genre, album2.getGenre());
		assertNotNull(album2.getCreator());
		assertEquals(100L, album2.getCreator().getCreatorId());
	}
	
	@Test
	public void testDeleteAlbum() {
		assertEquals(0, albumService.getAllAlbums().size());
		
		String title = "Title";
		boolean isArchive = false;
		boolean isReservable = true;
		Date releaseDate = Date.valueOf("2021-10-31");
		int numSongs = 15;
		boolean isAvailable = true;
		MusicGenre genre = MusicGenre.Rock;
		
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Album album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, isAvailable, genre, creator);
		assertNotNull(album); 
		
		try {	
			album = albumService.deleteAlbum(ALBUM_ID);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(0, albumService.getAllAlbums().size());		
	}

}
