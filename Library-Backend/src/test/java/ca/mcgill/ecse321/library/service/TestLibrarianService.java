package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Librarian;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
//	@Mock
//	private LibrarianRepository librarianDao;
//	
//	@InjectMocks
//	private LibrarianService service;
//	private static final Long LIBRARIAN_ID = 1L;
//	private static final String LIBRARIAN_FIRSTNAME = "First";
//	private static final String LIBRARIAN_LASTNAME = "Last";
//	
//	
//	@BeforeEach
//	public void setMoctOutput() {
//		lenient().when(librarianDao.findLibrarianByUserId(anyLong()))
//		.thenAnswer( (InvocationOnMock invocation) -> {
//			if (invocation.getArgument(0).equals(LIBRARIAN_ID)) {
//			Librarian librarian = new Librarian();
//			librarian.setUserId(LIBRARIAN_ID);
//			
//			return librarian;
//		} else {
//			return null;
//		}
//	});
//	}
	
}
