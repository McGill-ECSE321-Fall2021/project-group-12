package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.Album.MusicGenre;

public class AlbumDto {
	
	private String title;
	private boolean isArchive;
	boolean isReservable;
	Date releaseDate;
	int numSongs;
	boolean available;
	MusicGenre genre;
	CreatorDto creator;
	
	public AlbumDto() {}
	
	public AlbumDto(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numSongs, boolean available, MusicGenre genre, CreatorDto creator) {
		this.title = title;
		this.isArchive = isArchive;
		this.isReservable = isReservable;
		this.releaseDate = releaseDate;
		this.numSongs = numSongs;
		this.available = available;
		this.genre = genre;
		this.creator = creator;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean getIsArchive() {
		return this.isArchive;
	}
	
	public void setIsArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}
	
	public boolean getIsReservable() {
		return this.isReservable;
	}
	
	public void setIsReservable(boolean isReservable) {
		this.isReservable = isReservable;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public int getNumSongs() {
		return this.numSongs;
	}
	
	public boolean getIsAvailable() {
		return this.available;
	}
	
	public void setIsAvailable(boolean available) {
		this.available = available;
	}
	
	public MusicGenre getGenre() {
		return this.genre;
	}
	
	public CreatorDto getCreator() {
		return this.creator;
	}

}
