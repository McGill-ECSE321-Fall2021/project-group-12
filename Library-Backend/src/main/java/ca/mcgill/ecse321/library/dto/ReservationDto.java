package ca.mcgill.ecse321.library.dto;

import java.util.List;

import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.OfflineUser;
import ca.mcgill.ecse321.library.model.OnlineUser;
import ca.mcgill.ecse321.library.model.TimeSlot;
import ca.mcgill.ecse321.library.model.User;

public class ReservationDto {
	
	private Long reservationId;
	private List<ItemDto> items;
	private List<BookDto> books;
	private List<AlbumDto> albums;
	private List<MovieDto> movies;
	private OfflineUserDto offUser;
	private OnlineUserDto onUser;
	private LibrarianDto librarian;
	private TimeSlotDto timeSlot;
	
	public ReservationDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ReservationDto(List<ItemDto> items, List<BookDto> books, List<AlbumDto> albums, List<MovieDto> movies, OfflineUserDto offUser, OnlineUserDto onUser, LibrarianDto librarian, TimeSlotDto timeSlot, Long reservationId) {
		this.items = items;
		this.books = books;
		this.albums = albums;
		this.movies = movies;
		this.offUser = offUser;
		this.onUser = onUser;
		this.librarian = librarian;
		this.timeSlot = timeSlot;
		this.reservationId = reservationId;
	}
	
	public List<ItemDto> getItems() {
		return this.items;
	}
	
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	
	public List<BookDto> getBooks() {
		return this.books;
	}
	
	public void setBooks(List<BookDto> books) {
		this.books = books;
	}
	
	public List<AlbumDto> getAlbums() {
		return this.albums;
	}
	
	public void setAlbums(List<AlbumDto> albums) {
		this.albums = albums;
	}
	
	public List<MovieDto> getMobies() {
		return this.movies;
	}
	
	public void setMovies(List<MoviesDto> movies) {
		this.movies = movies;
	}
	
	public TimeSlotDto getTimeSlot() {
		return this.timeSlot;
	}
	
	public void setTimeSlot(TimeSlotDto timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public Long getReservationId() {
		return this.reservationId;
	}
	
	public void setReservationId(Long Id) {
		this.reservationId = Id;
	}
	
	public OnlineUserDto getOnlineUser() {
		return this.onUser;
	}
	
	public void setOnlineUser(OnlineUserDto onUser) {
		this.onUser = onUser;
	}
	
	public OfflineUserDto getOfflineUser() {
		return this.offUser;
	}
	
	public void setOfflineUser(OfflineUserDto offUser) {
		this.offUser = offUser;
	}
	
	public LibrarianDto getLibrarian() {
		return this.librarian;
	}
	
	public void setLibrarian(LibrarianDto librarian) {
		this.librarian = librarian;
	}

}
