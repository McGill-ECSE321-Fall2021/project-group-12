package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.AlbumRepository;
import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.CreatorRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Newspaper;

@Service
public class CreatorService {

	@Autowired
	CreatorRepository creatorRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AlbumRepository albumRepository;

	@Autowired
	NewspaperRepository newspaperRepository;

	@Transactional
	public Creator createCreator(String firstName, String lastName, CreatorType creatorType) throws IllegalArgumentException {
		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("A creator cannot have an empty first name.");
		}
		if (lastName == null || lastName.trim().length() == 0) {
			throw new IllegalArgumentException("A creator cannot have an empty last name.");
		}
		if (creatorType == null) {
			throw new IllegalArgumentException("A creator cannot have an empty creator type.");
		}
		Creator creator = new Creator();
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(creatorType);
		creatorRepository.save(creator);
		return creator;	
	}

	@Transactional 
	public Creator updateCreator(Long creatorId, String firstName, String lastName, Creator.CreatorType creatorType) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("A creator cannot have an empty first name.");
		}
		if (lastName == null || lastName.trim().length() == 0) {
			throw new IllegalArgumentException("A creator cannot have an empty last name.");
		}
		if (creatorType == null) {
			throw new IllegalArgumentException("A creator cannot have an empty creator type.");
		}
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(creatorType);
		creatorRepository.save(creator);
		return creator;
	}

	@Transactional
	public Creator deleteCreator(Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		creatorRepository.delete(creator);
		return creator;

	}


	@Transactional
	public Creator getCreator(Long creatorId) {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		return creator;
	}

	@Transactional
	public List<Creator> getAllCreators(){
		return toList(creatorRepository.findAll());
	}


	@Transactional
	public List<Item> getItemsByCreator(Long creatorId){
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		List<Item> items = new ArrayList<Item>();
		if (movieRepository != null) {
			List<Movie> movies = toList(movieRepository.findAll());
			for (Movie m:movies) {
				if (m.getCreator().equals(creator)) {
					items.add(m);
				}
			}
		}
		if (albumRepository != null) {
			List<Album> albums = toList(albumRepository.findAll());
			for (Album a:albums) {
				if (a.getCreator().equals(creator)) {
					items.add(a);
				}
			}
		}
		if (bookRepository != null) {
			List<Book> books = toList(bookRepository.findAll());
			for (Book b:books) {
				if (b.getCreator().equals(creator)) {
					items.add(b);
				}
			}
		}
		if (newspaperRepository != null) {
			List<Newspaper> newspapers = toList(newspaperRepository.findAll());
			for (Newspaper n:newspapers) {
				if (n.getCreator().equals(creator)) {
					items.add(n);
				}
			}
		}
		return items;
	}

	public <T> List<T> toList(Iterable<T> iterable){
		List<T> objects = new ArrayList<T>();
		for (T t:iterable) {
			objects.add(t);
		}
		return objects;
	}

}
