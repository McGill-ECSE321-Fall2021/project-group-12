package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.Album.MusicGenre;

public class AlbumDto extends ItemDto {
	
	boolean isReservable;
	int numSongs;
	boolean available;
	MusicGenre genre;
	
	public AlbumDto() {}
	
	public AlbumDto(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numSongs, boolean available, MusicGenre genre, CreatorDto creator, Long itemId) {
		this.setTitle(title);
		this.setIsArchive(isArchive);
		this.isReservable = isReservable;
		this.setReleaseDate(releaseDate);
		this.numSongs = numSongs;
		this.available = available;
		this.genre = genre;
		this.setCreator(creator);
		this.setItemId(itemId);
	}
	
	public boolean getIsReservable() {
		return this.isReservable;
	}
	
	public void setIsReservable(boolean isReservable) {
		this.isReservable = isReservable;
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

}
