package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(MockitoExtension.class)
public class TestCreatorService {
	
	@Mock
	private CreatorRepository creatorDao;
	
	@InjectMocks
	private CreatorService service;
	
	private static final String FIRST_NAME = "First";
	private static final String LAST_NAME = "Last";
	private static final CreatorType CREATOR_TYPE = CreatorType.Artist;
	
	private static final String CREATOR_KEY = LAST_NAME + FIRST_NAME + CREATOR_TYPE;
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(creatorDao.findCreatorByCreatorName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CREATOR_KEY)) {
				Creator creator = new Creator();
				creator.setCreatorName(FIRST_NAME, LAST_NAME, CREATOR_TYPE);
				return creator;
			}else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(creatorDao.save(any(Creator.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateCreator() {
		assertEquals(0, service.getAllCreators().size());
		
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Artist;
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(creator);
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(creatorType.toString(), creator.getCreatorType().toString());
		assertEquals(CREATOR_KEY, creator.getCreatorName());
	}
	
	@Test
	public void  testCreateCreatorNullFirstName() {
		String firstName = null;
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Director;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name or type.", error);
	}
	
	@Test
	public void  testCreateCreatorNullLastName() {
		String firstName = "First";
		String lastName = null;
		CreatorType creatorType = CreatorType.Director;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name or type.", error);
	}
	
	@Test
	public void  testCreateCreatorNullCreatorType() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType creatorType = null;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name or type.", error);
	}
	
	@Test
	public void  testCreateCreatorEmptyFirstName() {
		String firstName = "";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name.", error);
	}
	
	@Test
	public void  testCreateCreatorEmptyLastName() {
		String firstName = "First";
		String lastName = "";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name.", error);
	}
	
	public void  testCreateCreatorSpacesFirstName() {
		String firstName = " ";
		String lastName = "Last";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name.", error);
	}
	
	public void  testCreateCreatorSpacesLastName() {
		String firstName = "First";
		String lastName = " ";
		CreatorType creatorType = CreatorType.Author;
		String error = "";
		Creator creator = null;
		try {
			creator = service.createCreator(firstName, lastName, creatorType);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(creator);
		assertEquals("Cannot create creator with empty name.", error);
	}
	
}
