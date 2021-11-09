package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class NewspaperDto extends ItemDto {
	
	public NewspaperDto() {}
	
	public NewspaperDto(String title, boolean isArchive, Date releaseDate, CreatorDto creator, Long itemId) {
		this.setTitle(title);
		this.setIsArchive(isArchive);
		this.setReleaseDate(releaseDate);
		this.setCreator(creator);
		this.setItemId(itemId);

	}
}
