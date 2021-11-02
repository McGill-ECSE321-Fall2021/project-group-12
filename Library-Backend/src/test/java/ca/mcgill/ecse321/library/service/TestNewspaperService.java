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

import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Newspaper;

@ExtendWith(MockitoExtension.class)
public class TestNewspaperService {
	
	@Mock
	private NewspaperRepository newspaperDao;
	
	@Mock 
	private CreatorRepository creatorRepository;
	
	@InjectMocks
	private NewspaperService newspaperService;
	
	private static final String TITLE = "Title";
	private static final boolean IS_ARCHIVE = false;
	private static final Date RELEASE_DATE = Date.valueOf("2021-10-31");
	
	private static final Long NEWSPAPER_ID = 1L;

	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(newspaperDao.findByItemId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NEWSPAPER_ID)) {
				Newspaper newspaper = new Newspaper();
				newspaper.setTitle(TITLE);
				newspaper.setIsArchive(IS_ARCHIVE);
				newspaper.setIsAvailable(false);
				newspaper.setIsReservable(false);
				newspaper.setReleaseDate(RELEASE_DATE);
				Creator creator = new Creator();
				creator.setFirstName("First");
				creator.setLastName("Last");
				creator.setCreatorType(CreatorType.Publisher);
				creator.setCreatorId(100L);
				newspaper.setCreator(creator);
				return newspaper;
			}else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(newspaperDao.save(any(Newspaper.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateNewspaper() {
		assertEquals(0, newspaperService.getAllNewspapers().size());
		
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
	}

	@Test
	public void testCreateNewspaperNullTitle() {
		String title = null;
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(newspaper);
		assertEquals("A newspaper cannot have an empty title.",error);
	}
	
	@Test
	public void testCreateNewspaperEmptyTitle() {
		String title = "";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(newspaper);
		assertEquals("A newspaper cannot have an empty title.",error);
	}
	
	@Test
	public void testCreateNewspaperSpacesTitle() {
		String title = "    ";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(newspaper);
		assertEquals("A newspaper cannot have an empty title.",error);
	}
	
	@Test
	public void testCreateNewspaperNullReleaseDate() {
		String title = "First";
		boolean isArchive = false;
		Date releaseDate = null;
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(newspaper);
		assertEquals("A newspaper cannot have an empty release date.",error);
	}
	
	@Test
	public void testCreateNewspaperNullCreator() {
		String title = "First";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		Creator creator = null;
		
		String error = "";
		
		Newspaper newspaper = null;
		
		try {
			newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(newspaper);
		assertEquals("A newspaper cannot have an empty creator.",error);
	}
	
	@Test
	public void testUpdateNewspaper() {
		assertEquals(0, newspaperService.getAllNewspapers().size());
		
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		newspaper.setItemId(NEWSPAPER_ID);
		
		String newTitle = "NewTitle";
		boolean newIsArchive = true;
		Date newReleaseDate = Date.valueOf("2021-11-01");
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Artist;
		Creator newCreator = new Creator();
		newCreator.setCreatorId(200L);
		newCreator.setCreatorType(newCreatorType);
		newCreator.setFirstName(newFirstName);
		newCreator.setLastName(newLastName);
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(newspaper);
		assertEquals(newTitle, newspaper.getTitle());
		assertEquals(newIsArchive, newspaper.getIsArchive());
		assertEquals(newReleaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(200L, newspaper.getCreator().getCreatorId());
	}
	
	@Test
	public void testUpdateNewspaperNullTitle() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		
		String newTitle = null;
		boolean newIsArchive = true;
		Date newReleaseDate = Date.valueOf("2021-11-01");
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Artist;
		Creator newCreator = new Creator();
		newCreator.setCreatorId(200L);
		newCreator.setCreatorType(newCreatorType);
		newCreator.setFirstName(newFirstName);
		newCreator.setLastName(newLastName);
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO UPDATES HAVE BEEN MADE
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
		
		assertEquals("A newspaper cannot have an empty title.", error);
	}
	
	@Test
	public void testUpdateNewspaperEmptyTitle() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		
		String newTitle = "";
		boolean newIsArchive = true;
		Date newReleaseDate = Date.valueOf("2021-11-01");
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Artist;
		Creator newCreator = new Creator();
		newCreator.setCreatorId(200L);
		newCreator.setCreatorType(newCreatorType);
		newCreator.setFirstName(newFirstName);
		newCreator.setLastName(newLastName);
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO UPDATES HAVE BEEN MADE
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
		
		assertEquals("A newspaper cannot have an empty title.", error);
	}
	
	@Test
	public void testUpdateNewspaperSpacesTitle() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		
		String newTitle = "    ";
		boolean newIsArchive = true;
		Date newReleaseDate = Date.valueOf("2021-11-01");
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Artist;
		Creator newCreator = new Creator();
		newCreator.setCreatorId(200L);
		newCreator.setCreatorType(newCreatorType);
		newCreator.setFirstName(newFirstName);
		newCreator.setLastName(newLastName);
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO UPDATES HAVE BEEN MADE
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
		
		assertEquals("A newspaper cannot have an empty title.", error);
	}
	
	@Test
	public void testUpdateNewspaperNullReleaseDate() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		
		String newTitle = "NewTitle";
		boolean newIsArchive = true;
		Date newReleaseDate = null;
		
		String newFirstName = "NewFirst";
		String newLastName = "NewLast";
		CreatorType newCreatorType = CreatorType.Artist;
		Creator newCreator = new Creator();
		newCreator.setCreatorId(200L);
		newCreator.setCreatorType(newCreatorType);
		newCreator.setFirstName(newFirstName);
		newCreator.setLastName(newLastName);
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO UPDATES HAVE BEEN MADE
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
		
		assertEquals("A newspaper cannot have an empty release date.", error);
	}
	
	@Test
	public void testUpdateNewspaperNullCreator() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		String error = "";
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		
		String newTitle = "NewTitle";
		boolean newIsArchive = true;
		Date newReleaseDate = Date.valueOf("2021-11-01");
		Creator newCreator = null;
		
		try {
			newspaper = newspaperService.updateNewspaper(NEWSPAPER_ID, newTitle, newIsArchive, newReleaseDate, newCreator);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// CONFIRM NO UPDATES HAVE BEEN MADE
		assertNotNull(newspaper);
		assertEquals(title, newspaper.getTitle());
		assertEquals(isArchive, newspaper.getIsArchive());
		assertEquals(releaseDate, newspaper.getReleaseDate());
		assertFalse(newspaper.getIsAvailable());
		assertFalse(newspaper.getIsReservable());
		assertNotNull(newspaper.getCreator());
		assertEquals(100L, newspaper.getCreator().getCreatorId());
		
		assertEquals("A newspaper cannot have an empty creator.", error);
	}
	
	@Test
	public void testDeleteNewspaper() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		try {
			newspaper = newspaperService.deleteNewspaper(NEWSPAPER_ID);
		} catch (Exception e) {
			fail();
		}
		
		// WE RETURN THE NEWSPAPER OBJECT BUT IT SHOULD NOT EXIST IN THE SYSTEM.
		assertNotNull(newspaper); 
		assertEquals(0, newspaperService.getAllNewspapers().size());
	}
	
	@Test
	public void testGetNewspaper() {
		String title = "Title";
		boolean isArchive = false;
		Date releaseDate = Date.valueOf("2021-10-31");
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = new Creator();
		creator.setCreatorId(100L);
		creator.setCreatorType(creatorType);
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		
		// TESTED CREATION ABOVE
		Newspaper newspaper = newspaperService.createNewspaper(title, isArchive, releaseDate, creator);
		assertNotNull(newspaper); // To confirm newspaper exists
		Newspaper newspaper2 = null;
		try {
			newspaper2 = newspaperService.getNewspaper(NEWSPAPER_ID);
		} catch (Exception e) {
			fail();
		}
		
		assertNotNull(newspaper2);
		assertEquals(newspaper.getTitle(), newspaper2.getTitle());
		assertEquals(newspaper.getIsArchive(), newspaper2.getIsArchive());
		assertEquals(newspaper.getIsAvailable(), newspaper2.getIsAvailable());
		assertEquals(newspaper.getIsReservable(), newspaper2.getIsReservable());
		assertEquals(newspaper.getItemId(), newspaper2.getItemId());
		assertEquals(newspaper.getReleaseDate(), newspaper2.getReleaseDate());
		assertNotNull(newspaper.getCreator());
		assertNotNull(newspaper2.getCreator());
		assertEquals(newspaper.getCreator().getCreatorId(), newspaper2.getCreator().getCreatorId());
	}

	
	
}
