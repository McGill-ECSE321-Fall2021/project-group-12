package ca.mcgill.ecse321.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.EventRepository;
import ca.mcgill.ecse321.library.dao.OfflineUserRepository;
import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.model.Event;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.TimeSlot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class TestEventService {

	@Mock
	private EventRepository eventDao;
	
	@Mock 
	TimeSlotRepository timeSlotRepository;
	@Mock
	OfflineUserRepository offlineUserRepository;
	
	@InjectMocks
	private EventService service;
	
	private static final String EVENT_NAME = "Name";
	private static final Boolean IS_PRIVATE = false;
	private static final Boolean IS_ACCEPTED = false;
	
	private static Long EVENT_ID = 0L;
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(eventDao.findEventByEventId(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EVENT_ID)) {
				Event event = new Event();
				event.setName(EVENT_NAME);
				event.setIsPrivate(IS_PRIVATE);
				event.setIsAccepted(IS_ACCEPTED);
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(Time.valueOf("08:00:00"));
				timeSlot.setEndTime(Time.valueOf("20:00:00"));
				timeSlot.setStartDate(Date.valueOf("2021-12-12"));
				timeSlot.setEndDate(Date.valueOf("2021-12-13"));
				timeSlot.setTimeSlotId(100L);
				event.setTimeSlot(timeSlot);
				OfflineUser user = new OfflineUser();
				user.setFirstName("First");
				user.setLastName("Last");
				user.setIsLocal(true);
				user.setAddress("1000 King St.");
				user.setUserId(200L);
				event.setUser(user);
				return event;
			}else {
				return null;
			}
		});
		

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
	
	}
	
	
	@Test
	public void testCreateEvent() {
		assertEquals(0, service.getAllEvents().size());
		
		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			fail();	
		}
		
		assertNotNull(event);
		assertEquals(eventName, event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		
	}
	
	@Test
	public void testCreateEventNullName() {
		
		String eventName = null;
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	@Test
	public void testCreateEventEmptyName() {
		
		String eventName = "";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	@Test
	public void testCreateEventSpacesName() {
		
		String eventName = " ";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	@Test
	public void testCreateEventNullTimeSlot() {
		
		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot = null;
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals(error, "An Event cannot have an empty timeSlot.");
	}
	

	@Test
	public void testCreateEventNullUser() {
		
		String eventName = " ";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user = null;
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals(error, "An Event cannot have an empty user.");
	}
	
	
	@Test
	public void testUpdateEvent() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = "NewName";
			isPrivate = true;
			isAccepted = true;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(event);
		assertEquals(eventName, event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		
	}
	
	@Test
	public void testUpdateEventNullName() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = null;
			isPrivate = true;
			isAccepted = true;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(event);
		assertEquals("Name", event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	@Test
	public void testUpdateEventEmptyName() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = "";
			isPrivate = true;
			isAccepted = true;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(event);
		assertEquals("Name", event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	
	@Test
	public void testUpdateEventSpacesName() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = " ";
			isPrivate = true;
			isAccepted = true;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(event);
		assertEquals("Name", event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		assertEquals(error, "An Event cannot have an empty name.");
	}
	
	@Test
	public void testUpdateEventNullTimeSlot() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = "NewName";
			isPrivate = true;
			isAccepted = true;
			timeSlot = null;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(event);
		assertEquals("Name", event.getName());
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertNotNull(user);
		assertEquals(200L, event.getUser().getUserId());
		assertEquals(error, "An Event cannot have an empty timeSlot.");
	}
	
	@Test
	public void testUpdateEventNullUser() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		String error = "";
		
		Event event = null;
		
		try {
			event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
			eventName = "NewName";
			isPrivate = true;
			isAccepted = true;
			user = null;
			event = service.updateEvent(EVENT_ID, eventName, timeSlot, isPrivate, isAccepted, user);			
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(event);
		assertEquals("Name", event.getName());
		assertNotNull(timeSlot);
		assertEquals(100L, event.getTimeSlot().getTimeSlotId());
		assertEquals(isPrivate, event.getIsPrivate());
		assertEquals(isAccepted, event.getIsAccepted());
		assertEquals(200L, event.getUser().getUserId());
		assertEquals(error, "An Event cannot have an empty user.");
	}
	
	@Test
	public void testGetEvent() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		Event event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		assertNotNull(event);
		Event event2 = null;
				
		try {
			event2 = service.getEvent(EVENT_ID);
		} catch (IllegalArgumentException e){
			fail();	
		}
		
		assertNotNull(event2);
		assertEquals(event2.getName(), event.getName());
		assertNotNull(timeSlot);
		assertEquals(event2.getTimeSlot(),event.getTimeSlot());
		assertEquals(event2.getIsPrivate(), event.getIsPrivate());
		assertEquals(event2.getIsAccepted(), event.getIsAccepted());
		assertNotNull(user);
		assertEquals(event2.getUser(), event.getUser());
		
	}
	
	@Test
	public void testDeleteEvent() {

		String eventName = "Name";
		Boolean isPrivate = false;
		Boolean isAccepted = false;
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf("08:00:00"));
		timeSlot.setEndTime(Time.valueOf("20:00:00"));
		timeSlot.setStartDate(Date.valueOf("2021-12-12"));
		timeSlot.setEndDate(Date.valueOf("2021-12-13"));
		timeSlot.setTimeSlotId(100L);
		
		OfflineUser user = new OfflineUser();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setIsLocal(true);
		user.setAddress("1000 King St.");
		user.setUserId(200L);
		
		Event event = service.createEvent(eventName, timeSlot, isPrivate, isAccepted, user);
		assertNotNull(event);
		try {
			service.deleteEvent(EVENT_ID);
		} catch (IllegalArgumentException e){
			fail();	
		}
		
	}
	
	
}
