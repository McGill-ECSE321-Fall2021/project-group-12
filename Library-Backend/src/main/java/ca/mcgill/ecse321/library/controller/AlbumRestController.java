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

import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.service.AlbumService;
import ca.mcgill.ecse321.library.service.CreatorService;

@CrossOrigin(origins = "*")
@RestController
public class AlbumRestController {
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private CreatorService creatorService;
	
	@GetMapping(value = { "/albums", "/albums/" })
	public List<AlbumDto> getAllAlbums(){
		return albumService.getAllAlbums().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/album", "/album/" })
	public AlbumDto getAlbum(@RequestParam(value="albumId") Long albumId){
		return convertToDto(albumService.getAlbum(albumId));
	}
	
	@GetMapping(value = {"/album/creator", "/album/creator/"})
	public CreatorDto getAlbumCreator(@RequestParam("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(albumService.getAlbum(itemId)).getCreator();
	}
	
	
	@PostMapping(value = { "/album/create", "/album/create/" })
	public AlbumDto createAlbum(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") String releaseDate, @RequestParam(value="numSongs") int numSongs, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") MusicGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Date date = Date.valueOf(releaseDate);
		Album album = albumService.createAlbum(title, isArchive, isReservable, date, numSongs, available, genre, creator);
		return convertToDto(album);
	}
	
	@PutMapping(value = {"/album/update", "/album/update/"})
	public AlbumDto updateAlbum(@RequestParam("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(albumService.updateAlbum(itemId, isArchive, isReservable, available));
	}
	
	@DeleteMapping(value = { "/album/delete", "/album/delete/" })
	public AlbumDto deleteAlbum(@RequestParam("albumId") Long albumId) throws IllegalArgumentException {
		Album album = albumService.deleteAlbum(albumId);
		return convertToDto(album);
	}
	
	private AlbumDto convertToDto(Album album) {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()), album.getItemId());
		return albumDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId());
		return creatorDto;
	}
}
