package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
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

import ca.mcgill.ecse321.library.dao.NewspaperRepository;

@ExtendWith(MockitoExtension.class)
public class TestNewspaperService {
	
	@Mock
	private NewspaperRepository newspaperDao;
	
	@InjectMocks
	private NewspaperService newspaperService;
	
	private static final String TITLE = "NEWSPAPER TITLE";
	private static final boolean IS_ARCHIVE = false;
	private static final Date RELEASE_DATE = Date.valueOf("2021-10-31");
	
	


}
