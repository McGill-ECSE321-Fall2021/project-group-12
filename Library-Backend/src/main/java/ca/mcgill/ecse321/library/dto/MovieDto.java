package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.Movie.BMGenre;

public class MovieDto extends ItemDto {
	
	private boolean isReservable;
	private boolean isAvailable;
	private int duration;
	private BMGenre genre;
	
	public MovieDto() {}
	
	public MovieDto(String title, boolean isArchive, boolean isReservable, boolean isAvailable, Date releaseDate, int duration, BMGenre genre, CreatorDto creator, Long itemId) {
		this.setTitle(title);
		this.setIsArchive(isArchive);
		this.isReservable = isReservable;
		this.isAvailable = isAvailable;
		this.setReleaseDate(releaseDate);
		this.duration = duration;
		this.genre = genre;
		this.setCreator(creator);
		this.setItemId(itemId);

	}
	public boolean getIsReservable() {
		return isReservable;
	}
	public boolean setIsReservable(boolean isReservable) {
		this.isReservable = isReservable;
		return true;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public boolean setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
		return true;
	}
	
	public int getDuration() {
		return duration;
	}
	public boolean setDuration(int duration) {
		this.duration = duration;
		return true;
	}
	
	public BMGenre getGenre() {
		return genre;
	}
	public boolean setGenre(BMGenre genre) {
		this.genre = genre;
		return true;
	}
}
