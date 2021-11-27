package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestOnlineUserService {

    private static final Long ONLINE_USER_ID = 1L;
    private static final String ONLINE_USER_FIRST_NAME = "Test firstname";
    private static final String ONLINE_USER_LAST_NAME = "Test lastname";
    private static final String ONLINE_USER_ADDRESS = "Test address";
    private static final String ONLINE_USER_USERNAME = "testUsername";
    private static final String ONLINE_USER_PASSWORD = "testPassword!";
    private static final String ONLINE_USER_EMAIL = "Test@Test.com";

    private static final Long ITEM_ID = 222L;
    private static final Long TIMESLOT_ID = 333L;
    private static final Long RESERVATION_ID = 444L;
    private static final Long EVENT_ID = 555L;

    @Mock
    private OnlineUserRepository onlineUserRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private NewspaperRepository newspaperRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private OnlineUserService onlineUserService;

    @BeforeEach
    public void setMockOnlineUserOutput() {

        lenient().when(onlineUserRepository.findOnlineUserByUserId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(ONLINE_USER_ID)) {
                        OnlineUser onlineUser = new OnlineUser();
                        onlineUser.setUserId(ONLINE_USER_ID);
                        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
                        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
                        onlineUser.setAddress(ONLINE_USER_ADDRESS);
                        onlineUser.setIsLocal(true);
                        onlineUser.setUsername(ONLINE_USER_USERNAME);
                        onlineUser.setPassword(ONLINE_USER_PASSWORD);
                        onlineUser.setEmail(ONLINE_USER_EMAIL);
                        return onlineUser;
                    } else {
                        return null;
                    }
                });

        lenient().when(onlineUserRepository.findOnlineUserByUsername(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                     if(invocation.getArgument(0).equals(ONLINE_USER_USERNAME)) {
                         OnlineUser onlineUser = new OnlineUser();
                         onlineUser.setUserId(ONLINE_USER_ID);
                         onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
                         onlineUser.setLastName(ONLINE_USER_LAST_NAME);
                         onlineUser.setAddress(ONLINE_USER_ADDRESS);
                         onlineUser.setIsLocal(true);
                         onlineUser.setUsername(ONLINE_USER_USERNAME);
                         onlineUser.setPassword(ONLINE_USER_PASSWORD);
                         onlineUser.setEmail(ONLINE_USER_EMAIL);
                         return onlineUser;
                     } else {
                         return null;
                     }
        });

        lenient().when(onlineUserRepository.findOnlineUserByEmail(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ONLINE_USER_EMAIL)) {
                        OnlineUser onlineUser = new OnlineUser();
                        onlineUser.setUserId(ONLINE_USER_ID);
                        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
                        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
                        onlineUser.setAddress(ONLINE_USER_ADDRESS);
                        onlineUser.setIsLocal(true);
                        onlineUser.setUsername(ONLINE_USER_USERNAME);
                        onlineUser.setPassword(ONLINE_USER_PASSWORD);
                        onlineUser.setEmail(ONLINE_USER_EMAIL);
                        return onlineUser;
                    } else {
                        return null;
                    }
                });

        lenient().when(albumRepository.findAlbumByItemId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        Album album = new Album();
                        album.setNumSongs(1);
                        album.setIsReservable(true);
                        album.setIsAvailable(true);
                        album.setIsArchive(false);
                        album.setGenre(Album.MusicGenre.Classical);
                        album.setItemId(ITEM_ID);
                        return album;
                    }
                    else {
                        return null;
                    }
                });

        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                   if(invocation.getArgument(0).equals(TIMESLOT_ID)) {
                       TimeSlot timeSlot = new TimeSlot();
                       timeSlot.setTimeSlotId(TIMESLOT_ID);

                       return timeSlot;
                   } else {
                       return null;
                   }
                });

        lenient().when(reservationRepository.findReservationByReservationId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                   if(invocation.getArgument(0).equals(RESERVATION_ID)) {

                       OnlineUser onlineUser = new OnlineUser();
                       onlineUser.setUserId(ONLINE_USER_ID);
                       onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
                       onlineUser.setLastName(ONLINE_USER_LAST_NAME);
                       onlineUser.setAddress(ONLINE_USER_ADDRESS);
                       onlineUser.setIsLocal(true);
                       onlineUser.setUsername(ONLINE_USER_USERNAME);
                       onlineUser.setPassword(ONLINE_USER_PASSWORD);
                       onlineUser.setEmail(ONLINE_USER_EMAIL);

                       Reservation reservation = new Reservation();
                       reservation.setReservationId(RESERVATION_ID);
                       reservation.setUser(onlineUser);
                       return reservation;
                   } else {
                       return null;
                   }
                });

        lenient().when(eventRepository.findEventByEventId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                   if(invocation.getArgument(0).equals(EVENT_ID)) {

                       OnlineUser onlineUser = new OnlineUser();
                       onlineUser.setUserId(ONLINE_USER_ID);
                       onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
                       onlineUser.setLastName(ONLINE_USER_LAST_NAME);
                       onlineUser.setAddress(ONLINE_USER_ADDRESS);
                       onlineUser.setIsLocal(true);
                       onlineUser.setUsername(ONLINE_USER_USERNAME);
                       onlineUser.setPassword(ONLINE_USER_PASSWORD);
                       onlineUser.setEmail(ONLINE_USER_EMAIL);

                       Event event = new Event();
                       event.setEventId(EVENT_ID);
                       event.setUser(onlineUser);
                       event.setIsAccepted(true);

                       return event;
                   } else {
                       return null;
                   }
                });

        Answer<?> returningAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        Answer<?> returningAnswerBoolean = (InvocationOnMock invocation) -> {
            return true;
        };

        lenient().when(onlineUserRepository.save(any(OnlineUser.class))).thenAnswer(returningAnswer);
        lenient().when(reservationRepository.save(any(Reservation.class))).thenAnswer(returningAnswer);

        lenient().when(albumRepository.existsById(anyLong())).thenAnswer(returningAnswerBoolean);
        lenient().when(bookRepository.existsById(anyLong())).thenAnswer(returningAnswerBoolean);
        lenient().when(movieRepository.existsById(anyLong())).thenAnswer(returningAnswerBoolean);
        lenient().when(newspaperRepository.existsById(anyLong())).thenAnswer(returningAnswerBoolean);

    }

    @Test
    public void testLoginWithUserException() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user;
        String exception = "";
        try {
            user = onlineUserService.login(null, ONLINE_USER_PASSWORD);
        } catch(IllegalArgumentException e) {
            user = null;
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void testLoginWithPasswordException() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user;
        String exception = "";

        try {
            user = onlineUserService.login(ONLINE_USER_USERNAME, "Wrong password");
        } catch(IllegalArgumentException e) {
            user = null;
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Incorrect password.", exception);
    }

    @Test
    public void testLogin() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user;
        String exception = "";

        try {
            user = onlineUserService.login(ONLINE_USER_USERNAME, ONLINE_USER_PASSWORD);
        } catch(IllegalArgumentException e) {
            user = null;
            exception = e.getMessage();
        }

        assertNotNull(user);
        assertEquals(user.getUserId(), ONLINE_USER_ID);
        assertEquals(user.getUsername(), ONLINE_USER_USERNAME);
        assertEquals(user.getEmail(), ONLINE_USER_EMAIL);
        assertEquals(user.getPassword(), ONLINE_USER_PASSWORD);
        assertEquals(user.getAddress(), ONLINE_USER_ADDRESS);
        assertEquals(user.getIsLocal(), true);
        assertEquals("", exception);
    }

    @Test
    public void testCreatingOnlineUserWithNullLastName() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String firstName = "testFirstName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword";
        String email = "test@testemail.com";

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.createOnlineUser(firstName, null, address, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("An online user cannot have an empty last name.", exception);

    }

    @Test
    public void testCreatingOnlineUserWithNullAddress() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String firstName = "testFirstName";
        String lastName = "testLastName";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword";
        String email = "test@testemail.com";

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.createOnlineUser(firstName, lastName, null, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("An online user cannot have an empty address.", exception);
    }

    @Test
    public void testCreatingOnlineUserWithNullUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String password = "testPassword";
        String email = "test@testemail.com";

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.createOnlineUser(firstName, lastName, address, isLocal, null, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("An online user cannot have an empty username.", exception);
    }

    @Test
    public void testCreatingOnlineUserWithEmptyPassword() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        String userName = "testUsername";
        boolean isLocal = true;
        String email = "test@testemail.com";

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.createOnlineUser(firstName, lastName, address, isLocal, userName, "", email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("An online user cannot have an empty password.", exception);
    }

    @Test
    public void testCreatingOnlineUserWithNullEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        String userName = "testUsername";
        boolean isLocal = true;
        String password = "testPassword";
        String email = null;

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.createOnlineUser(firstName, lastName, address, isLocal, userName, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Password does not contain a special character letter. Passwords must contain at least 1 upper case letter and one of the following special characters: '!', '#', '$', '%', '&', '*', '+', '-', '=', '?', '@', '^', '_'.", exception);
    }

    @Test
    public void testCreatingOnlineUserWithDuplicateUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());
        OnlineUser testOnlineUser = new OnlineUser();

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername(username);
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll()).thenReturn(userList);

        OnlineUser actualOnlineUser = null;
        String exception = "";

        try {
            actualOnlineUser = onlineUserService.createOnlineUser(firstName, lastName, address, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(actualOnlineUser);
        assertEquals("A user with that username already exists.", exception);
    }


    @Test
    public void testCreatingOnlineUser() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());
        OnlineUser testOnlineUser = new OnlineUser();

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String uniqueUsername = "UniqueUsername";
        String password = "testPassword!";
        String uniqueEmail = "uniqueEmail@testemail.com";

        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername(uniqueUsername);
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(uniqueEmail);

        List<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        String email = "test@testemail.com";
        String username = "testUsername";
        OnlineUser actualOnlineUser = null;

        try {
            actualOnlineUser = onlineUserService.createOnlineUser(firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            testOnlineUser = null;
        }

        assertNotNull(actualOnlineUser);
        assertEquals(firstName, actualOnlineUser.getFirstName());
        assertEquals(lastName, actualOnlineUser.getLastName());
        assertEquals(isLocal, actualOnlineUser.getIsLocal());
        assertEquals(address, actualOnlineUser.getAddress());
        assertEquals(username, actualOnlineUser.getUsername());
        assertEquals(password, actualOnlineUser.getPassword());
        assertEquals(email, actualOnlineUser.getEmail());

    }

    @Test
    public void testChangeUsernameWithOldUsernameException() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.changeUsername(null, ONLINE_USER_PASSWORD, "newUsername");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void testChangeUsernameWithDuplicateUsernameException() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser testOnlineUser = new OnlineUser();

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername(username);
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.changeUsername(username, password, username);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("A user with that username already exists.", exception);
    }

    @Test
    public void testChangeUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser testOnlineUser = new OnlineUser();

        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername("someUsername");
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.changeUsername(username, password, "newUsername");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNotNull(user);
        assertEquals("", exception);
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testChangePassword() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());
        String username = "testUsername";
        String password = "testPassword!";

        OnlineUser testOnlineUser = new OnlineUser();
        String newPassword = "newPassword!";
        testOnlineUser.setUsername(username);
        testOnlineUser.setPassword(password);


        try{
            testOnlineUser = onlineUserService.changePassword(username, password, newPassword);
        }catch (IllegalArgumentException e){
            fail();
        }

        assertNotNull(testOnlineUser);
        assertEquals(newPassword,testOnlineUser.getPassword());
    }

    @Test
    public void testUpdateOnlineUserWithNullFirstName() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;

        Long userId = 1L;
        String firstName = null;
        String lastName = "Test lastname";
        String address = "Test address";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "Test@test.com";

        String exception = "";

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("the first name cannot have an empty first name.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithNullAddress() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;

        Long userId = 1L;
        String firstName = "Test firstname";
        String lastName = "Test lastname";
        String address = null;
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "Test@test.com";

        String exception = "";

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("the address cannot have an empty address.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithNullUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;

        Long userId = 1L;
        String firstName = "Test firstname";
        String lastName = "Test lastname";
        String address = "Test address";
        boolean isLocal = true;
        String username = null;
        String password = "testPassword!";
        String email = "Test@test.com";

        String exception = "";

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("the username cannot have an empty username.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithNullPassword() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;

        Long userId = 1L;
        String firstName = "Test firstname";
        String lastName = "Test lastname";
        String address = "Test address";
        boolean isLocal = true;
        String username = "testUsername";
        String password = null;
        String email = "Test@test.com";

        String exception = "";

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("the password cannot have an empty password.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithNullEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser user = null;

        Long userId = 1L;
        String firstName = "Test firstname";
        String lastName = "Test lastname";
        String address = "Test address";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = null;

        String exception = "";

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("the email cannot have an empty email.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithDuplicateUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser testOnlineUser = new OnlineUser();

        Long userId = 1L;
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername(username);
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        String exception = "";

        OnlineUser user = null;

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("A user with that username already exists.", exception);
    }

    @Test
    public void testUpdateOnlineUserWithDuplicateEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser testOnlineUser = new OnlineUser();

        Long userId = 1L;
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setUserId(2L);
        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername("uniqueUsername");
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        String exception = "";

        OnlineUser user = null;

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("A user with that email already exists.", exception);
    }


    @Test
    public void testUpdateOnlineUser() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser testOnlineUser = new OnlineUser();

        Long userId = 1L;
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String username = "testUsername";
        String password = "testPassword!";
        String email = "test@testemail.com";

        testOnlineUser.setUserId(1L);
        testOnlineUser.setFirstName(firstName);
        testOnlineUser.setLastName(lastName);
        testOnlineUser.setAddress(address);
        testOnlineUser.setIsLocal(isLocal);
        testOnlineUser.setUsername("testUsername");
        testOnlineUser.setPassword(password);
        testOnlineUser.setEmail(email);

        ArrayList<OnlineUser> userList = new ArrayList<>();
        userList.add(testOnlineUser);

        lenient().when(onlineUserRepository.findAll())
                .thenReturn(userList);

        String exception = "";

        OnlineUser user = null;

        try {
            user = onlineUserService.updateOnlineUser(userId, firstName, lastName, address, isLocal, username, password, email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNotNull(user);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(isLocal, user.getIsLocal());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());

        assertEquals("", exception);
    }

    @Test
    public void getOnlineUserWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = null;
        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.getOnlineUser(userId);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void getOnlineUser() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        OnlineUser user = null;

        try {
            user = onlineUserService.getOnlineUser(userId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(user);
        assertEquals(userId, user.getUserId());
    }

    @Test
    public void getOnlineUserByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = "testUsername";

        OnlineUser user = null;

        try {
            user = onlineUserService.getOnlineUserByUsername(username);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(user);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void getOnlineUserByEmailWithNullEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String email = null;

        OnlineUser user = null;
        String exception = "";

        try {
            user = onlineUserService.getOnlineUserByEmail(email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(user);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void getOnlineUserByEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String email = "Test@Test.com";

        OnlineUser user = null;

        try {
            user = onlineUserService.getOnlineUserByEmail(email);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void deleteOnlineUserByUserIdWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = null;

        boolean deleted = false;
        String exception = "";

        try {
            deleted = onlineUserService.deleteOnlineUserByUserId(userId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(deleted);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void deleteOnlineUserByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        boolean deleted = false;

         try {
             deleted = onlineUserService.deleteOnlineUserByUserId(userId);
         } catch(IllegalArgumentException e) {
             fail();
         }

         assertEquals(true, deleted);
    }

    @Test
    public void deleteOnlineUserByUsernameWithNullUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = null;

        boolean deleted = false;
        String exception = "";

        try {
            deleted = onlineUserService.deleteOnlineUserByUsername(username);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(deleted);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void deleteOnlineUserByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = "testUsername";
        boolean deleted = false;

        try {
            deleted = onlineUserService.deleteOnlineUserByUsername(username);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertEquals(true, deleted);
    }

    @Test
    public void deleteOnlineUserByEmailWithNullEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String email = null;

        boolean deleted = false;
        String exception = "";

        try {
            deleted = onlineUserService.deleteOnlineUserByEmail(email);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(deleted);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void deleteOnlineUserByEmail() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String email = "Test@Test.com";
        boolean deleted = false;

        try {
            deleted = onlineUserService.deleteOnlineUserByEmail(email);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertEquals(true, deleted);
    }

    @Test
    public void reserveItemsByUserIdWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = null;
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = 333L;

        String exception = "";
        Reservation reservation = null;

        try {
            reservation = onlineUserService.reserveItems(userId, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void reserveItemsByUserIdWithNullItems() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;

        Long timeSlotId = 333L;

        String exception = "";
        Reservation reservation = null;

        try {
            reservation = onlineUserService.reserveItems(userId, null, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("List of item ids cannot be null", exception);
    }

    @Test
    public void reserveItemsByUserIdWithNullTimeslot() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = null;

        String exception = "";
        Reservation reservation = null;

        try {
            reservation = onlineUserService.reserveItems(userId, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("TimeSlot cannot be empry.", exception);
    }

    @Test
    public void reserveItemsByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = TIMESLOT_ID;

        Reservation reservation = null;
        try {
            reservation = onlineUserService.reserveItems(userId, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(reservation);
        assertEquals(userId, reservation.getUser().getUserId());
        assertEquals(itemIds.size(), reservation.getItems().size());
        assertEquals(timeSlotId, reservation.getTimeSlot().getTimeSlotId());

    }

    @Test
    public void reserveItemsByUsernameWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = null;
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = 333L;

        String exception = "";
        Reservation reservation = null;

        try {
            reservation = onlineUserService.reserveItems(username, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("Online user does not exist.", exception);
    }


    @Test
    public void reserveItemsByUsernameWithNullTimeslot() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = "testUsername";
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = null;

        String exception = "";
        Reservation reservation = null;

        try {
            reservation = onlineUserService.reserveItems(username, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("TimeSlot cannot be empry.", exception);
    }

    @Test
    public void reserveItemsByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = "testUsername";
        Long itemId = 1L;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(itemId);

        Long timeSlotId = TIMESLOT_ID;

        Reservation reservation = null;
        try {
            reservation = onlineUserService.reserveItems(username, itemIds, timeSlotId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(reservation);
        assertEquals(ONLINE_USER_ID, reservation.getUser().getUserId());
        assertEquals(itemIds.size(), reservation.getItems().size());
        assertEquals(timeSlotId, reservation.getTimeSlot().getTimeSlotId());
    }

    @Test
    public void getReservationsByUserIdWithNullId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = null;

        String exception = "";
        List<Reservation> reservations = null;

        try {
            reservations = onlineUserService.getReservations(userId);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservations);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void getReservationsByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Reservation expectedReservation = new Reservation();
        expectedReservation.setReservationId(123L);
        expectedReservation.setUser(onlineUser);
        List<Reservation> expectedReservations = new ArrayList<>();

        expectedReservations.add(expectedReservation);

        lenient().when(reservationRepository.findAll()).thenReturn(expectedReservations);

        List<Reservation> reservations = null;

        try {
            reservations = onlineUserService.getReservations(userId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(reservations);
        assertEquals(expectedReservations.size(), reservations.size());
    }

    @Test
    public void getReservationsByUsernameWithNullId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = null;

        String exception = "";
        List<Reservation> reservations = null;

        try {
            reservations = onlineUserService.getReservations(username);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(reservations);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void getReservationsByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = "testUsername";

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Reservation expectedReservation = new Reservation();
        expectedReservation.setReservationId(123L);
        expectedReservation.setUser(onlineUser);
        List<Reservation> expectedReservations = new ArrayList<>();

        expectedReservations.add(expectedReservation);

        lenient().when(reservationRepository.findAll()).thenReturn(expectedReservations);

        List<Reservation> reservations = null;

        try {
            reservations = onlineUserService.getReservations(username);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(reservations);
        assertEquals(expectedReservations.size(), reservations.size());
    }

    @Test
    public void addItemToReservationByUserIdWithNullReservationId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        Long reservationId = null;

        Long itemId = ITEM_ID;

        String exception = "";
        boolean added = false;

        try {
            added = onlineUserService.addItemToReservation(userId, reservationId, itemId);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Reservation does not exist.", exception);
    }

    @Test
    public void addItemToReservationByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = 1L;
        Long reservationId = RESERVATION_ID;

        Long itemId = ITEM_ID;

        boolean added = false;

        try {
            added = onlineUserService.addItemToReservation(userId, reservationId, itemId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertTrue(added);
    }


    @Test
    public void addItemToReservationByUsernameWithNullReservationId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        Long reservationId = null;

        Long itemId = ITEM_ID;

        String exception = "";
        boolean added = false;

        try {
            added = onlineUserService.addItemToReservation(username, reservationId, itemId);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Reservation does not exist.", exception);
    }


    @Test
    public void addItemToReservationByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        Long reservationId = RESERVATION_ID;

        Long itemId = ITEM_ID;

        boolean added = false;

        try {
            added = onlineUserService.addItemToReservation(username, reservationId, itemId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertTrue(added);
    }

    @Test
    public void cancelReservationByUserIdWithNullReservationId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = ONLINE_USER_ID;
        Long reservationId = null;
        String exception = "";

        boolean canceled = false;

        try {
            canceled = onlineUserService.cancelReservation(userId, reservationId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(canceled);
        assertEquals("Reservation does not exists.", exception);
    }

    @Test
    public void cancelReservationByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = ONLINE_USER_ID;
        Long reservationId = RESERVATION_ID;

        boolean canceled = false;

        try {
            canceled = onlineUserService.cancelReservation(userId, reservationId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertTrue(canceled);
    }

    @Test
    public void cancelReservationByUsernameWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = null;
        Long reservationId = RESERVATION_ID;
        String exception = "";

        boolean canceled = false;

        try {
            canceled = onlineUserService.cancelReservation(username, reservationId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertFalse(canceled);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void cancelReservationByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        Long reservationId = RESERVATION_ID;

        boolean canceled = false;

        try {
            canceled = onlineUserService.cancelReservation(username, reservationId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertTrue(canceled);
    }

    @Test
    public void requestbookingByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = ONLINE_USER_ID;
        String eventName = "testEventName";
        boolean isPrivate = false;
        Long timeSlotId = TIMESLOT_ID;

        Event expectedEvent = new Event();
        expectedEvent.setName(eventName);
        expectedEvent.setIsPrivate(false);

        Event event = null;

        try {
            event = onlineUserService.requestBooking(userId, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(event);
        assertEquals(expectedEvent.getName(), event.getName());
        assertEquals(expectedEvent.getIsPrivate(), event.getIsPrivate());
    }

    @Test
    public void requestBookingByUsernameWithNullUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = null;
        String eventName = "testEventName";
        boolean isPrivate = false;
        Long timeSlotId = TIMESLOT_ID;

        String exception = "";
        Event event = null;

        try {
            event = onlineUserService.requestBooking(username, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("Online user does not exist.", exception);
    }

    @Test
    public void requestBookingByUsernameWithNullEventName() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        String eventName = null;
        boolean isPrivate = false;
        Long timeSlotId = TIMESLOT_ID;

        String exception = "";
        Event event = null;

        try {
            event = onlineUserService.requestBooking(username, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("The name of an event cannot be empty.", exception);
    }

    @Test
    public void requestBookingByUsernameWithEmptyEventName() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        String eventName = "";
        boolean isPrivate = false;
        Long timeSlotId = TIMESLOT_ID;

        String exception = "";
        Event event = null;

        try {
            event = onlineUserService.requestBooking(username, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("The name of an event cannot be empty.", exception);
    }

    @Test
    public void requestBookingByUsernameWithNullTimeSlot() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        String eventName = "testEventName";
        boolean isPrivate = false;
        Long timeSlotId = null;

        String exception = "";
        Event event = null;

        try {
            event = onlineUserService.requestBooking(username, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("TimeSlot cannot be empry.", exception);
    }

    @Test
    public void requestBookingByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        String eventName = "testEventName";
        boolean isPrivate = false;
        Long timeSlotId = TIMESLOT_ID;

        Event expectedEvent = new Event();
        expectedEvent.setName(eventName);
        expectedEvent.setIsPrivate(false);

        Event event = null;

        try {
            event = onlineUserService.requestBooking(username, eventName, isPrivate, timeSlotId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(event);
        assertEquals(expectedEvent.getName(), event.getName());
        assertEquals(expectedEvent.getIsPrivate(), event.getIsPrivate());
    }


    @Test
    public void getRequestByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Event expectedEvent = new Event();
        expectedEvent.setUser(onlineUser);

        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(expectedEvent);

        lenient().when(eventRepository.findAll()).thenReturn(expectedEvents);
        Long userId = ONLINE_USER_ID;

        List<Event> events = null;

        try {
            events = onlineUserService.getRequests(userId);
        } catch(IllegalArgumentException e) {
        	fail();
        }

        assertNotNull(events);
        assertEquals(expectedEvents.size(), events.size());
    }

    @Test
    public void getRequestByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Event expectedEvent = new Event();
        expectedEvent.setUser(onlineUser);

        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(expectedEvent);

        lenient().when(eventRepository.findAll()).thenReturn(expectedEvents);
        String username = ONLINE_USER_USERNAME;

        List<Event> events = null;

        try {
            events = onlineUserService.getRequests(username);
        } catch(IllegalArgumentException e) {
        	fail();
        }

        assertNotNull(events);
        assertEquals(expectedEvents.size(), events.size());
    }

    @Test
    public void getEventsByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Event expectedEvent = new Event();
        expectedEvent.setUser(onlineUser);
        expectedEvent.setIsAccepted(true);

        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(expectedEvent);

        lenient().when(eventRepository.findAll()).thenReturn(expectedEvents);
        Long userId = ONLINE_USER_ID;

        List<Event> events = null;

        try {
            events = onlineUserService.getEvents(userId);
        } catch(IllegalArgumentException e) {
        	fail();
        }

        assertNotNull(events);
        assertEquals(expectedEvents.size(), events.size());
    }

    @Test
    public void getEventsByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserId(ONLINE_USER_ID);
        onlineUser.setFirstName(ONLINE_USER_FIRST_NAME);
        onlineUser.setLastName(ONLINE_USER_LAST_NAME);
        onlineUser.setAddress(ONLINE_USER_ADDRESS);
        onlineUser.setIsLocal(true);
        onlineUser.setUsername(ONLINE_USER_USERNAME);
        onlineUser.setPassword(ONLINE_USER_PASSWORD);
        onlineUser.setEmail(ONLINE_USER_EMAIL);

        Event expectedEvent = new Event();
        expectedEvent.setUser(onlineUser);
        expectedEvent.setIsAccepted(true);

        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(expectedEvent);

        lenient().when(eventRepository.findAll()).thenReturn(expectedEvents);
        String username = ONLINE_USER_USERNAME;

        List<Event> events = null;

        try {
            events = onlineUserService.getEvents(username);
        } catch(IllegalArgumentException e) {
        	fail();
        }

        assertNotNull(events);
        assertEquals(expectedEvents.size(), events.size());
    }


    @Test
    public void cancelEventByUserId() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        Long userId = ONLINE_USER_ID;
        Long eventId = EVENT_ID;

        boolean cancelled = false;

        try {
            cancelled = onlineUserService.cancelEvent(userId, eventId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertTrue(cancelled);
    }


    @Test
    public void cancelEventByUsername() {
        assertEquals(0, onlineUserService.getAllOnlineUsers().size());

        String username = ONLINE_USER_USERNAME;
        Long eventId = EVENT_ID;

        boolean cancelled = false;

        try {
            cancelled = onlineUserService.cancelEvent(username, eventId);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertTrue(cancelled);
    }

}
