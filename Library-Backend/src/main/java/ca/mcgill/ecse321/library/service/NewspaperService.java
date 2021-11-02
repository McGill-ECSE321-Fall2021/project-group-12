package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Newspaper;

@Service
public class NewspaperService {
	
	@Autowired
	NewspaperRepository newspaperRepository;
	
	@Transactional
	public Newspaper createNewspaper(String title, boolean isArchive, Date releaseDate, Creator creator) throws IllegalArgumentException {
		if (title == null || title.trim().length() == 0) {
			throw new IllegalArgumentException("A newspaper cannot have an empty title.");
		}
		if (releaseDate == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty release date.");
		}
		if (creator == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty creator.");
		}
		Newspaper newspaper = new Newspaper();
		newspaper.setTitle(title);
		newspaper.setIsArchive(isArchive);
		newspaper.setIsReservable(false);
		newspaper.setReleaseDate(releaseDate);
		newspaper.setIsAvailable(false);
		newspaper.setCreator(creator);
		newspaperRepository.save(newspaper);
		return newspaper;
	}
	
	@Transactional
	public Newspaper updateNewspaper(Long itemId, String title, boolean isArchive, Date releaseDate, Creator creator) throws IllegalArgumentException {
		Newspaper newspaper = newspaperRepository.findByItemId(itemId);
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		if (title == null || title.trim().length() == 0) {
			throw new IllegalArgumentException("A newspaper cannot have an empty title.");
		}
		if (releaseDate == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty release date.");
		}
		if (creator == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty creator.");
		}
		newspaper.setTitle(title);
		newspaper.setIsArchive(isArchive);
		newspaper.setIsReservable(false);
		newspaper.setReleaseDate(releaseDate);
		newspaper.setIsAvailable(false);
		newspaper.setCreator(creator);
		newspaperRepository.save(newspaper);
		return newspaper;
	}
	
	@Transactional
	public Newspaper deleteNewspaper(Long itemId) throws IllegalArgumentException {
		Newspaper newspaper = newspaperRepository.findByItemId(itemId);
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist");
		}
		newspaperRepository.delete(newspaper);
		return newspaper;
	}
	
	@Transactional
	public Newspaper getNewspaper(Long itemId) throws IllegalArgumentException {
		Newspaper newspaper = newspaperRepository.findByItemId(itemId);
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist");
		}
		return newspaper;
	}
	
	@Transactional
	public List<Newspaper> getAllNewspapers(){
		System.out.println(newspaperRepository.findAll());
		return toList(newspaperRepository.findAll());
	}
	
	public List<Newspaper> toList(Iterable<Newspaper> iterable){
		List<Newspaper> newspapers = new ArrayList<Newspaper>();
		for (Newspaper n: iterable) {
			newspapers.add(n);
		}
		return newspapers;
	}
	

}
