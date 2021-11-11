package ca.mcgill.ecse321.library.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Book.BMGenre;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.CreatorService;

@CrossOrigin(origins = "*")
@RestController
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CreatorService creatorService;
	
	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks(){
		return bookService.getAllBooks().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/book", "/book/" })
	public BookDto getBook(@RequestParam(value="bookId") Long bookId){
		return convertToDto(bookService.getBook(bookId));
	}
	
	@GetMapping(value = {"/book/creator", "/book/creator/"})
	public CreatorDto getBookCreator(@RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(bookService.getBook(itemId)).getCreator();
	}
	
	
	@PostMapping(value = { "/book/create", "/book/create/" })
	public BookDto createBook(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numPages") int numPages, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") BMGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Book book = bookService.createBook(title, isArchive, isReservable, date, numPages, available, genre, creator);
		return convertToDto(book);
	}
	
	@PutMapping(value = {"/book/update", "/book/update/"})
	public BookDto updateBook(@RequestParam("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(bookService.updateBook(itemId, isArchive, isReservable, available));
	}
	
	@DeleteMapping(value = { "/book/delete", "/book/delete/" })
	public BookDto deleteBook(@RequestParam("bookId") Long bookId) throws IllegalArgumentException {
		Book book = bookService.deleteBook(bookId);
		return convertToDto(book);
	}
	
	private BookDto convertToDto(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		BookDto bookDto = new BookDto(book.getTitle(), book.getIsArchive(), book.getIsReservable(), book.getReleaseDate(), book.getNumPages(), book.getIsAvailable(), book.getGenre(), convertToDto(book.getCreator()), book.getItemId());
		return bookDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
	}
	
}
