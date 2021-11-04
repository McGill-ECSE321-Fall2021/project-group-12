package ca.mcgill.ecse321.library.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.LibraryHourDto;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.LibraryHourService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryHourRestController {
	
	@Autowired
	private LibraryHourService service;
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@GetMapping(value = { "/libraryHours", "/libraryHours/" })
	public List<LibraryHourDto> getAllLibraryHours() {
		List<LibraryHourDto> libraryHours = new ArrayList<>();
		for(LibraryHour libraryHour: service.getAllLibraryHours()) {
			libraryHours.add(convertToDto(libraryHour));
		}
		return libraryHours;
	}
	
	@GetMapping(value = { "/libraryHour{libraryHourId}", "/ibraryHour/{libraryHourId}/" })
	public LibraryHourDto getLibraryHourById(@PathVariable("libraryHourId") Long libraryHourId) throws IllegalArgumentException {
		LibraryHourDto libraryHour = convertToDto(service.getLibraryHour(libraryHourId));
		return libraryHour;
	}
	
	@GetMapping(value = { "/libraryHours/{librarian}", "/libraryHours/{librarian}/" })
	public List<LibraryHourDto> getLibraryHoursOfLibrarian(@PathVariable("librarian") LibrarianDto librarianDto) throws IllegalArgumentException {
		List<LibraryHourDto> libraryHoursOfLibrarian = new ArrayList<>();
		Librarian librarian = librarianRepository.findLibrarianByUserId(librarianDto.getLibrarianId());//Or getLibrarianLibraryHours if such method is constructed.
		List<LibraryHour> libraryHours = librarian.getLibraryHours();
		for(LibraryHour libraryHour: libraryHours) {
			libraryHoursOfLibrarian.add(convertToDto(libraryHour));
		}
		return libraryHoursOfLibrarian;
	}
	
	@PostMapping(value = { "/libraryHour/create", "/libraryHour/create/" })
	public LibraryHourDto createLibraryHour(@RequestParam(name = "day") Day day,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
	throws IllegalArgumentException {
		LibraryHourDto libraryHourDto = new LibraryHourDto();
		libraryHourDto.setDay(day);
		libraryHourDto.setStartTime(Time.valueOf(startTime));
		libraryHourDto.setEndTime(Time.valueOf(endTime));
		return libraryHourDto;
	}
	
	@DeleteMapping(value = { "/libraryHour/delete/{libraryHourId}", "/libraryHour/delete/{libraryHourId}/" })
	public LibraryHourDto deleteLibraryHour(@PathVariable("libraryHourId") Long libraryHourId,
	@RequestParam(name = "librarian") LibrarianDto librarianDto)
	throws IllegalArgumentException {
		Librarian librarian = librarianRepository.findLibrarianByUserId(librarianDto.getLibrarianId);
		LibraryHour libraryHour = service.getLibraryHour(libraryHourId);
		service.deleteLibraryHour(librarian, libraryHour);
		LibraryHourDto libraryHourDto = convertToDto(libraryHour);
		return libraryHourDto;
	}
	
	private LibraryHourDto convertToDto(LibraryHour libraryHour) throws IllegalArgumentException {
		if(libraryHour == null) {
			throw new IllegalArgumentException("Input libraryHour is null.");
		}
		LibraryHourDto libraryHourDto = new LibraryHourDto();
		libraryHourDto.setStartTime(libraryHour.getStartTime());
		libraryHourDto.setEndTime(libraryHour.getEndTime());
		libraryHourDto.setDay(libraryHour.getDay());
		libraryHourDto.setLibraryHourId(libraryHour.getLibraryHourId());
		return libraryHourDto;
	}
	
}
