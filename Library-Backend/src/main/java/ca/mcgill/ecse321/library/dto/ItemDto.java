package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public abstract class ItemDto {
	
	private String title;
	private boolean isArchive;
	private Date releaseDate;
	private CreatorDto creator;
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean setTitle(String title) {
		this.title = title;
		return true;
	}
	
	public boolean getIsArchive() {
		return this.isArchive;
	}
	
	public boolean setIsArchive(boolean isArchive) {
		this.isArchive = isArchive;
		return true;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public boolean setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		return true;
	}
	
	public CreatorDto getCreator() {
		return this.creator;
	}
	
	public boolean setCreator(CreatorDto creator) {
		this.creator = creator;
		return true;
	}

}
