package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.Book.BMGenre;

public class BookDto extends ItemDto {

	boolean isReservable;
	int numPages;
	boolean available;
	BMGenre genre;

	public BookDto() {}

	public BookDto(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numPages, boolean available, BMGenre genre, CreatorDto creator) {
		this.setTitle(title);
		this.setIsArchive(isArchive);
		this.isReservable = isReservable;
		this.setReleaseDate(releaseDate);
		this.numPages = numPages;
		this.available = available;
		this.genre = genre;
		this.setCreator(creator);
	}

	public boolean getIsReservable() {
		return this.isReservable;
	}

	public void setIsReservable(boolean isReservable) {
		this.isReservable = isReservable;
	}

	public int getNumPages() {
		return this.numPages;
	}

	public boolean getIsAvailable() {
		return this.available;
	}

	public void setIsAvailable(boolean available) {
		this.available = available;
	}

	public BMGenre getGenre() {
		return this.genre;
	}

}
