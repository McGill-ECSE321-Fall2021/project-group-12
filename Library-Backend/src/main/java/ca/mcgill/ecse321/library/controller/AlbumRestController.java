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
import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;
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
	
	@GetMapping(value = {"/album/creator/{itemId}", "/album/creator/{itemId}/"})
	public CreatorDto getAlbumCreator(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(albumService.getAlbum(itemId)).getCreator();
	}
	
	
	@PostMapping(value = { "/album/create", "/album/create/" })
	public AlbumDto createAlbum(@RequestParam(value="title") String title, @RequestParam(value="isArchive") boolean isArchive, @RequestParam(value="isReservable") boolean isReservable, @RequestParam(value="releaseDate") Date releaseDate, @RequestParam(value="numSongs") int numSongs, @RequestParam(value="isAvailable") boolean available, @RequestParam(value="genre") MusicGenre genre, @RequestParam(value="creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		Album album = albumService.createAlbum(title, isArchive, isReservable, releaseDate, numSongs, available, genre, creator);
		return convertToDto(album);
	}
	
	@PutMapping(value = {"/album/update/{itemId}", "/album/update/{itemId}/"})
	public AlbumDto updateAlbum(@PathVariable("itemId") Long itemId, @RequestParam("isArchive") boolean isArchive, @RequestParam("isReservable") boolean isReservable, @RequestParam("isAvailable") boolean available) throws IllegalArgumentException {
		return convertToDto(albumService.updateAlbum(itemId, isArchive, isReservable, available));
	}
	
	@DeleteMapping(value = { "/album/delete", "/album/delete/" })
	public AlbumDto deleteAlbum(@PathVariable("albumId") Long albumId) throws IllegalArgumentException {
		Album album = albumService.deleteAlbum(albumId);
		return convertToDto(album);
	}
	
	private AlbumDto convertToDto(Album album) {
		if (album == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		AlbumDto albumDto = new AlbumDto(album.getTitle(), album.getIsArchive(), album.getIsReservable(), album.getReleaseDate(), album.getNumSongs(), album.getIsAvailable(), album.getGenre(), convertToDto(album.getCreator()));
		return albumDto;
	}
	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId() ,itemDtosForCreator(creator));
		return creatorDto;
	}
	
	private ItemDto convertToDto(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item does not exist.");
		}
		ItemDto itemDto = new ItemDto(); // To be updated when ItemDto gets updated
		return itemDto;
	}
	
	private List<ItemDto> itemDtosForCreator(Creator creator){
		List<Item> items = creatorService.getItemsByCreator(creator.getCreatorId());
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if (items != null) {
			for (Item i:items) {
				itemDtos.add(convertToDto(i));
			}
		}
		return itemDtos;
	}

}
