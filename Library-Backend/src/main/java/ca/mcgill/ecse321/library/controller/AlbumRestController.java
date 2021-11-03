package ca.mcgill.ecse321.library.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.AlbumDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;
import ca.mcgill.ecse321.library.service.AlbumService;


@CrossOrigin(origins = "*")
@RestController
public class AlbumRestController {
	
	@Autowired
	private AlbumService service;
	
	@GetMapping(value = { "/albums", "/albums/" })
	public List<AlbumDto> getAllAlbums(){
		return service.getAllAlbums().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/album", "/album/" })
	public AlbumDto getAlbum(@RequestParam(value="albumId") Long albumId){
		return convertToDto(service.getAlbum(albumId));
	}
	
	
	@PostMapping(value = { "/album/create", "/album/create/" })
	public AlbumDto createAlbum(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") Date releaseDate, @RequestParam(value="numSongs") int numSongs, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") MusicGenre genre) throws IllegalArgumentException {
		Album album = service.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, available, genre);
		return convertToDto(album);
	}
	
	@DeleteMapping(value = { "/album/delete", "/album/delete/" })
	public AlbumDto deleteAlbum(@PathVariable("albumId") Long albumId) throws IllegalArgumentException {
		Album album = service.deleteAlbum(albumId);
		return convertToDto(album);
	}
	
	private AlbumDto convertToDto(Album album) {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre());
		return albumDto;
	}

}
