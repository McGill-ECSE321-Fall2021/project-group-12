package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.Movie.BMGenre;

public class MovieDto {
	
	private String title;
	private boolean isArchive;
	private boolean isReservable;
	private boolean isAvailable;
	private Date releaseDate;
	private int duration;
	private BMGenre genre;
	private CreatorDto creator;
	
	public MovieDto() {}
	
	
	public MovieDto(String title, boolean isArchive, boolean isReservable, boolean isAvailable, Date releaseDate, int duration, BMGenre genre, CreatorDto creator) {
		this.title = title;
		this.isArchive = isArchive;
		this.isReservable = isReservable;
		this.isAvailable = isAvailable;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = genre;
		this.creator = creator;
	}
	public String getTitle() {
		return title;
	}
	public boolean setTitle(String title) {
		this.title = title;
		return true;
	}
	
	public boolean getIsArchive() {
		return isArchive;
	}
	public boolean setIsArchive(boolean isArchive) {
		this.isArchive = isArchive;
		return true;
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
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	public boolean setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
	
	public CreatorDto getCreator() {
		return creator;
	}
	public boolean setCreator(CreatorDto creator) {
		this.creator = creator;
		return true;
	}
}
