package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class NewspaperDto {
	
	private String title;
	private boolean isArchive;
	private Date releaseDate;
	private CreatorDto creator;
	
	public NewspaperDto() {}
	
	public NewspaperDto(String title, boolean isArchive, Date releaseDate, CreatorDto creator) {
		this.title = title;
		this.isArchive = isArchive;
		this.releaseDate = releaseDate;
		this.creator = creator;
	}
	
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
