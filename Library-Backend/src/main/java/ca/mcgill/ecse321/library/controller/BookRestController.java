package ca.mcgill.ecse321.library.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Book.BMGenre;
import ca.mcgill.ecse321.library.service.BookService;

@CrossOrigin(origins = "*")
@RestController
public class BookRestController {

	@Autowired
	private BookService service;
	
	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks(){
		return service.getAllBooks().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/book", "/book/" })
	public BookDto getBook(@RequestParam(value="bookId") Long bookId){
		return convertToDto(service.getBook(bookId));
	}
	
	
	@PostMapping(value = { "/book/create", "/book/create/" })
	public BookDto createBook(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") Date releaseDate, @RequestParam(value="numPages") int numPages, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") BMGenre genre) throws IllegalArgumentException {
		Book book = service.createBook(title, isArchive, isReservable, releaseDate, numPages, available, genre);
		return convertToDto(book);
	}
	
	@DeleteMapping(value = { "/book/delete", "/book/delete/" })
	public BookDto deleteBook(@PathVariable("bookId") Long bookId) throws IllegalArgumentException {
		Book book = service.deleteBook(bookId);
		return convertToDto(book);
	}
	
	private BookDto convertToDto(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre());
		return bookDto;
	}
	
}
