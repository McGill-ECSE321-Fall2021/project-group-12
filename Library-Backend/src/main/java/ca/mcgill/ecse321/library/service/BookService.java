package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Book.BMGenre;
import ca.mcgill.ecse321.library.model.Creator;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Transactional
	public Book createBook(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numPages, boolean available, BMGenre genre, Creator creator) throws IllegalArgumentException {
		if (title == null || releaseDate == null) {
			throw new IllegalArgumentException("Cannot create book with empty fields.");
		}
		if (title  == "") {
			throw new IllegalArgumentException("Cannot create book with empty title.");
		}
		boolean valid = false;
		for (int i=0; i<title.length();i++) {
			if (title.charAt(i) != ' ') {
				valid = true;
				break;
			}
		}
		
		if (creator == null) {
			throw new IllegalArgumentException("A newspaper cannot have an empty creator.");
		}
		
		if(numPages == 0) {
			valid = false;
		}
		
		if (!valid) {
			throw new IllegalArgumentException("Cannot create book");
		}
		
		Book book = new Book();
		book.setTitle(title);
		book.setIsArchive(isArchive);
		book.setIsReservable(isReservable);
		book.setReleaseDate(releaseDate);
		book.setNumPages(numPages);
		book.setIsAvailable(available);
		book.setGenre(genre);
		book.setCreator(creator);
		bookRepository.save(book);

		return book;	
	}
	
	@Transactional
	public Book updateBook(Long itemId, boolean isArchive, boolean isReservable, boolean available) throws IllegalArgumentException {
		Book book = bookRepository.findBookByItemId(itemId);
		if (book == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		
		book.setIsArchive(isArchive);
		book.setIsReservable(isReservable);
		book.setIsAvailable(available);
		return book;
	}
	
	@Transactional
	public Book deleteBook(Long bookId) throws IllegalArgumentException {
		Book book = bookRepository.findBookByItemId(bookId);
		if (book == null) {
			throw new IllegalArgumentException("Book does not exist.");
		}
		bookRepository.delete(book);
		return book;
		
	}
	
	@Transactional
	public Book getBook(Long bookId) {
		Book book = bookRepository.findBookByItemId(bookId);
		if (book == null) {
			throw new IllegalArgumentException("Book does not exist.");
		}
		return book;
	}
	
	@Transactional
	public List<Book> getAllBooks(){
		return toList(bookRepository.findAll());
	}
	
	public List<Book> toList(Iterable<Book> iterable){
		List<Book> books = new ArrayList<Book>();
		for (Book b:iterable) {
			books.add(b);
		}
		return books;
	}

}
