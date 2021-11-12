package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.LibraryHour.Day;

@Service
public class LibraryHourService {
	
	@Autowired
	LibraryHourRepository libraryHourRepository;
	@Autowired
	LibrarianRepository librarianRepository;
	
	@Transactional
	public LibraryHour createLibraryHour(Time startTime, Time endTime, Day day) throws IllegalArgumentException {
		if(startTime == null || endTime == null || day == null) {
			throw new IllegalArgumentException("Cannot create LibraryHour with empty arguments.");
		}
		if(!(day == Day.Friday || day == Day.Thursday || day == Day.Wednesday || day == Day.Tuesday || day == Day.Monday || day == Day.Sunday || day == Day.Saturday)) {
			throw new IllegalArgumentException("Entry of day is invalid.");
		}
		if(startTime.after(endTime)) {
			throw new IllegalArgumentException("Invalid time input, startTime cannot be after endTime");
		}
		LibraryHour libraryHour = new LibraryHour();
		libraryHour.setDay(day);
		libraryHour.setStartTime(startTime);
		libraryHour.setEndTime(endTime);
		libraryHourRepository.save(libraryHour);
		return libraryHour;
	}
	
	
	
	
	@Transactional
	public LibraryHour assignLibraryHour(Librarian librarian, LibraryHour libraryHour) throws IllegalArgumentException {
		if(librarian == null || libraryHour == null) {
			throw new IllegalArgumentException("Cannot delete LibraryHour with empty arguments.");
		}
		if(librarian.getLibraryHours().contains(libraryHour)) {
			throw new IllegalArgumentException("Targeting librarian already have such libraryHour.");
		}
		List<LibraryHour> updatedLibraryHours = librarian.getLibraryHours();
		updatedLibraryHours.add(libraryHour);
		librarian.setLibraryHours(updatedLibraryHours);
		librarianRepository.save(librarian);
		return libraryHour;
	}
	
	
	
	
	
	@Transactional
	public LibraryHour deleteLibraryHour(Librarian librarian, LibraryHour libraryHour) throws IllegalArgumentException {
		if(librarian == null || libraryHour == null) {
			throw new IllegalArgumentException("Cannot delete LibraryHour with empty arguments.");
		}
		if(!librarian.getLibraryHours().contains(libraryHour)) {
			throw new IllegalArgumentException("Targeting librarian does not have such libraryHour.");
		}
		List<LibraryHour> updatedLibraryHours = librarian.getLibraryHours();
		updatedLibraryHours.remove(libraryHour);
		librarian.setLibraryHours(updatedLibraryHours);
		librarianRepository.save(librarian);
		libraryHourRepository.delete(libraryHour);
		return libraryHour;
	}
	
	@Transactional
	public LibraryHour getLibraryHour(Long libraryHourId) throws IllegalArgumentException {
		if(libraryHourId == null) {
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		LibraryHour libraryHour = libraryHourRepository.findLibraryHourByLibraryHourId(libraryHourId);
		if (libraryHour == null) {
			throw new IllegalArgumentException("Cannot find such libraryHour.");
		}
		return libraryHour;
	}
	
	@Transactional
	public List<LibraryHour> getLibraryHoursOfLibrarian(Librarian librarian) throws IllegalArgumentException {
		if(librarian == null) {
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		if(librarianRepository.findLibrarianByUserId(librarian.getUserId()) == null) {
			throw new IllegalArgumentException("Such librarian does not exist.");
		}
		List<LibraryHour> libraryHours = librarian.getLibraryHours();
		return libraryHours;
	}
	
	@Transactional
	public List<LibraryHour> getAllLibraryHours() {
		return (List<LibraryHour>) libraryHourRepository.findAll();
	}
}
