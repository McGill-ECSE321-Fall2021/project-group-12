package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.LibraryApplication;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestCreatorPersistence {
	@Autowired
	private CreatorRepository creatorRepository;
	
	
	/* *** I added this because it is necessary to construct certain objects 
	 * (e.g. Creator) but there was nothing like it in the tutorial so this may be incorrect. ***
	 */
	private LibraryApplicationSystem system = new LibraryApplicationSystem();

	@AfterEach
	public void clearDatabase() {
		// Ordering matters here. Be careful.
		creatorRepository.deleteAll();
		// make new system.
		system = new LibraryApplicationSystem();
	}

	@Test
	public void testPersistAndLoadCreator() {
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		Creator creator = new Creator();
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(Creator.CreatorType.Author);
		creator.setLibraryApplicationSystem(system);
		creatorRepository.save(creator);
		Long id = creator.getCreatorId();
		
		creator = null;
		
		
		creator = creatorRepository.findCreatorByCreatorId(id);
		
		assertNotNull(creator);
		assertEquals(id, creator.getCreatorId());
		assertEquals(firstName, creator.getFirstName());
		assertEquals(lastName, creator.getLastName());
		assertEquals(type.toString(), creator.getCreatorType().toString());
		assertEquals(system, creator.getLibraryApplicationSystem());
	}
}
