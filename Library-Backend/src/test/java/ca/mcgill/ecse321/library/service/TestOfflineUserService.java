package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.model.OfflineUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestOfflineUserService {

    private static final Long OFFLINE_USER_ID = 1L;
    private static final String OFFLINE_USER_FIRST_NAME = "Test firstname";
    private static final String OFFLINE_USER_LAST_NAME = "Test lastname";
    private static final String OFFLINE_USER_ADDRESS = "Test address";

    @Mock
    private OfflineUserRepository offlineUserRepository;

    @InjectMocks
    private OfflineUserService offlineUserService;

    @BeforeEach
    public void setMockOfflineUserOutput() {
        lenient().when(offlineUserRepository.findOfflineUserByUserId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(OFFLINE_USER_ID)) {
                        OfflineUser offlineUser = new OfflineUser();
                        offlineUser.setFirstName(OFFLINE_USER_FIRST_NAME);
                        offlineUser.setLastName(OFFLINE_USER_LAST_NAME);
                        offlineUser.setAddress(OFFLINE_USER_ADDRESS);
                        offlineUser.setIsLocal(true);
                        return offlineUser;
                    } else {
                        return null;
                    }
                });

        Answer<?> returningAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(offlineUserRepository.save(any(OfflineUser.class))).thenAnswer(returningAnswer);
    }

    @Test
    public void testCreatingOfflineUser() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;

        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        try {
            testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(testOfflineUser);
        assertEquals(firstName, testOfflineUser.getFirstName());
        assertEquals(lastName, testOfflineUser.getLastName());
        assertEquals(isLocal, testOfflineUser.getIsLocal());
        assertEquals(address, testOfflineUser.getAddress());

    }

    @Test
    public void testUpdateOfflineUser() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineeUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;

        testOfflineeUser.setFirstName(firstName);
        testOfflineeUser.setLastName(lastName);
        testOfflineeUser.setAddress(address);
        testOfflineeUser.setIsLocal(isLocal);

        testOfflineeUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        testOfflineeUser.setUserId(OFFLINE_USER_ID);
        firstName = "testFirstName1";
        lastName = "testLastName1";
        address = "testAddress1";
        isLocal = false;

        try {
            testOfflineeUser = offlineUserService.updateOfflineUser(OFFLINE_USER_ID, firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(testOfflineeUser);
        assertEquals(firstName, testOfflineeUser.getFirstName());
        assertEquals(lastName, testOfflineeUser.getLastName());
        assertEquals(isLocal, testOfflineeUser.getIsLocal());
        assertEquals(address, testOfflineeUser.getAddress());

    }

    @Test
    public void testDeleteOfflineUser() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;

        testOfflineUser.setUserId(OFFLINE_USER_ID);
        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        assertNotNull(testOfflineUser);

        boolean isDeleted = offlineUserService.deleteOfflineUser(OFFLINE_USER_ID);

        assertTrue(isDeleted);

    }
}
