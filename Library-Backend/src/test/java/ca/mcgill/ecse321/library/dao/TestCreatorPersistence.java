package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestCreatorPersistence {
	@Autowired
	private CreatorRepository creatorRepository;

	@AfterEach
	public void clearDatabase() {
		creatorRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadCreator() {
		LibraryApplicationSystem system = new LibraryApplicationSystem();
		String firstName = "First";
		String lastName = "Last";
		CreatorType type = Creator.CreatorType.Author;
		Creator creator = new Creator();
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(type);
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
		assertNotNull(creator.getLibraryApplicationSystem());
	}
}
