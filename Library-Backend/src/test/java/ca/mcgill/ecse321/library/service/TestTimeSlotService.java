package ca.mcgill.ecse321.library.service;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.TimeSlotRepository;
import ca.mcgill.ecse321.library.model.TimeSlot;


@ExtendWith(MockitoExtension.class)
public class TestTimeSlotService {
	
	@Mock
	private TimeSlotRepository timeSlotDao;
	
	@InjectMocks
	private TimeSlotService service;
	
	private static final Time START_TIME = Time.valueOf("08:00:00");
	private static final Time END_TIME = Time.valueOf("20:00:00");
	private static final Date START_DATE = Date.valueOf("2021-12-12");
	private static final Date END_DATE = Date.valueOf("2022-01-01");
	private static final Long TIMESLOT_KEY = 0L;
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(timeSlotDao.findTimeSlotByTimeSlotId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TIMESLOT_KEY)) {
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(START_TIME);
				timeSlot.setEndTime(END_TIME);
				timeSlot.setStartDate(START_DATE);
				timeSlot.setEndDate(END_DATE);
				timeSlot.setTimeSlotId(TIMESLOT_KEY);
				return timeSlot;
			} else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(timeSlotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		
	}
	
	
	@Test
	public void testCreateTimeSlot() {
		assertEquals(0, service.getAllTimeSlots().size());
		
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
	}
	
	@Test
	public void testCreateTimeSlotNullStartTime() {
		
		String start = null;
		String end = "20:00:00";
  		String startD = "2021-12-12";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotNullEndTime() {
		
		String start = "08:00:00";
		String end = null;
		String startD = "2021-12-12";
		String endD ="2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotNullStartDate() {
		
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = null;
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start date.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotNullEndDate() {
		
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = null;
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end date.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotEmptyStartTime() {
		String start = "";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotEmptyEndTime() {
		String start = "08:00:00";
		String end = "";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotEmptyStartDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start date.", error);
	}
	
	@Test
	public void testCreateTimeSlotEmptyEndDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end date.", error);
	}
	
	@Test
	public void testCreateTimeSlotSpacesStartTime() {
		String start = " ";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotSpacesEndTime() {
		String start = "08:00:00";
		String end = " ";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end time.", error);
		
	}
	
	@Test
	public void testCreateTimeSlotSpacesStartDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = " ";
		String endD = "2022-01-01";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty start date.", error);
	}
	
	@Test
	public void testCreateTimeSlotSpacesEndDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = " ";
		String error = "";
		
		TimeSlot timeSlot = null;
		try {
			timeSlot = service.createTimeSlot(start, end, startD, endD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(timeSlot);
		assertEquals("A TimeSlot cannot have an empty end date.", error);
	}
	
	@Test
	public void testUpdateTimeSlot() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(newStart), timeSlot.getStartTime());
		assertEquals(Time.valueOf(newEnd), timeSlot.getEndTime());
		assertEquals(Date.valueOf(newStartD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(newEndD), timeSlot.getEndDate());
		
	}
	
	@Test
	public void testUpdateTimeSlotNullStartTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = null;
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start time.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotNullEndTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = null;
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end time.",error);
		
	}
	
	
	@Test
	public void testUpdateTimeSlotNullStartDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = null;
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start date.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotNullEndDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = null;
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end date.",error);
		
	}
	
	
	@Test
	public void testUpdateTimeSlotEmptyStartTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start time.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotEmptyEndTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end time.",error);
		
	}
	
	
	@Test
	public void testUpdateTimeSlotEmptyStartDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = "";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start date.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotEmptyEndDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = "";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end date.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotSpacesStartTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = " ";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start time.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotSpacesEndTime() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = " ";
		String newStartD = "2021-12-13";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end time.",error);
		
	}
	
	
	@Test
	public void testUpdateTimeSlotSpacesStartDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = " ";
		String newEndD = "2022-01-02";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty start date.",error);
		
	}
	
	@Test
	public void testUpdateTimeSlotSpacesEndDate() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		
		String newStart = "09:00:00";
		String newEnd = "21:00:00";
		String newStartD = "2021-12-13";
		String newEndD = " ";
		
		String error = "";
		
		try {
			timeSlot = service.updateTimeSlot(TIMESLOT_KEY, newStart, newEnd, newStartD, newEndD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(timeSlot);
		assertEquals(Time.valueOf(start), timeSlot.getStartTime());
		assertEquals(Time.valueOf(end), timeSlot.getEndTime());
		assertEquals(Date.valueOf(startD), timeSlot.getStartDate());
		assertEquals(Date.valueOf(endD), timeSlot.getEndDate());
		
		assertEquals("A TimeSlot cannot have an empty end date.",error);
		
	}
	
	@Test
	public void testGetTimeSlot() {
		assertEquals(0, service.getAllTimeSlots().size());
		String startTime = "08:00:00";
		String endTime = "20:00:00";
		String startDate = "2021-12-12";
		String endDate = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(startTime, endTime, startDate, endDate);
		assertNotNull(timeSlot);
		TimeSlot timeSlot2 = null;
		try {
			timeSlot2 = service.getTimeSlot(TIMESLOT_KEY);
		} catch (Exception e) {
			fail();
		}
		assertNotNull(timeSlot2);
		assertEquals(Time.valueOf(startTime), timeSlot2.getStartTime());
		assertEquals(Time.valueOf(endTime),timeSlot2.getEndTime());
		assertEquals(Date.valueOf(startDate), timeSlot2.getStartDate());
		assertEquals(Date.valueOf(endDate), timeSlot2.getEndDate());
	}
	
	
	@Test
	public void testDeleteTimeSlot() {
		String start = "08:00:00";
		String end = "20:00:00";
		String startD = "2021-12-12";
		String endD = "2022-01-01";
		TimeSlot timeSlot = null;
		timeSlot = service.createTimeSlot(start, end, startD, endD);
		assertNotNull(timeSlot);
		
		try {
			timeSlot = service.deleteTimeSlot(TIMESLOT_KEY);
		} catch (Exception e) {
			fail();
		}
	}
	
	
}
