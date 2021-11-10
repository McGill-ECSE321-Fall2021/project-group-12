package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    private static final Long RESERVATION_ID = 222L;
    private static final Long ITEM_ID = 333L;
    private static final Long EVENT_ID = 444L;
    private static final Long TIMESLOT_ID = 555L;
    @Mock
    private OfflineUserRepository offlineUserRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private NewspaperRepository newspaperRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private OfflineUserService offlineUserService;

    @BeforeEach
    public void setMockOfflineUserOutput() {
        lenient().when(offlineUserRepository.findOfflineUserByUserId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(OFFLINE_USER_ID)) {
                        OfflineUser offlineUser = new OfflineUser();
                        offlineUser.setUserId(OFFLINE_USER_ID);
                        offlineUser.setFirstName(OFFLINE_USER_FIRST_NAME);
                        offlineUser.setLastName(OFFLINE_USER_LAST_NAME);
                        offlineUser.setAddress(OFFLINE_USER_ADDRESS);
                        offlineUser.setIsLocal(true);
                        return offlineUser;
                    } else {
                        return null;
                    }
                });

        lenient().when(reservationRepository.findReservationByReservationId(anyLong()))
            .thenAnswer((InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(RESERVATION_ID)) {
                    OfflineUser offlineUser = new OfflineUser();
                    offlineUser.setFirstName(OFFLINE_USER_FIRST_NAME);
                    offlineUser.setLastName(OFFLINE_USER_LAST_NAME);
                    offlineUser.setAddress(OFFLINE_USER_ADDRESS);
                    offlineUser.setUserId(OFFLINE_USER_ID);
                    offlineUser.setIsLocal(true);

                    Reservation reservation = new Reservation();
                    reservation.setReservationId(RESERVATION_ID);
                    reservation.setItems(new ArrayList<Item>());
                    reservation.setUser(offlineUser);
                    reservation.setTimeSlot(new TimeSlot());
                    reservation.setLibraryApplicationSystem(new LibraryApplicationSystem());
                    return reservation;
                } else {
                    return null;
                }
        });

        lenient().when(albumRepository.findAlbumByItemId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        Album album = new Album();
                        album.setNumSongs(1);
                        album.setGenre(Album.MusicGenre.Jazz);
                        return album;
                    }
                    else{
                        return null;
                    }
                });

        lenient().when(bookRepository.findBookByItemId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        Book book = new Book();
                        book.setNumPages(1);
                        book.setGenre(Book.BMGenre.Action);

                        return book;
                    }
                    else{
                        return null;
                    }
                });

        lenient().when(movieRepository.findMovieByItemId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        Movie movie = new Movie();
                        movie.setDuration(1);
                        movie.setGenre(Movie.BMGenre.Action);
                        return movie;
                    }
                    else {
                        return null;
                    }
                });

        lenient().when(eventRepository.findEventByEventId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(EVENT_ID)) {
                        OfflineUser offlineUser = new OfflineUser();
                        offlineUser.setFirstName(OFFLINE_USER_FIRST_NAME);
                        offlineUser.setLastName(OFFLINE_USER_LAST_NAME);
                        offlineUser.setAddress(OFFLINE_USER_ADDRESS);
                        offlineUser.setUserId(OFFLINE_USER_ID);
                        offlineUser.setIsLocal(true);

                        Event event = new Event();
                        event.setUser(offlineUser);
                        event.setEventId(EVENT_ID);
                        return event;
                    } else {
                        return null;
                    }
                });

        lenient().when(newspaperRepository.findByItemId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {

                        Reservation reservation = new Reservation();
                        Newspaper newspaper = new Newspaper();

                        newspaper.setReservation(reservation);
                        return newspaper;
                    } else {
                        return null;
                    }
                });


        Answer<?> returningAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(albumRepository.existsById(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        return true;
                    } else {
                        return false;
                    }
                });

        lenient().when(bookRepository.existsById(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        return true;
                    } else {
                        return false;
                    }
                });

        lenient().when(movieRepository.existsById(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        return true;
                    } else {
                        return false;
                    }
                });

        lenient().when(newspaperRepository.existsById(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(ITEM_ID)) {
                        return true;
                    } else {
                        return false;
                    }
                });

        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotId(anyLong()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if(invocation.getArgument(0).equals(TIMESLOT_ID)) {
                        TimeSlot timeslot = new TimeSlot();
                        timeslot.setTimeSlotId(TIMESLOT_ID);
                        return timeslot;
                    } else {
                        return null;
                    }
                });


        lenient().when(offlineUserRepository.save(any(OfflineUser.class))).thenAnswer(returningAnswer);
        lenient().when(reservationRepository.save(any())).thenAnswer(returningAnswer);
        lenient().when(eventRepository.save(any(Event.class))).thenAnswer(returningAnswer);

    }

    @Test
    public void testCreatingOfflineUserWithValidFirstNameLastNameAddress() {
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
    public void testCreatingOfflineUserWithInvalidFirstName() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String exception = "";

        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        try {
            testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            testOfflineUser = null;
        }

        assertNull(testOfflineUser);
        assertEquals("the first name cannot have an empty first name.", exception);

    }

    @Test
    public void testCreatingOfflineUserWithInvalidLastName() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "";
        String address = "testAddress";
        boolean isLocal = true;
        String exception = "";

        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        try {
            testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            testOfflineUser = null;
        }

        assertNull(testOfflineUser);
        assertEquals("the last name cannot have an empty last name.", exception);

    }

    @Test
    public void testCreatingOfflineUserWithInvalidAddress() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "";
        boolean isLocal = true;
        String exception = "";

        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        try {
            testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            testOfflineUser = null;
        }

        assertNull(testOfflineUser);
        assertEquals("the address cannot have an empty address.", exception);

    }

    @Test
    public void testCreatingOfflineUserWithInvalidFirstNameLastNameAddress() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineUser = new OfflineUser();
        String firstName = "";
        String lastName = "";
        String address = "";
        boolean isLocal = true;
        String exception = "";

        testOfflineUser.setFirstName(firstName);
        testOfflineUser.setLastName(lastName);
        testOfflineUser.setAddress(address);
        testOfflineUser.setIsLocal(isLocal);

        try {
            testOfflineUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            testOfflineUser = null;
        }

        assertNull(testOfflineUser);
        assertEquals("the first name cannot have an empty first name.", exception);

    }

    @Test
    public void testUpdateOfflineUserWithNotNullFields() {
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
    public void testGetOfflineUser() {
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

        lenient().when(offlineUserRepository.findOfflineUserByUserId(Mockito.any())).thenReturn(testOfflineeUser);

        OfflineUser testOfflineeUser2 = offlineUserService.getOfflineUser(testOfflineeUser.getUserId());

        assertEquals(testOfflineeUser, testOfflineeUser2);
    }

    @Test
    public void testGetOfflineUserWithException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        OfflineUser testOfflineeUser = new OfflineUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String address = "testAddress";
        boolean isLocal = true;
        String exception = "";

        testOfflineeUser.setFirstName(firstName);
        testOfflineeUser.setLastName(lastName);
        testOfflineeUser.setAddress(address);
        testOfflineeUser.setIsLocal(isLocal);

        testOfflineeUser = offlineUserService.createOfflineUser(firstName, lastName, address, isLocal);

        try {
            OfflineUser testOfflineeUser2 = offlineUserService.getOfflineUser(testOfflineeUser.getUserId());
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            testOfflineeUser = null;
        }

        assertNull(testOfflineeUser);
        assertEquals("Offline user does not exist.", exception);
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

    @Test
    public void testDeleteOfflineUserWithException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        String exception = "";
        boolean isDeleted;
        try {
            isDeleted = offlineUserService.deleteOfflineUser(999L);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            isDeleted = false;
        }

        assertFalse(isDeleted);
        assertEquals("Offline user does not exist.", exception);

    }

    @Test
    public void reserveItemsTestWithUserNotFound() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        String exception = "";
        try {
            offlineUserService.reserveItems(22L, null, null);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void reserveItemsTestWithEmptyItems() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        try {
            offlineUserService.reserveItems(OFFLINE_USER_ID, null, null);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertEquals("List of item ids cannot be null", exception);
    }

    @Test
    public void reserveItemsTestWithEmptyTimeslot() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Long> items = new ArrayList<>();
        items.add(ITEM_ID);

        String exception = "";
        try {
            offlineUserService.reserveItems(OFFLINE_USER_ID, items, null);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }

        assertEquals("TimeSlot cannot be empry.", exception);
    }

    @Test
    public void reserveItemsTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());


        List<Long> items = new ArrayList<>();
        items.add(ITEM_ID);
        Reservation reservation;
        String exception = "";

        try {
            reservation = offlineUserService.reserveItems(OFFLINE_USER_ID, items, TIMESLOT_ID);
        } catch (IllegalArgumentException e) {
            reservation = null;
            exception = e.getMessage();
        }

        assertNotNull(reservation);
        assertEquals(reservation.getItems().size(), items.size());
    }

    @Test
    public void getReservationsWithException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Reservation> reservation;
        String exception = "";

        try {
            reservation = offlineUserService.getReservations(999L);
        } catch (IllegalArgumentException e) {
            reservation = null;
            exception = e.getMessage();
        }

        assertNull(reservation);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void getReservationsTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Reservation> reservation;
        String exception = "";

        try {
            reservation = offlineUserService.getReservations(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            reservation = null;
            exception = e.getMessage();
        }

        assertNotNull(reservation);
        assertEquals(0, reservation.size());
        assertEquals("", exception);
    }

    @Test
    public void getReservationsTest2() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        OfflineUser offlineUser = new OfflineUser();
        offlineUser.setUserId(OFFLINE_USER_ID);

        List<Reservation> reservations = new ArrayList<>();

        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(2L);
        reservation1.setUser(offlineUser);
        Reservation reservation2 = new Reservation();
        reservation2.setReservationId(3L);
        reservation2.setUser(offlineUser);

        reservations.add(reservation1);
        reservations.add(reservation2);
        lenient().when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> reservation;
        String exception = "";

        try {
            reservation = offlineUserService.getReservations(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            reservation = null;
            exception = e.getMessage();
        }

        assertNotNull(reservation);
        assertEquals(2, reservation.size());
    }


    @Test
    public void cancelReservationTestWithReservationException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        boolean canceled;
        String exception = "";

        try {
            canceled = offlineUserService.cancelReservation(OFFLINE_USER_ID, null);
        } catch (IllegalArgumentException e) {
            canceled = false;
            exception = e.getMessage();
        }

        assertFalse(canceled);
        assertEquals("Reservation does not exists.", exception);
    }

    @Test
    public void cancelReservationWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        boolean canceled;
        String exception = "";

        try {
            canceled = offlineUserService.cancelReservation(null, 222L);
        } catch(IllegalArgumentException e) {
            canceled = false;
            exception = e.getMessage();
        }

        assertFalse(canceled);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void cancelReservationTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        boolean canceled;
        String exception = "";

        try {
            canceled = offlineUserService.cancelReservation(OFFLINE_USER_ID, RESERVATION_ID);
        } catch(IllegalArgumentException e) {
            canceled = false;
            exception = e.getMessage();
        }

        assertTrue(canceled);
        assertEquals("", exception);
    }

    @Test
    public void addItemToReservationWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;
        try {
            added = offlineUserService.addItemToReservation(null, RESERVATION_ID, 333L);
        } catch (IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void addItemToReservationWithReservationException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItemToReservation(OFFLINE_USER_ID, null, 333L);
        } catch(IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Reservation does not exist.", exception);
    }

    @Test
    public void addItemToReservationWithItemException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItemToReservation(OFFLINE_USER_ID, RESERVATION_ID, null);
        } catch(IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Item does not exist", exception);
    }

    @Test
    public void addItemToReservationTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItemToReservation(OFFLINE_USER_ID, RESERVATION_ID, ITEM_ID);
        } catch(IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertTrue(added);
        assertEquals("", exception);
    }

    @Test
    public void addItemWithUserExeption() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItem(null, RESERVATION_ID, new Book());
        } catch(IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void addItemWithReservationException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItem(OFFLINE_USER_ID, null, new Book());
        } catch(IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Reservation does not exist.", exception);
    }

    @Test
    public void addItemWithItemException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItem(OFFLINE_USER_ID, RESERVATION_ID, null);
        } catch (IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertFalse(added);
        assertEquals("Item does not exist.", exception);
    }

    @Test
    public void addItemTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        boolean added;

        try {
            added = offlineUserService.addItem(OFFLINE_USER_ID, RESERVATION_ID, new Book());
        } catch (IllegalArgumentException e) {
            added = false;
            exception = e.getMessage();
        }

        assertTrue(added);
        assertEquals("", exception);
    }

    @Test
    public void requestBookingWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        Event event = null;

        try{
            event = offlineUserService.requestBooking(null, "Event Name", true, 33L);
        } catch(IllegalArgumentException e) {
            event = null;
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void requestBookingWithEventNameNullException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        Event event = null;

        try{
            event = offlineUserService.requestBooking(OFFLINE_USER_ID, null, true, 22L);
        } catch(IllegalArgumentException e) {
            event = null;
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("The name of an event cannot be empty.", exception);
    }

    @Test
    public void requestBookingWithEventNameEmptyException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        Event event = null;

        try{
            event = offlineUserService.requestBooking(OFFLINE_USER_ID, "", true, 33L);
        } catch(IllegalArgumentException e) {
            event = null;
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("The name of an event cannot be empty.", exception);
    }

    @Test
    public void requestBookingWithEventTimeSlotNullException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        String exception = "";
        Event event = null;

        try{
            event = offlineUserService.requestBooking(OFFLINE_USER_ID, "Event Name", true, null);
        } catch(IllegalArgumentException e) {
            event = null;
            exception = e.getMessage();
        }

        assertNull(event);
        assertEquals("TimeSlot cannot be empry.", exception);
    }

    @Test
    public void requestBookingTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        OfflineUser offlineUser = new OfflineUser();
        offlineUser.setUserId(OFFLINE_USER_ID);
        offlineUser.setFirstName(OFFLINE_USER_FIRST_NAME);
        offlineUser.setLastName(OFFLINE_USER_LAST_NAME);
        offlineUser.setAddress(OFFLINE_USER_ADDRESS);
        offlineUser.setIsLocal(true);

        TimeSlot expectedTimeSlot = new TimeSlot();
        expectedTimeSlot.setTimeSlotId(TIMESLOT_ID);

        Event expectedEvent = new Event();
        expectedEvent.setName("Event Name");
        expectedEvent.setIsPrivate(true);
        expectedEvent.setTimeSlot(expectedTimeSlot);
        expectedEvent.setUser(offlineUser);
        expectedEvent.setIsAccepted(false);

        String exception = "";
        Event event = null;

        try{
            event = offlineUserService.requestBooking(OFFLINE_USER_ID, "Event Name", true, TIMESLOT_ID);
        } catch(IllegalArgumentException e) {
            event = null;
            exception = e.getMessage();
        }
        assertEquals("", exception);
        assertEquals(event.getName(), expectedEvent.getName());
        assertEquals(event.getIsPrivate(), expectedEvent.getIsPrivate());
        assertEquals(event.getTimeSlot().getTimeSlotId(), expectedEvent.getTimeSlot().getTimeSlotId());
        assertEquals(event.getUser().getUserId(), expectedEvent.getUser().getUserId());
        assertEquals(event.getUser().getFirstName(), expectedEvent.getUser().getFirstName());
        assertEquals(event.getUser().getLastName(), expectedEvent.getUser().getLastName());
        assertEquals(event.getUser().getIsLocal(), expectedEvent.getUser().getIsLocal());

        assertEquals(event.getIsAccepted(), expectedEvent.getIsAccepted());
    }

    @Test
    public void getRequestsWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Event> events;
        String exception = "";
        try {
            events = offlineUserService.getRequests(null);
        } catch(IllegalArgumentException e) {
            events = null;
            exception = e.getMessage();
        }

        assertNull(events);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void getRequestsEmptyList() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Event> events;
        String exception = "";

        try {
            events = offlineUserService.getRequests(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            events = null;
            exception = e.getMessage();
        }

        assertNotNull(events);
        assertEquals(0, events.size());
        assertEquals("", exception);
    }

    @Test
    public void getRequestsNotEmptyTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        OfflineUser offlineUser = new OfflineUser();
        offlineUser.setUserId(OFFLINE_USER_ID);

        List<Event> events = new ArrayList<>();

        Event event1 = new Event();
        event1.setUser(offlineUser);
        event1.setEventId(2L);
        Event event2 = new Event();
        event2.setUser(offlineUser);
        event2.setEventId(3L);

        events.add(event1);
        events.add(event2);
        lenient().when(eventRepository.findAll()).thenReturn(events);

        List<Event> actualEvents;
        String exception = "";

        try {
            actualEvents = offlineUserService.getRequests(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            actualEvents = null;
            exception = e.getMessage();
        }

        assertNotNull(actualEvents);
        assertEquals(2, events.size());
    }

    @Test
    public void getEventsWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Event> events;
        String exception = "";
        try {
            events = offlineUserService.getEvents(null);
        } catch(IllegalArgumentException e) {
            events = null;
            exception = e.getMessage();
        }

        assertNull(events);
        assertEquals("Offline user does not exist.", exception);
    }

    @Test
    public void getEventsEmptyList() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        List<Event> events;
        String exception = "";

        try {
            events = offlineUserService.getEvents(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            events = null;
            exception = e.getMessage();
        }

        assertNotNull(events);
        assertEquals(0, events.size());
        assertEquals("", exception);
    }

    @Test
    public void getEventsNotEmptyTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        OfflineUser offlineUser = new OfflineUser();
        offlineUser.setUserId(OFFLINE_USER_ID);

        List<Event> events = new ArrayList<>();

        Event event1 = new Event();
        event1.setUser(offlineUser);
        event1.setEventId(2L);
        Event event2 = new Event();
        event2.setUser(offlineUser);
        event2.setEventId(3L);

        events.add(event1);
        events.add(event2);
        lenient().when(eventRepository.findAll()).thenReturn(events);

        List<Event> actualEvents;
        String exception = "";

        try {
            actualEvents = offlineUserService.getEvents(OFFLINE_USER_ID);
        } catch (IllegalArgumentException e) {
            actualEvents = null;
            exception = e.getMessage();
        }

        assertNotNull(actualEvents);
        assertEquals(2, events.size());
    }

    @Test
    public void cancelEventWithUserException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        boolean cancelledEvent;
        String exception = "";

        try {
            cancelledEvent = offlineUserService.cancelEvent(null, EVENT_ID);
        } catch(IllegalArgumentException e) {
            cancelledEvent = false;
            exception = e.getMessage();
        }

        assertFalse(cancelledEvent);
        assertEquals("Offline user does not exist.", exception);

    }

    @Test
    public void cancelEventWithReservationException() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());

        boolean cancelledEvent;
        String exception = "";

        try {
            cancelledEvent = offlineUserService.cancelEvent(OFFLINE_USER_ID, null);
        } catch(IllegalArgumentException e) {
            cancelledEvent = false;
            exception = e.getMessage();
        }

        assertFalse(cancelledEvent);
        assertEquals("Reservation does not exists.", exception);
    }

    @Test
    public void cancelEventTest() {
        assertEquals(0, offlineUserService.getAllOfflineUsers().size());
        boolean cancelledEvent;
        String exception = "";

        try {
            cancelledEvent = offlineUserService.cancelEvent(OFFLINE_USER_ID, EVENT_ID);
        } catch(IllegalArgumentException e) {
            cancelledEvent = false;
            exception = e.getMessage();
        }

        assertTrue(cancelledEvent);
        assertEquals("", exception);
    }
}
